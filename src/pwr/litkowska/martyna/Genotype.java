package pwr.litkowska.martyna;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Martyna on 2016-03-11.
 */
public class Genotype implements Comparable<Genotype>{


    private int size;
    private List<Color> genotype;
    private int max_generate;
    private double evaluationValue;

    public Genotype(int size, int max_generate) {
        this.size = size;
        this.max_generate = max_generate;
        genotype = new ArrayList<Color>();
        evaluationValue = 0;
    }

    public void generateGenotype(){
        for (int i = 0; i<size; i++){
            Color color = new Color();
            color.generateColor(max_generate);
            genotype.add(color);
        }
    }

    public Color getMaxColor(){
        Color min = genotype.get(0);
        for(Color color:genotype){
            if(color.getValue()>min.getValue()){
                min = color;
            }
        }
        return min;
    }

    public String toString(){
        String toStr = "GENOTYPE -- [";
        for(Color color:genotype){
            toStr+=color;
            toStr+=" ";
        }
        toStr+="]";
        return toStr;
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

    @Override
    public int compareTo(Genotype o) {
        return ((Double)this.evaluationValue).compareTo(o.evaluationValue);
    }
}
