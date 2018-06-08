
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface SupervisorInterface extends Remote {
         public void register(SensorInterface SI) throws RemoteException;

}
