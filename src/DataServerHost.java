/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author kassem
 */
public interface DataServerHost extends Remote {

    public byte[] getImage(int x, int y, int width, int height) throws RemoteException;

    public void setImagePath(String path) throws RemoteException;

    public String getImagePath() throws RemoteException;
}
