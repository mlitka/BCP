package pwr.litkowska.martyna;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Martyna on 2016-03-11.
 */
public class Population {

    private List<Genotype> population;
    private int size;
    private int sizeOfGenotype;
    private int max_generate;
    private int numOfGenotypesInPop;

    public Population(int size, int sizeOfGenotype, int max_generate) {
        this.size = size;
        this.sizeOfGenotype = sizeOfGenotype;
        this.max_generate = max_generate;
        population = new ArrayList<>();
        numOfGenotypesInPop = 0;
    }

    public String toString() {
        String toStr = "* POPULATION *\n";
        for (Genotype genotype : population) {
            toStr += genotype;
            toStr += " eval= " + genotype.getEvaluationValue() + "\n";
        }
        toStr += "* * *";
        return toStr;
    }

    public void generatePopulation() {
        for (int i = 0; i < size; i++) {
            Genotype genotype = new Genotype(sizeOfGenotype, max_generate);
            genotype.generateGenotype();
            population.add(genotype);
        }
        numOfGenotypesInPop = size;
    }

    public void generatePopulation(int index) {
        for (int i = index; i < size; i++) {
            Genotype genotype = new Genotype(sizeOfGenotype, max_generate);
            genotype.generateGenotype();
            this.addGenotype(genotype);
        }
    }

    public int checkStopCondition(double stopCond) {
        int check = -1;
//        for (Genotype genotype : population) {
//            if (genotype.getEvaluationValue() > 0 && genotype.getEvaluationValue() <= stopCond) {
//                check = population.indexOf(genotype);
//
//                break;
//            }
//        }
        Genotype genotype = this.getBestGenotypeOfPopulation();
        if (genotype.getEvaluationValue()<=stopCond)
            check = population.indexOf(genotype);
        return check;
    }

    public Genotype getBestGenotypeOfPopulation() {
        Genotype genotype = population.get(0);
//        double min = 10000;
        for (Genotype g : population) {
            if (g.getEvaluationValue() < genotype.getEvaluationValue() && g.getEvaluationValue() != 0) {
                genotype = g;
//                min = g.getEvaluationValue();
            }
        }
        return genotype;
    }

    public Genotype getW0rstGenotypeOfPopulation() {
        Genotype genotype = population.get(0);
        double max = 0;
        for (Genotype g : population) {
            if (g.getEvaluationValue() > max) {
                genotype = g;
                max = g.getEvaluationValue();
            }
        }
        return genotype;
    }

    public void selectBest(int howMany) {
        Collections.sort(population);
        population = population.subList(0, howMany);
    }

    public void sortPopulation() {
        Collections.sort(population);
    }

    public void removeGenotype(Genotype genotype) {
        this.population.remove(genotype);
        numOfGenotypesInPop--;
    }

    public void addGenotype(Genotype genotype) {

        if (numOfGenotypesInPop >= size) {
            if (this.getW0rstGenotypeOfPopulation().getEvaluationValue() > genotype.getEvaluationValue()){
                this.removeGenotype(this.getW0rstGenotypeOfPopulation());
                this.population.add(genotype);
                numOfGenotypesInPop++;
            }

        } else {
            this.population.add(genotype);
            numOfGenotypesInPop++;
        }


    }

    public void addGenotypes(List<Genotype> genotypes) {
        for (Genotype genotype : genotypes){
            this.addGenotype(genotype);

        }
    }

    public void setSelected(Population selected) {
        this.population.clear();
        this.population = selected.getPopulation();
        this.numOfGenotypesInPop = selected.numOfGenotypesInPop;
    }

    public double getMinEval(){
        return this.getBestGenotypeOfPopulation().getEvaluationValue();
    }

    public double getMaxEval(){
        return this.getW0rstGenotypeOfPopulation().getEvaluationValue();
    }

    public double getAvgEval(){
        double sum = 0;
        for (Genotype g : population) {
            sum+=g.getEvaluationValue();
        }
        return sum/numOfGenotypesInPop;
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

    public int getNumOfGenotypesInPop() {
        return numOfGenotypesInPop;
    }
}
