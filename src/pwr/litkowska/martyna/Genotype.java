package pwr.litkowska.martyna;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martyna on 2016-03-11.
 */
public class Genotype implements Comparable<Genotype> {


    private int size;
    private List<Color> genotype;
    private int max_generate = 20;
    private double evaluationValue = 0;
    private int numOfInvalidEdges;


    public Genotype(int size, int max_generate) {
        this.size = size;
        this.max_generate = max_generate;
        genotype = new ArrayList<Color>();
    }

    public Genotype(List<Color> genotype) {
        this.genotype = genotype;
        size = genotype.size();

    }

    public void generateGenotype() {
        for (int i = 0; i < size; i++) {
            Color color = new Color();
            color.generateColor(max_generate);
            genotype.add(color);
        }
    }

    public Color getMaxColor() {
        Color max = genotype.get(0);
        for (Color color : genotype) {
            if (color.getValue() > max.getValue()) {
                max = color;
            }
        }
        return max;
    }

    public Color getMinColor() {
        Color min = genotype.get(0);
        for (Color color : genotype) {
            if (color.getValue() < min.getValue()) {
                min = color;
            }
        }
        return min;
    }

    public String toString() {
        String toStr = "GENOTYPE -- [";
        for (Color color : genotype) {
            toStr += color;
            toStr += " ";
        }
        toStr += "]";
        toStr += "\n eval= " + evaluationValue;
        toStr += "\n invalid= " + numOfInvalidEdges;
        toStr += "\n max= " + getMaxColor().getValue();
        return toStr;
    }

    public void normalize(){
        int min = this.getMinColor().getValue();
        for (Color color: genotype) {
            color.setValue(color.getValue()-min);
        }
    }

    public void setColorValueAtIndex(int index, int value){
        this.genotype.get(index).setValue(value);
    }

    public List<Color> getGenotype() {
        return genotype;
    }

    public void setGenotype(List<Color> genotype) {
        this.genotype = genotype;
    }

    public double getEvaluationValue() {
        return evaluationValue;
    }

    public void setEvaluationValue(double evaluationValue) {
        this.evaluationValue = evaluationValue;
    }

    public int getSize() {
        return size;
    }

    public int getNumOfInvalidEdges() {
        return numOfInvalidEdges;
    }

    public void setNumOfInvalidEdges(int numOfInvalidEdges) {
        this.numOfInvalidEdges = numOfInvalidEdges;
    }

    @Override
    public int compareTo(Genotype o) {
        return ((Double) this.evaluationValue).compareTo(o.evaluationValue);
    }
}
