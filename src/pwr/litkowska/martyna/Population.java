package pwr.litkowska.martyna;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martyna on 2016-03-11.
 */
public class Population {

    private List<Genotype> population;
    private int size;
    private int sizeOfGenotype;
    private int max_generate;

    public Population(int size, int sizeOfGenotype, int max_generate) {
        this.size = size;
        this.sizeOfGenotype = sizeOfGenotype;
        this.max_generate = max_generate;
        population = new ArrayList<>();
    }

    public String toString(){
        String toStr = "* POPULATION *\n";
        for(Genotype genotype:population){
            toStr+=genotype;
            toStr+=" eval= " + genotype.getEvaluationValue() + "\n";
        }
        toStr+="* * *";
        return toStr;
    }

    public void generatePopulation(){
        for (int i = 0; i<size; i++){
            Genotype genotype = new Genotype(sizeOfGenotype, max_generate);
            genotype.generateGenotype();
            population.add(genotype);
        }
    }

    public int checkStopCondition(double stopCond){
        int check = -1;
        for (Genotype genotype:population){
            if(genotype.getEvaluationValue()<=stopCond){
                check = population.indexOf(genotype);
                break;
            }
        }
        return check;
    }

    public Genotype getBestGenotypeOfPopulation(){
        Genotype genotype = null;
        double min = 10000;
        for(Genotype g:population){
            if(g.getEvaluationValue()<min && g.getEvaluationValue()!=0){
                genotype = g;
                min = g.getEvaluationValue();
            }
        }
        return genotype;
    }

    public List<Genotype> getPopulation() {
        return population;
    }

    public void setPopulation(List<Genotype> population) {
        this.population = population;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSizeOfGenotype() {
        return sizeOfGenotype;
    }

    public void setSizeOfGenotype(int sizeOfGenotype) {
        this.sizeOfGenotype = sizeOfGenotype;
    }

    public int getMax_generate() {
        return max_generate;
    }

    public void setMax_generate(int max_generate) {
        this.max_generate = max_generate;
    }
}
