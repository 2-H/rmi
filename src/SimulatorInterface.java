
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SimulatorInterface extends Remote {

    void register(String name,int x,int y) throws RemoteException;

}
