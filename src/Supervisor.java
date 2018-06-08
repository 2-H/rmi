
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Supervisor extends UnicastRemoteObject implements SupervisorInterface {

    ArrayList<SensorInterface> ListOfSensorsReference;

    public Supervisor() throws RemoteException {
        super();
        ListOfSensorsReference = new ArrayList<>();
    }

    @Override
    public void register(SensorInterface SI) throws RemoteException {
        ListOfSensorsReference.add(SI);
        System.out.println("New sensor has registered.");
    }

    public static void main(String[] args) {
        try {
            Supervisor theSupervisor = new Supervisor();
            Naming.rebind("rmi://127.0.0.1:1234/SensorRoom", theSupervisor);
        } catch (MalformedURLException | RemoteException e) {
            System.out.println("Fatal error: " + e.getMessage());
        }
    }

}
