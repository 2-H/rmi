
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface SupervisorInterface extends Remote {

    public void register(SensorInterface SI) throws RemoteException;

    public ArrayList<SensorInterface> getSensors() throws RemoteException;

    public HashMap<Integer, SensorInterface> getSecondTier() throws RemoteException;

}
