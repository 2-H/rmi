
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Sensor extends UnicastRemoteObject implements Remote {

    public Sensor() throws RemoteException {
        super();
    }

    public void run() {
        try {
            SimulatorInterface sim = (SimulatorInterface) Naming.lookup("rmi://127.0.0.1:1234/Supervisor");
            System.out.println("Registered");
            sim.register("s1", 100, 200);
        } catch (MalformedURLException | NotBoundException | RemoteException ex) {
            System.out.println("Fatal error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) throws RemoteException {
        new Sensor().run();
    }

}
