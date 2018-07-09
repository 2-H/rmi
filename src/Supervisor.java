
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
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

    ArrayList<SensorInterface> ListOfSensorsReference;
    HashMap<Integer, SensorInterface> secondTier;
    HashMap<SensorInterface, ArrayList<SensorInterface>> baseTier;
    HashMap<Integer, String> ipGetter;
    ArrayList<Integer> waitingSensors;
    Scanner keyboard;
    int imgWidth, imgHeight;
    static final int overlap = 10;

    public Supervisor() throws RemoteException {
        super();
        ListOfSensorsReference = new ArrayList<>();
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
                System.out.println("HERE 1: " + sd.parentIndex);
            } else {
                SensorInterface parent = secondTier.get(sd.parentIndex);
                System.out.println("HERE 2: " + parent.getSensorData().index);
                if (baseTier.get(parent) == null) {
                    ArrayList<SensorInterface> arr = new ArrayList<>();
                    baseTier.put(parent, arr);
                }
                baseTier.get(parent).add(SI);
                System.out.println("temple: " + parent.getSensorData().index);
                System.out.println("\t" + baseTier.get(parent).get(0).getSensorData().index);
            }
            ipGetter.put(sd.index, SI.getIP());
        } catch (Exception ex) {
            Logger.getLogger(Supervisor.class.getName()).log(Level.SEVERE, null, ex);
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

    public void configureRegion(int sensorNbr) throws RemoteException {
        Point x1 = new Point();
        Point x2 = new Point();
        System.out.print("Enter new x1 x: ");
        x1.setX(keyboard.nextInt());
        System.out.print("Enter new x1 y: ");
        x1.setY(keyboard.nextInt());
        System.out.print("Enter new x2 x: ");
        x2.setX(keyboard.nextInt());
        System.out.print("Enter new x2 y: ");
        x2.setY(keyboard.nextInt());
        ListOfSensorsReference.get(sensorNbr).configureRegion(x1, x2);
        System.out.println("Sensor configured successfully.");
    }

    public void changeState(int sensorNbr) throws RemoteException, InterruptedException {
        int choice;
        System.out.print("[1]:Wake\n[2]:Stand by\n[3]:Stop\n[4]:Restart\nYour choice: ");
        choice = keyboard.nextInt();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ListOfSensorsReference.get(sensorNbr).changeState(choice);
                    if (choice == 1) {
                        synchronized (ListOfSensorsReference.get(sensorNbr)) {
                            ListOfSensorsReference.get(sensorNbr).notify();
                        }
                    }
                    if (choice == 3) {
                        ListOfSensorsReference.remove(sensorNbr);
                        System.out.println("Sensor removed.");
                    }
                } catch (Exception ex) {
                    System.out.println("Fatal error: " + ex.toString());
                }
            }
        }).start();
        System.out.println("Status changed successfully.");
    }

    public void showDescription(int sensorNbr) throws RemoteException {
        System.out.println("Region: x1 = (" + ListOfSensorsReference.get(sensorNbr).getX1().getX() + ", "
                + ListOfSensorsReference.get(sensorNbr).getX1().getY() + ") x2 = ("
                + ListOfSensorsReference.get(sensorNbr).getX2().getX() + ", "
                + ListOfSensorsReference.get(sensorNbr).getX2().getY() + ")");
        System.out.println("Current state: " + ListOfSensorsReference.get(sensorNbr).getState());

    }

    public void generator() throws RemoteException, InterruptedException {
        int choice, sensorNbr;
        while (true) {
            System.out.print("[1]Configure Region\n[2]Change state\n[3]Show Description\nYour choice: ");
            choice = keyboard.nextInt();
            System.out.print("Enter sensor number [0->" + (ListOfSensorsReference.size() - 1) + "]: ");
            sensorNbr = keyboard.nextInt();
            switch (choice) {
                case 1:
                    configureRegion(sensorNbr);
                    break;
                case 2:
                    changeState(sensorNbr);
                    break;
                case 3:
                    showDescription(sensorNbr);
                    break;
                default:
                    break;
            }
        }
    }

    public void distributeRegions() throws RemoteException {
        int val = imgWidth / ListOfSensorsReference.size(), tmp = 0;
        for (int i = 0; i < ListOfSensorsReference.size(); i++) {
            Point x1 = new Point(tmp, 0);
            Point x2;
            if (i == ListOfSensorsReference.size() - 1) {
                x2 = new Point(val * i + overlap, imgHeight);
            } else {
                x2 = new Point(val * i, imgHeight);
            }
            ListOfSensorsReference.get(i).configureRegion(x1, x2);
            tmp += val;
        }
    }

    @Override
    public ArrayList<Integer> removeSensor(int index, String ip) throws RemoteException, Exception {
        SensorInterface s;
        ArrayList<SensorInterface> children;
        ArrayList<Integer> childrenIndexes = new ArrayList<>();
        // in case a child sensor -> remove only the sensor
        // in case a parent sensor -> children of deleted sensor become a parent
        String bindingString = "rmi://" + ip + ":1236/Sensor" + index;
        if (secondTier.containsKey(index)) {
            s = secondTier.get(index);
            System.out.println("index: " + s.getSensorData().index + "\t" + baseTier.keySet().contains(s));
            children = baseTier.get(s);
            if (children == null) {
                secondTier.remove(index);
                Naming.unbind(bindingString);
                return null;
            }
            for (SensorInterface tmp : children) {
                tmp.setParentIndex(-1);
                secondTier.put(tmp.getSensorData().index, tmp);
                childrenIndexes.add(tmp.getSensorData().index);
                System.out.println("index: " + tmp.getSensorData().index + " parent: " + tmp.getSensorData().parentIndex);
            }
            baseTier.remove(s);
            secondTier.remove(index);
            Naming.unbind(bindingString);
            return childrenIndexes;
        } else {
            System.out.println("HERE 2");
            for (SensorInterface tmp : baseTier.keySet()) {
                ArrayList<SensorInterface> arr = baseTier.get(tmp);
                for (SensorInterface si : arr) {
                    if (si.getSensorData().index == index) {
                        arr.remove(si);
                        Naming.unbind(bindingString);
                        return null;
                    }
                }
            }
        }
        System.out.println(baseTier + "\n" + secondTier);
        Naming.unbind(bindingString);
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            System.setSecurityManager(new RMISecurityManager());
            Registry r = LocateRegistry.createRegistry(1234);
            Supervisor theSupervisor = new Supervisor();
            r.rebind("Supervisor", theSupervisor);
            while (true) {
                try {
                    theSupervisor.generator();
                } catch (Exception ex) {
                    System.out.println("Fatal error: " + ex.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Fatal error: " + e.getMessage());
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
}
