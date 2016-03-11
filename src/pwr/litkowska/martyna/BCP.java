package pwr.litkowska.martyna;

import com.sun.org.apache.bcel.internal.generic.POP;

import java.io.IOException;

public class BCP {

    private static final String FILE_NAME = "GEOM20.col";
    private static final int MAX_GENERATE_RAND = 20;
    private static final int MAX_ITER = 100;
    private static final int SIZE_OF_POPULATION = 100;
    private static final int SIZE_OF_GENOTYPE = 20;
    private static final double ALPHA_PARAM = 0.5;
    private static final double STOP_EVAL_VALUE = 11.5;

    private Graph graph;
    private Population population;
    private Eval eval;

    public int algorithm(){
        population = new Population(SIZE_OF_POPULATION, SIZE_OF_GENOTYPE, MAX_GENERATE_RAND);
        population.generatePopulation();
        boolean foundBest = population.checkStopCondition(STOP_EVAL_VALUE)!=-1;
        int iter = 0;

        while (!foundBest && iter<MAX_ITER){

        }

        return iter;
    }

    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        graph.readGraph(FILE_NAME);
//        System.out.println(graph.toString());

        Genotype genotype = new Genotype(20, MAX_GENERATE_RAND);
        genotype.generateGenotype();
        System.out.println(genotype.toString());
        graph.setColors(genotype.getGenotype());
        System.out.println(graph.toString());

        Eval eval = new Eval(genotype, graph, ALPHA_PARAM);
        eval.evaluate();
        System.out.println(eval.toString());
    }
}
