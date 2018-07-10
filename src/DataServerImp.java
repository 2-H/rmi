/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.imageio.ImageIO;

/**
 *
 * @author kassem
 */
public class DataServerImp extends UnicastRemoteObject implements DataServerHost {

    String imagePath = "java.jpg";

    public DataServerImp() throws RemoteException {
        super();
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public byte[] getImage(int x1, int y1, int x2, int y2) throws RemoteException {
        Image src;
        try {
            System.out.println("getImage from Data Server " + x1);

            src = ImageIO.read(new File(imagePath));
            int w = Math.abs(x2 - x1);
            int h = Math.abs(y2 - y1);
            BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            dst.getGraphics().drawImage(src, 0, 0, w, h, x1, y1, x2, y2, null);
            ImageIO.write(dst, "png", new File("java_cropped.png"));
            ByteArrayOutputStream arr = new ByteArrayOutputStream();
            ImageIO.write(dst, "png", arr);
            return arr.toByteArray();
        } catch (IOException ex) {
            System.out.println("DataServer.DataServerImp.getImage " + ex.getMessage());
            return null;
        }
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException, UnknownHostException {
        //System.setSecurityManager(new RMISecurityManager());

        Registry r = LocateRegistry.createRegistry(1235);
        DataServerImp ds = new DataServerImp();
        //ds.getImage(300, 200, 500,700);        
        Naming.rebind("rmi://"+InetAddress.getLocalHost().getHostAddress()+":1235/DataServer", ds);
    }

}
