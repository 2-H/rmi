
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SupervisorInterface extends Remote {

    public void  register(SensorInterface SI) throws RemoteException;
    public ArrayList<SensorInterface> getSensors() throws RemoteException;

}
