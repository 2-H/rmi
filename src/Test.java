
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ali Al-Jobouri
 */
public class Test {

    public static byte[] getImage(int x1, int y1, int x2, int y2) throws RemoteException {
        Image src;
        try {
            System.out.println("getImage from Data Server " + x1);

            src = ImageIO.read(new File("java.jpg"));
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

    public static void main(String[] args) throws IOException {
        ArrayList<SensorData> lst = new ArrayList<>();
        int x1 = 80;
        int y1 = 60;
        int x2 = 125;
        int y2 = 125;

        lst.add(new SensorData(1, new Point(50, 50), new Point(100, 100), "", getImage(50, 50, 100, 100), -1));
        lst.add(new SensorData(1, new Point(120, 50), new Point(200, 100), "", getImage(120, 50, 200, 100), -1));

        BufferedImage dst = new BufferedImage(x2 - x1, y2 - y1, BufferedImage.TYPE_INT_ARGB);
        int index = 0;
        for (SensorData sd : lst) {
            index++;
            Rectangle rect1 = new Rectangle(x1, y1, x2 - x1, y2 - y1);
            Rectangle rect2 = new Rectangle(sd.x1.x, sd.x1.y, sd.x2.x - sd.x1.x, sd.x2.y - sd.x1.y);
            Rectangle i = rect1.intersection(rect2);
            int newx1 = (int) i.getX();
            int newy1 = (int) i.getY();
            int newx2 = (int) i.getX() + (int) i.getWidth();
            int newy2 = (int) i.getY() + (int) i.getHeight();

            int cropx1 = Math.abs(newx1 - sd.x1.x);
            int cropy1 = Math.abs(newy1 - sd.x1.y);
            int cropx2 = Math.abs(newx2 - sd.x1.x);
            int cropy2 = Math.abs(newy2 - sd.x1.y);

            int newrectx1 = Math.abs(newx1 - x1);
            int newrecty1 = Math.abs(newy1 - y1);
            int newrectx2 = Math.abs(newx2 - x1);
            int newrecty2 = Math.abs(newy2 - y1);
            System.out.println("sd.x1.x " + sd.x1.x + "," + sd.x1.y + "_" + sd.x2.x + "," + sd.x2.y);

            System.out.println("x1 " + x1 + "," + y1 + "_" + x2 + "," + y2);

            System.out.println("newx1 " + newx1 + "," + newy1 + "_" + newx2 + "," + newy2);

            System.out.println("cropx1 " + cropx1 + "," + cropy1 + "_" + cropx2 + "," + cropy2);
            System.out.println("newrectx1 " + newrectx1 + "," + newrecty1 + "_" + newrectx2 + "," + newrecty2);

            int w = Math.abs(newx2 - newx1);
            int h = Math.abs(newy2 - newy1);
            if (!i.isEmpty()) {
                {
                    BufferedImage src = ImageIO.read(new ByteArrayInputStream(sd.image));
                    ImageIO.write(src, "png", new File("sensor_" + index + ".png"));

                    dst.getGraphics().drawImage(src, newrectx1, newrecty1, newrectx2, newrecty2, cropx1, cropy1, cropx2, cropy2, null);
                    ImageIO.write(dst, "png", new File("sensor_cropped_" + index + "_width_" + (int) i.getWidth() + "_height_" + (int) i.getHeight() + "_.png"));
                }
            }

            ImageIO.write(dst, "png", new File("sensor_final.png"));

        }
    }
}
