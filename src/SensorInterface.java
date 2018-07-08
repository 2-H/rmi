
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SensorInterface extends Remote {

    public void configureRegion(Point newX1, Point newX2) throws RemoteException;

    public void changeState(int i) throws RemoteException;

    public SensorData getSensorData() throws Exception;

    public Point getX1() throws RemoteException;

    public Point getX2() throws RemoteException;

    public String getState() throws RemoteException;

    public String ping() throws RemoteException;

    public void setImgBytes(byte[] imgBytes) throws RemoteException;

}
