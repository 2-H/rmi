
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ali Al-Jobouri
 */
public class SensorData implements Serializable {

    public Point x1;
    public Point x2;
    public int index;

    public String State;
    public byte[] image;

    public SensorData(int index,Point x1, Point x2, String State, byte[] image) {
        this.index= index;
        this.x1 = x1;
        this.x2 = x2;
        this.State = State;
        this.image = image;
    }

}
