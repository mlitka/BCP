package pwr.litkowska.martyna;

/**
 * Created by Martyna on 2016-03-13.
 */
public class Selection {

    private int contestSize;
    private int contestIter;
    private Eval eval;

    public Selection(int contestSize, int contestIter, Eval eval) {
        this.contestSize = contestSize;
        this.contestIter = contestIter;
        this.eval = eval;
    }

    public Population contest(Population population){
        Population pop = new Population(population.getSize(), population.getSizeOfGenotype(), population.getMax_generate());
        Genotype genotype = findGenotype(population);
        for(int i =0; i<contestIter; i++){
            for (int j = 1; j<contestSize; j++){
               Genotype gen = findGenotype(population);
               if(eval.evaluate(gen) < eval.evaluate(genotype)){
                   genotype = gen;
                }
            }
            pop.addGenotype(genotype);
        }

        return pop;
    }

    private Genotype findGenotype(Population population){
        Genotype genotype = population.getPopulation().get((int)(Math.random() * population.getNumOfGenotypesInPop() - 2) + 1);
        return genotype;
    }
}
