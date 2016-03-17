package pwr.litkowska.martyna;

/**
 * Created by Martyna on 2016-03-09.
 */
public class Vertex {
    private int id;
    private Color color;

    public Vertex(int id) {
        this.id = id;
        this.color = new Color();
    }

    public String toString() {
        return "Id: " + id + color;
    }

    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
