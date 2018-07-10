
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataServerHost extends Remote {

    public byte[] getImage(int x, int y, int width, int height) throws RemoteException;

    public void setImagePath(String path) throws RemoteException;

    public String getImagePath() throws RemoteException;
}
