package pwr.litkowska.martyna;

import com.sun.org.apache.bcel.internal.generic.POP;

import java.io.IOException;

public class BCP {

    private static final String FILE_NAME = "GEOM20.col";
    private static final int MAX_GENERATE_RAND = 30;
    private static final int MAX_ITER = 100;
    private static final int SIZE_OF_POPULATION = 100;
    private static final int SIZE_OF_GENOTYPE = 20;
    private static final double ALPHA_PARAM = 0.3;
    private static final double STOP_EVAL_VALUE = 10.0;
    private static final int NUM_OF_CROSSOVERS = 10;

    private Graph graph;
    private Population population;
    private Eval eval;
    private Crossover crossover = new Crossover(NUM_OF_CROSSOVERS);

    public int algorithm() throws IOException {
        graph = new Graph();
        graph.readGraph(FILE_NAME);

        population = new Population(SIZE_OF_POPULATION, SIZE_OF_GENOTYPE, MAX_GENERATE_RAND);
        population.generatePopulation();
        eval = new Eval(graph, ALPHA_PARAM);
        eval.evaluatePopulation(population);

        boolean foundBest = population.checkStopCondition(STOP_EVAL_VALUE)!=-1;
        int iter = 0;

        while (!foundBest && iter<MAX_ITER){
            // crossover -> mutation -> evaluate -> selection
            crossover.crossPopulation(population);
            //TODO mutation
            eval.evaluatePopulation(population);
            foundBest = population.checkStopCondition(STOP_EVAL_VALUE)!=-1;

            iter++;
            //TODO selection
        }
        graph.setColors(population.getBestGenotypeOfPopulation());
        return iter-1;
    }

    public static void main(String[] args) throws IOException {

        BCP bcp = new BCP();
        int iter = bcp.algorithm();

        System.out.println("ITERATION= " + iter);
        System.out.println(bcp.graph.getGenotype());
        bcp.eval.evaluate(bcp.graph.getGenotype());
        System.out.println(bcp.eval);
        System.out.println("\n" + bcp.graph + "\n");

    }
}
