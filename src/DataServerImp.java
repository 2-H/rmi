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
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author kassem
 */
public class DataServerImp implements DataServerHost {

    @Override
    public byte[] getImage(int x, int y, int w, int h) throws RemoteException {
        Image src;
        try {
            src = ImageIO.read(new File("java.jpg"));
            BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
            ImageIO.write(dst, "png", new File("java_cropped.png"));
            ByteArrayOutputStream arr = new ByteArrayOutputStream();
            ImageIO.write(dst, "png", arr);
            return arr.toByteArray();
        } catch (IOException ex) {
            System.out.println("DataServer.DataServerImp.getImage " + ex.getMessage());
            return null;
        }
    }
    
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        DataServerImp ds = new DataServerImp();
        //ds.getImage(300, 200, 500,700);        
        Naming.rebind("rmi://rmi://127.0.0.1:1234/DataServer", ds);   
     }

}
