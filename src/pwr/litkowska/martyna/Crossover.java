package pwr.litkowska.martyna;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martyna on 2016-03-11.
 */
public class Crossover {

    private double probabilityOfCrossover;

    public Crossover(double probabilityOfCrossover) {
        this.probabilityOfCrossover = probabilityOfCrossover;
    }

    // get middle index of genotype list
    // first genotype : taking second genotype's tail and placing it in the first genotype's head
    // second genotype : taking first genotype's head and placing it in the second genotype's tail
    public List<Genotype> crossGenotypes(Genotype genotype1, Genotype genotype2) {

        List<Genotype> newGenotypes = new ArrayList<>();

        if (Math.random() < probabilityOfCrossover) {

            int index = (int) (Math.random() * ((genotype1.getGenotype().size() - 1) + 1));

            List<Color> gen1 = new ArrayList<>();
            List<Color> gen2 = new ArrayList<>();

            for (int i = 0; i < index; i++) {
                gen1.add(genotype1.getGenotype().get(i));
                gen2.add(genotype2.getGenotype().get(i));
            }
            for (int i = index; i < genotype1.getGenotype().size(); i++) {
                gen2.add(genotype1.getGenotype().get(i));
                gen1.add(genotype2.getGenotype().get(i));
            }

            newGenotypes.add(new Genotype(gen1));
            newGenotypes.add(new Genotype(gen2));

        }

        return newGenotypes;
    }
}
