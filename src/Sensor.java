
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class Sensor extends UnicastRemoteObject implements SensorInterface {

    int x, y;

    public Sensor() throws RemoteException {
        super();
        Random rand = new Random();
        x = rand.nextInt(100);
        y = rand.nextInt(100);
    }

    @Override
    public void Configure_X_Y(int newX, int newY) throws RemoteException {
        this.x = newX;
        this.y = newY;
        System.out.println("My new x and y are (" + newX + ", " + y + ").");
    }

    @Override
    public int getX() throws RemoteException {
        return x;
    }

    @Override
    public int getY() throws RemoteException {
        return y;
    }

    public void run() {
        //System.setSecurityManager(new RMISecurityManager());
        try {
            SupervisorInterface SRI = (SupervisorInterface) Naming.lookup("rmi://127.0.0.1:1234/SensorRoom");
            SRI.register(this);
        } catch (MalformedURLException | NotBoundException | RemoteException ex) {
            System.out.println("Fatal error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) throws RemoteException {
        new Sensor().run();
    }

}
