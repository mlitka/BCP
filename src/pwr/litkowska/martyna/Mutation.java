package pwr.litkowska.martyna;

/**
 * Created by Martyna on 2016-03-11.
 */
public class Mutation {

    private double probabilityOfMutation;

    public Mutation(double probabilityOfMutation) {
        this.probabilityOfMutation = probabilityOfMutation;
    }

    public Population mutate(Population population){
        population.sortPopulation();
        Genotype genotype1;
        for (int i= population.getNumOfGenotypesInPop()/2; i<population.getNumOfGenotypesInPop(); i++){
            if (Math.random()>probabilityOfMutation){
                genotype1 = population.getPopulation().get((int)(Math.random() * ((population.getNumOfGenotypesInPop() - 2) + 1)));

            }
        }


        return population;
    }
}
