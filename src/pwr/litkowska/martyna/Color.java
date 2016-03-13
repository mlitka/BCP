package pwr.litkowska.martyna;

/**
 * Created by Martyna on 2016-03-11.
 */
public class Color {

    private int value;

    public Color() {
        this.value = 0;
    }

    public Color(int value) {
        this.value = value;
    }

    public String toString(){
        return " {c: " + value + "}";
    }

    public void generateColor(int max){
        this.value = 1 + (int)(Math.random() * ((max - 1) + 1));
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
