
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface SupervisorInterface extends Remote {

    public void register(SensorInterface SI) throws RemoteException;

    public ArrayList<SensorInterface> getSensors() throws RemoteException;

    public HashMap<Integer, SensorInterface> getSecondTier() throws RemoteException;

    public void setWaitingSensors(ArrayList<Integer> waitingSensors) throws RemoteException;

    public ArrayList<Integer> getWaitingSensors() throws RemoteException;

    public void addToWaiting(Integer index) throws RemoteException;

    public void removeFromWaiting(Integer index) throws RemoteException;

    public ArrayList<Integer> removeSensor(int index, String ip) throws RemoteException, Exception;
    
    public String getSensorIP(int index) throws RemoteException;

}
