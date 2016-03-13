package pwr.litkowska.martyna;

import com.sun.org.apache.bcel.internal.generic.POP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BCP {

    private static final String FILE_NAME = "GEOM20.col";
    private static final int MAX_GENERATE_RAND = 20;
    private static final int MAX_ITER = 1000;
    private static final int SIZE_OF_POPULATION = 1000;
    private static final int SIZE_OF_GENOTYPE = 20;     //equals num of vertices in graph
    private static final double ALPHA_PARAM = 0.35;
    private static final double STOP_EVAL_VALUE = 7.5;
    private static final int NUM_OF_CROSSOVERS = 500;
    private static final double CROSSOVER_PROBABILITY = 0.6;
    private static final double MUTATION_PROBABILITY = 0.2;
    private static final int CONTEST_SIZE = 3;
    private static final int CONTEST_ITER = 500;

    private Graph graph;
    private Population population;
    private Eval eval;
    private Crossover crossover = new Crossover(NUM_OF_CROSSOVERS, CROSSOVER_PROBABILITY);
    private Selection selection;

    public int algorithm() throws IOException {
        List<Genotype> children;
        Population selected;

        graph = new Graph();
        graph.readGraph(FILE_NAME);

        population = new Population(SIZE_OF_POPULATION, SIZE_OF_GENOTYPE, MAX_GENERATE_RAND);
        population.generatePopulation();
        eval = new Eval(graph, ALPHA_PARAM);
        selection = new Selection(CONTEST_SIZE, CONTEST_ITER, eval);

        eval.evaluatePopulation(population);

        boolean foundBest = population.checkStopCondition(STOP_EVAL_VALUE)!=-1;
        int iter = 0;
        int indexOfFoundPop = 0;
        while (!foundBest && iter<MAX_ITER){
            // crossover -> mutation -> evaluate -> selection
            selected = selection.contest(population);
            System.out.println("\nSELECTED SIZE= " + selected.getNumOfGenotypesInPop());
            children = crossover.crossPopulation(selected);
            System.out.println("\nCHILDREN SIZE= " + children.size());
            population.setSelected(selected);
            population.addChildren(children);
//            System.out.println("\nPOPULATION SIZE (SELCTED+CHILDREN+GENERATED)= " + population.getNumOfGenotypesInPop());
            //TODO mutation
            eval.evaluatePopulation(population);
            indexOfFoundPop = population.checkStopCondition(STOP_EVAL_VALUE);
            foundBest = indexOfFoundPop != -1;
            if(foundBest)
                graph.setColors(population.getPopulation().get(indexOfFoundPop));

            iter++;

        }
        if (!foundBest) graph.setColors(population.getBestGenotypeOfPopulation());
        return iter-1;
    }

    public static void main(String[] args) throws IOException {

        BCP bcp = new BCP();
        int iter = bcp.algorithm();
//        Population pop = bcp.selection.contest(bcp.population);
//        System.out.println("\nSELECTED POPULATION= " + pop);
//        System.out.println("\n" + bcp.graph + "\n");
        System.out.println("ITERATION= " + iter);
        System.out.println(bcp.graph.getGenotype());
        bcp.eval.evaluate(bcp.graph.getGenotype());
        System.out.println(bcp.eval + "\nMAX COLOR VALUE= " + bcp.graph.getGenotype().getMaxColor().getValue());


    }
}
