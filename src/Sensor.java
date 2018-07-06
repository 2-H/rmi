
import static java.lang.System.exit;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sensor extends UnicastRemoteObject implements SensorInterface {

    public int index;
    Point x1, x2;
    State currentState;
    SupervisorInterface SRI;
    String bindingString;
    byte[] imgBytes;

    @Override
    public SensorData getSensorData() throws Exception {
        DataServerHost ds;
        ds = (DataServerHost) Naming.lookup("rmi://localhost:1235/DataServer");
        imgBytes = ds.getImage(x1.x, x1.y, x2.x, x2.y);
        return new SensorData(this.index, x1, x2, currentState.toString(), imgBytes);
    }

    @Override
    public String ping() throws RemoteException {
        return "I am alive " + this.index;
    }

    enum State {
        STANDBY, WAKE
    };

    public Sensor(int x1, int y1, int x2, int y2) throws RemoteException {
        Random rn = new Random();
        this.index = Math.abs(rn.nextInt()) % 500000000;
        this.x1 = new Point(x1, y1);
        this.x2 = new Point(x2 , y2 );
        currentState = State.WAKE;
        run();
    }

    public Sensor() throws RemoteException {
        super();
        Random rand = new Random();
        this.index = Math.abs(rand.nextInt()) % 500000000;
        x1 = new Point(rand.nextInt(100), rand.nextInt(100));
        x2 = new Point(rand.nextInt(100), rand.nextInt(100));
        currentState = State.WAKE;
    }

    @Override
    public void changeState(int i) throws RemoteException {
        switch (i) {
            case 4://RESTART
                main(null);
                System.out.println("Restarted.");
                break;
            case 3: {//STOP
                try {
                    Naming.unbind("SensorRoom");
                    UnicastRemoteObject.unexportObject(SRI, true);
                } catch (MalformedURLException | NotBoundException | RemoteException ex) {
                    System.out.println("Fatal error: " + ex.getMessage());
                }
                exit(0);
            }
            break;
            case 2: {//STANDBY
                System.out.println("Waiting...");
                currentState = State.STANDBY;
                synchronized (this) {
                    try {
                        wait();
                        System.out.println("Wait finished.");
                    } catch (InterruptedException ex) {
                        System.out.println("Fatal error: " + ex.toString());
                    }
                }
                System.out.println("after waiting");
            }
            break;
            case 1://WAKE
                currentState = State.WAKE;
                System.out.println("Waked.");
                break;
            default:
                break;
        }
    }

    @Override
    public void configureRegion(Point newX1, Point newX2) throws RemoteException {
        this.x1 = newX1;
        this.x2 = newX2;
        System.out.println("My new x1 = (" + x1.getX() + ", " + x1.getY() + ") x2 = (" + x2.getX() + ", " + x2.getY() + ")");
    }

    @Override
    public void setImgBytes(byte[] imgBytes) throws RemoteException {
        this.imgBytes = imgBytes;
    }

    @Override
    public String getState() {
        return currentState.toString();
    }

    @Override
    public Point getX1() throws RemoteException {
        return x1;
    }

    @Override
    public Point getX2() throws RemoteException {
        return x2;
    }

    public void run() {
        //    System.setSecurityManager(new RMISecurityManager());
        try {
            bindingString = "rmi://localhost:1234/Supervisor";//SensorRoom
            SupervisorInterface SRI = (SupervisorInterface) Naming.lookup(bindingString);
            SRI.register(this);
            System.out.println("Region 1");

            try {
                Registry r = LocateRegistry.createRegistry(1236);
            } catch (Exception ex) {
            }

            String sensorname = "rmi://localhost:1236/Sensor" + this.index;
            System.out.println("rebind " + sensorname);
            Naming.rebind(sensorname, this);
//            Scanner sc = new Scanner(System.in);
//            
//            System.out.println("Enter x , y , width , height :");
//            
//            this.imgBytes = ds.getImage(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
        } catch (MalformedURLException | NotBoundException | RemoteException ex) {
            System.out.println("Fatal error: " + ex.toString());
        }
    }

    public static void main(String[] args) throws RemoteException {
        new Sensor().run();
    }

}
