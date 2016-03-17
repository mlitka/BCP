package pwr.litkowska.martyna;

/**
 * Created by Martyna on 2016-03-11.
 */
public class Mutation {

    private double probabilityOfMutation;
    private int maxGenerateColor;

    public Mutation(double probabilityOfMutation, int maxGenerateColor) {
        this.probabilityOfMutation = probabilityOfMutation;
        this.maxGenerateColor = maxGenerateColor;
    }

    public void mutate(Population population) {
        Color color = new Color();
        for (int i = 0; i < population.getNumOfGenotypesInPop(); i++) {
                    Genotype genotype1 = population.getPopulation().get((int) (Math.random() * ((population.getNumOfGenotypesInPop() - 1) + 1)));
                    for (int j = 0; j < genotype1.getSize(); j++) {
                        if (Math.random() < probabilityOfMutation) {
                            genotype1.setColorValueAtIndex(j, color.generateColorValue(maxGenerateColor));
                        }
                }
        }
    }
}
