
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

    public int parentIndex;

    public SensorData(int index, Point x1, Point x2, String State, byte[] image, int parentIndex) {
        this.index = index;
        this.x1 = x1;
        this.x2 = x2;
        this.State = State;
        this.image = image;
        this.parentIndex = parentIndex;
    }

    public int getParentIndex() {
        return parentIndex;
    }

    public void setParentIndex(int parentIndex) {
        this.parentIndex = parentIndex;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.index;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SensorData other = (SensorData) obj;
        if (this.index != other.index) {
            return false;
        }
        return true;
    }

}
