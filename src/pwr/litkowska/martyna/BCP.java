package pwr.litkowska.martyna;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BCP {

    private static final String FILE_NAME = "GEOM60.col";
    private static final int MAX_GENERATE_RAND = 100;
    private static final int MAX_ITER = 1000;
    private static final int SIZE_OF_POPULATION = 300;
    private static final int SIZE_OF_GENOTYPE = 60;     //equals num of vertices in graph
    private static final double ALPHA_PARAM = 0.6;
    private static final double CROSSOVER_PROBABILITY = 0.6;
    private static final double MUTATION_PROBABILITY = 0.02;
    private static final int CONTEST_SIZE = 3;
    private static final String CSV_FILE_NAME = "stat60.csv";
    private static final String FILE_HEADER = "id,worst,average,best";


    private Graph graph;
    private Population population;
    private Eval eval;
    private Crossover crossover = new Crossover(CROSSOVER_PROBABILITY);
    private Selection selection;
    private Mutation mutation = new Mutation(MUTATION_PROBABILITY, MAX_GENERATE_RAND);

    public int algorithm() throws IOException {
        Population selected;
        Genotype best;
        FileWriter fileWriter = new FileWriter(CSV_FILE_NAME);
        fileWriter.append(FILE_HEADER.toString());
        fileWriter.append("\n");

        graph = new Graph();
        graph.readGraph(FILE_NAME);

        population = new Population(SIZE_OF_POPULATION, SIZE_OF_GENOTYPE, MAX_GENERATE_RAND);
        population.generatePopulation();
        eval = new Eval(graph, ALPHA_PARAM);
        selection = new Selection(CONTEST_SIZE, eval, crossover);

        eval.evaluatePopulation(population);
        best = new Genotype(population.getBestGenotypeOfPopulation().getGenotype());
        best.setEvaluationValue(population.getBestGenotypeOfPopulation().getEvaluationValue());
        double bestE = best.getEvaluationValue();
        int iter = 0;
        while (iter < MAX_ITER) {

            selected = selection.select(population);
            population.setSelected(selected);
            mutation.mutate(population);
            eval.evaluatePopulation(population);
            double bestOfPop = population.getBestGenotypeOfPopulation().getEvaluationValue();

            if (bestOfPop <= bestE) {
                best = new Genotype(population.getBestGenotypeOfPopulation().getGenotype());
                best.setEvaluationValue(population.getBestGenotypeOfPopulation().getEvaluationValue());
                bestE = bestOfPop;
            }

            fileWriter.append(String.valueOf(iter + 1));
            fileWriter.append(",");
            fileWriter.append(String.valueOf(population.getMaxEval()));
            fileWriter.append(",");
            fileWriter.append(String.valueOf(population.getAvgEval()));
            fileWriter.append(",");
            fileWriter.append(String.valueOf(population.getMinEval()));
            fileWriter.append("\n");

            iter++;

        }
        graph.setColors(best);
        fileWriter.flush();
        fileWriter.close();
        return iter - 1;
    }

    public static void main(String[] args) throws IOException {

        BCP bcp = new BCP();
        int iter = bcp.algorithm();
        System.out.println("ITERATION= " + iter + "\n");
        System.out.println(bcp.graph.getGenotype() + "    MIN= " + bcp.graph.getGenotype().getMinColor().getValue() + "in= " + bcp.graph.getGenotype().getNumOfInvalidEdges());

    }
}
