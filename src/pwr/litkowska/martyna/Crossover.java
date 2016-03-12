package pwr.litkowska.martyna;

import sun.security.util.Debug;

import java.util.List;

/**
 * Created by Martyna on 2016-03-11.
 */
public class Crossover {

    private int numOfCrossOvers;
    private double probabilityOfCrossover;

    public Crossover(int numOfCrossOvers, double probabilityOfCrossover) {
        this.numOfCrossOvers = numOfCrossOvers;
        this.probabilityOfCrossover= probabilityOfCrossover;
    }

    public void crossPopulation(Population population){
        Genotype genotype1;
        Genotype genotype2;
        for(int i = 0; i<numOfCrossOvers; i++){
            genotype1 = population.getPopulation().get((int)(probabilityOfCrossover*(Math.random() * ((population.getPopulation().size() - 2) + 1))));
            genotype2 = population.getPopulation().get((int)(probabilityOfCrossover*(Math.random() * ((population.getPopulation().size() - 2) + 1))));
            crossGenotypes(genotype1, genotype2);
        }
    }

    // get middle index of genotype list
    // first genotype : taking second genotype's tail and placing it reversed in the first genotype's head
    // second genotype : taking first genotype's head and placing it reversed in the second genotype's tail
    // TODO case when genotype size %2 != 0
    public void crossGenotypes(Genotype genotype1, Genotype genotype2){
        int index = genotype1.getGenotype().size()/2;

        List<Color> g1 = genotype1.getGenotype().subList(0,index-1);
        List<Color> g2 = genotype2.getGenotype().subList(index, genotype1.getGenotype().size());

        for(int i = 0; i<index; i++){
            genotype1.getGenotype().get(i).setValue(g2.get(index-1-i).getValue());
            genotype2.getGenotype().get(index-i).setValue(g1.get(0).getValue());
        }
    }
}
