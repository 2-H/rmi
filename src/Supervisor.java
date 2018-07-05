
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class Supervisor extends UnicastRemoteObject implements SupervisorInterface {

    ArrayList<SensorInterface> ListOfSensorsReference;
    Scanner keyboard;
    int imgWidth, imgHeight;
    static final int overlap = 10;

    public Supervisor() throws RemoteException {
        super();
        ListOfSensorsReference = new ArrayList<>();
        keyboard = new Scanner(System.in);
    }

    @Override
    public void register(SensorInterface SI) throws RemoteException {
        ListOfSensorsReference.add(SI);
        keyboard = new Scanner(System.in);
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

    public static void main(String[] args) throws InterruptedException {
        try {
            Registry r = LocateRegistry.createRegistry(1234);
            Supervisor theSupervisor = new Supervisor();
            r.rebind("SensorRoom", theSupervisor);
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

}
