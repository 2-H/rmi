
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Supervisor extends UnicastRemoteObject implements SupervisorInterface {

    HashMap<Integer, SensorInterface> secondTier;
    HashMap<SensorInterface, ArrayList<SensorInterface>> baseTier;
    HashMap<Integer, String> ipGetter;
    ArrayList<Integer> waitingSensors;
    Scanner keyboard;
    int imgWidth, imgHeight;
    static final int overlap = 10;

    public Supervisor() throws RemoteException {
        super();
        secondTier = new HashMap<>();
        baseTier = new HashMap<>();
        waitingSensors = new ArrayList<>();
        ipGetter = new HashMap<>();
        keyboard = new Scanner(System.in);
    }

    @Override
    public void register(SensorInterface SI) throws RemoteException {
        try {
            SensorData sd = SI.getSensorData();
            if (sd.parentIndex == -1) {
                secondTier.put(sd.index, SI);
                baseTier.put(SI, new ArrayList<>());
            } else {
                SensorInterface parent = secondTier.get(sd.parentIndex);
                if (baseTier.get(parent) == null) {
                    ArrayList<SensorInterface> arr = new ArrayList<>();
                    baseTier.put(parent, arr);
                }
                baseTier.get(parent).add(SI);
            }
            ipGetter.put(sd.index, SI.getIP());
        } catch (Exception ex) {
            System.out.println("Fatal error: " + ex.toString());
        }
    }

    @Override
    public ArrayList<Integer> getWaitingSensors() {
        return waitingSensors;
    }

    @Override
    public void setWaitingSensors(ArrayList<Integer> waitingSensors) {
        this.waitingSensors = waitingSensors;
    }

    @Override
    public void removeSensor(int index, String ip) throws RemoteException, Exception {
        SensorInterface s;
        ArrayList<SensorInterface> children;
        // in case a child sensor -> remove only the sensor
        // in case a parent sensor -> children of deleted sensor become a parent
        if (secondTier.containsKey(index)) {
            s = secondTier.get(index);
            children = baseTier.get(s);
            if (children == null || children.isEmpty()) {
                secondTier.remove(index);
                baseTier.remove(s);
                return;
            }
            ArrayList<SensorInterface> kids = baseTier.remove(s);
            SensorInterface newParent = kids.get(kids.size() - 1);
            kids.remove(kids.size() - 1);
            newParent.setParentIndex(-1);
            for (SensorInterface kid : kids) {
                kid.setParentIndex(newParent.getSensorData().index);
            }
            secondTier.remove(index);
            secondTier.put(newParent.getSensorData().index, newParent);
            baseTier.put(newParent, kids);
        } else {
            for (SensorInterface tmp : baseTier.keySet()) {
                ArrayList<SensorInterface> arr = baseTier.get(tmp);
                for (SensorInterface si : arr) {
                    if (si.getSensorData().index == index) {
                        arr.remove(si);
                    }
                }
            }
        }
    }

    @Override
    public ArrayList<SensorInterface> getSensors() throws RemoteException {
        ArrayList<SensorInterface> arr = new ArrayList<>();
        for (int parent : secondTier.keySet()) {
            arr.add(secondTier.get(parent));
        }
        for (SensorInterface parent : baseTier.keySet()) {
            ArrayList<SensorInterface> tmp = baseTier.get(parent);
            arr.addAll(tmp);
        }
        return arr;
    }

    public void balanceKids() throws RemoteException {
        if (secondTier.isEmpty()) {
            return;
        }
        ArrayList<SensorInterface> kidsOverflow = new ArrayList<>();
        int kidsSum = 0;
        int average;
        for (ArrayList<SensorInterface> kids : baseTier.values()) {
            kidsSum += kids.size();
        }
        average = (int) Math.ceil(kidsSum / secondTier.values().size() * 1.0);
        if (average == 0) {
            return;
        }
        //putting overflow kids on an array and removing them from the hashmap array
        for (ArrayList<SensorInterface> kids : baseTier.values()) {
            while (kids.size() > average) {
                kidsOverflow.add(kids.get(kids.size() - 1));
                kids.remove(kids.size() - 1);
            }
        }
        //giving each parent the recommended number to balance number of kids
        for (ArrayList<SensorInterface> kids : baseTier.values()) {
            while (kids.size() < average) {
                kids.add(kidsOverflow.get(kidsOverflow.size() - 1));
                kidsOverflow.remove(kidsOverflow.size() - 1);
            }
        }
        //in case an additional kid remaining in the overflow array
        if (kidsOverflow.size() > 0) {
            for (ArrayList<SensorInterface> kids : baseTier.values()) {
                kids.add(kidsOverflow.get(kidsOverflow.size() - 1));
                kidsOverflow.remove(kidsOverflow.size() - 1);
                if (kidsOverflow.isEmpty()) {
                    break;
                }
            }
        }
        //giving the kids the new parent index
        for (SensorInterface SI : baseTier.keySet()) {
            try {
                int parentIndex = SI.getSensorData().index;
                for (SensorInterface kid : baseTier.get(SI)) {
                    kid.setParentIndex(parentIndex);
                }
            } catch (Exception ex) {
                System.out.println("Fatal error: " + ex.toString());
            }
        }
    }

    @Override
    public HashMap<Integer, SensorInterface> getSecondTier() throws RemoteException {
        return secondTier;
    }

    @Override
    public void addToWaiting(Integer index) throws RemoteException {
        waitingSensors.add(index);
    }

    @Override
    public void removeFromWaiting(Integer index) throws RemoteException {
        waitingSensors.remove(index);
    }

    @Override
    public String getSensorIP(int index) throws RemoteException {
        return ipGetter.get(index);
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            Registry r = LocateRegistry.createRegistry(1234);
            Supervisor theSupervisor = new Supervisor();
            Naming.rebind("rmi://" + InetAddress.getLocalHost().getHostAddress() + ":1234/Supervisor", theSupervisor);
            System.out.println("Supervisor running...");
            Thread threadBalance = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            Thread.sleep(10000);
                            theSupervisor.balanceKids();
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(Supervisor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            threadBalance.start();
        } catch (Exception e) {
            System.out.println("Fatal error: " + e.getMessage());
        }
    }
}
