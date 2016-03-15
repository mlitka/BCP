package pwr.litkowska.martyna;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martyna on 2016-03-11.
 */
public class Crossover {

    private int numOfCrossOvers;
    private double probabilityOfCrossover;

    public Crossover(int numOfCrossOvers, double probabilityOfCrossover) {
        this.numOfCrossOvers = numOfCrossOvers;
        this.probabilityOfCrossover = probabilityOfCrossover;
    }

    /*public List<Genotype> crossPopulation(Population population) {
        Genotype genotype1;
        Genotype genotype2;
        List<Genotype> newGenotypes = new ArrayList<>();
        for (int i = 0; i < population.getNumOfGenotypesInPop(); i++) {
            genotype1 = population.getPopulation().get((int) (Math.random() * ((population.getNumOfGenotypesInPop() - 2) + 1)));
            genotype2 = population.getPopulation().get((int) (Math.random() * ((population.getNumOfGenotypesInPop() - 2) + 1)));
            if (Math.random() < probabilityOfCrossover && genotype1 != null && genotype2 != null)
                newGenotypes = crossGenotypes(genotype1, genotype2, newGenotypes);
        }
        return newGenotypes;
    }
*/
    // get middle index of genotype list
    // first genotype : taking second genotype's tail and placing it in the first genotype's head
    // second genotype : taking first genotype's head and placing it in the second genotype's tail
    // TODO case when genotype size %2 != 0
    public List<Genotype> crossGenotypes(Genotype genotype1, Genotype genotype2) {

        List<Genotype> newGenotypes = new ArrayList<>();
        if (Math.random() < probabilityOfCrossover){

            int index = (int) (Math.random() * ((genotype1.getGenotype().size() - 1) + 1));
//            List<Color> g1 = genotype1.getGenotype().subList(0, index);
//            List<Color> g2 = genotype2.getGenotype().subList(index, genotype2.getGenotype().size());

            List<Color> gen1 = new ArrayList<>();
            List<Color> gen2 = new ArrayList<>();

            for (int i = 0; i < index; i++) {
                    gen1.add(genotype1.getGenotype().get(i));
                    gen2.add(genotype2.getGenotype().get(i));
//            genotype1.getGenotype().get(i).setValue(g2.get(index-1-i).getValue());
//            genotype2.getGenotype().get(index-i).setValue(g1.get(0).getValue());
            }
            for (int i =index; i < genotype1.getGenotype().size(); i++) {
                    gen2.add(genotype1.getGenotype().get(i));
                    gen1.add(genotype2.getGenotype().get(i));
            }

            newGenotypes.add(new Genotype(gen1));
            newGenotypes.add(new Genotype(gen2));

        }

        return newGenotypes;
    }
}
