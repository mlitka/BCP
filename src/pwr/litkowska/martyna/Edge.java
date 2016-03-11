package pwr.litkowska.martyna;

/**
 * Created by Martyna on 2016-03-09.
 */
public class Edge {
    private int weight;
    private Vertex vert1;
    private Vertex vert2;

    public Edge(Vertex vert1, Vertex vert2, int weight) {
        this.weight = weight;
        this.vert1 = vert1;
        this.vert2 = vert2;
    }

    public String toString(){
        return "V1 " + vert1 + "   V2 " + vert2 + "    W: "+weight;
    }

    public boolean isValid(){
        return Math.abs(vert1.getColor().getValue() - vert2.getColor().getValue())>=weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vertex getVert1() {
        return vert1;
    }

    public void setVert1(Vertex vert1) {
        this.vert1 = vert1;
    }

    public Vertex getVert2() {
        return vert2;
    }

    public void setVert2(Vertex vert2) {
        this.vert2 = vert2;
    }
}
