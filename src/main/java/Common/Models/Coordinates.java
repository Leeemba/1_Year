package Common.Models;

import java.io.Serializable;

public class Coordinates implements Validator, Serializable {
    private long x;
    private Double y; //Поле не может быть null


    public Coordinates(long x, Double y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public boolean validate(){
        return (y != null);
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }
}
