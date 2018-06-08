
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SensorInterface extends Remote {

    public void Configure_X_Y(int newX, int newY) throws RemoteException;

    public int getX() throws RemoteException;

    public int getY() throws RemoteException;
}
