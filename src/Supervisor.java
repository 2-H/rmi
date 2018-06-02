

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Supervisor extends UnicastRemoteObject implements SimulatorInterface {

    ArrayList<Sensor> sensors;

    public Supervisor() throws RemoteException {
        super();
        sensors = new ArrayList<>();
    }

    public static void main(String[] args) {
        try {
            Supervisor me = new Supervisor();
            Naming.rebind("rmi://127.0.0.1:1234/Supervisor", me);
        } catch (MalformedURLException | RemoteException ex) {
            System.out.println("Fatal error: " + ex.getMessage());
        }
    }

    @Override
    public void register(String name,int x,int y) {
        System.out.println("Registered successfully.");
        //sensors.add(sensor);
    }
}
