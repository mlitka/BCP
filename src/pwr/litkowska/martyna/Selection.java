 package pwr.litkowska.martyna;

 import java.util.List;

 /**
 * Created by Martyna on 2016-03-13.
 */
public class Selection {

    private int contestSize;
    private int contestIter;
    private Eval eval;
    private Crossover crossover;

    public Selection(int contestSize, int contestIter, Eval eval, Crossover crossover) {
        this.contestSize = contestSize;
        this.contestIter = contestIter;
        this.eval = eval;
        this.crossover = crossover;
    }

    public Population select(Population population){
        Population pop = new Population(population.getSize(), population.getSizeOfGenotype(), population.getMax_generate());
        pop.addGenotype(population.getBestGenotypeOfPopulation());
        for (int i = 0; i < population.getNumOfGenotypesInPop()/2 ; i++) {
            Genotype gen1 = this.contest(population);

            Genotype gen2 = this.contest(population);
//            while (checkSimilarity(gen1, gen2)>0.5)
                gen2 = this.contest(population);

            List<Genotype> list  = this.crossover.crossGenotypes(gen1, gen2);
            list.add(gen1);
            list.add(gen2);
            for (int j = 0; j< list.size(); j++){
                eval.evaluate(list.get(j));
                pop.addGenotype(list.get(j));
            }
//            pop.addGenotypes(list);
        }

        return pop;
    }

    public Genotype contest(Population population) {
        Genotype genotype = findGenotype(population);
            for (int j = 1; j < contestSize; j++) {
                Genotype gen = findGenotype(population);
                if (gen.getEvaluationValue() < genotype.getEvaluationValue()) {
                    genotype = gen;
                }
            }
        return genotype;
    }

    private Genotype findGenotype(Population population) {
        Genotype genotype = population.getPopulation().get((int) (Math.random() * population.getNumOfGenotypesInPop() - 1) + 1);
        return genotype;
    }

    private double checkSimilarity(Genotype gen1, Genotype gen2){
        double similarity = 0;
        for(int i =0; i<gen1.getGenotype().size(); i++){
            if (gen1.getGenotype().get(i).getValue()==gen2.getGenotype().get(i).getValue())
                similarity++;
        }

        return similarity/gen1.getGenotype().size();
    }
}
