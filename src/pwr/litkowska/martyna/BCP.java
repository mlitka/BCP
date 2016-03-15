package pwr.litkowska.martyna;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BCP {

    private static final String FILE_NAME = "GEOM20.col";
    private static final int MAX_GENERATE_RAND = 100;
    private static final int MAX_ITER =100;
    private static final int SIZE_OF_POPULATION = 200;
    private static final int SIZE_OF_GENOTYPE = 20;     //equals num of vertices in graph
    private static final double ALPHA_PARAM = 0.9;
    private static final double STOP_EVAL_VALUE = 25;
    private static final int NUM_OF_CROSSOVERS = 100;
    private static final double CROSSOVER_PROBABILITY = 0.6;
    private static final double MUTATION_PROBABILITY = 0.02;
    private static final int CONTEST_SIZE = 3;
    private static final int CONTEST_ITER = 300;
    private static final String CSV_FILE_NAME = "stat.csv";
    private static final String FILE_HEADER = "id,worst,average,best";


    private Graph graph;
    private Population population;
    private Eval eval;
    private Crossover crossover = new Crossover(NUM_OF_CROSSOVERS, CROSSOVER_PROBABILITY);
    private Selection selection;
    private Mutation mutation = new Mutation(MUTATION_PROBABILITY, MAX_GENERATE_RAND);

    public int algorithm() throws IOException {
        List<Genotype> children;
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
        selection = new Selection(CONTEST_SIZE, CONTEST_ITER, eval, crossover);

        eval.evaluatePopulation(population);
        best = new Genotype(population.getBestGenotypeOfPopulation().getGenotype());
        best.setEvaluationValue(population.getBestGenotypeOfPopulation().getEvaluationValue());
        double bestE = best.getEvaluationValue();
//        boolean foundBest = false;
        int iter = 0;
        int indexOfFoundPop = -1;
        while (iter < MAX_ITER) {
            // crossover -> mutation -> evaluate -> selection

            selected = selection.select(population);
            System.out.println("\n" + (iter + 1) + "");

//            children = crossover.crossPopulation(selected);
//            System.out.println("\nSEL" + selected);
//            System.out.println("\n" + selected);
            population.setSelected(selected);
//            population.addGenotypes(children);
            System.out.println("\nSEL SIZE = " + selected.getNumOfGenotypesInPop());
            mutation.mutate(population);
            eval.evaluatePopulation(population);
            System.out.print(population);
            indexOfFoundPop = population.checkStopCondition(STOP_EVAL_VALUE);

            System.out.println("\nINDEX OF FOUND BEST = " + indexOfFoundPop);
//            foundBest = (indexOfFoundPop != -1);
            double bestOfPop = population.getBestGenotypeOfPopulation().getEvaluationValue();

            if (bestOfPop <= bestE) {
//                population.getPopulation().get(indexOfFoundPop).normalize();
                best = population.getBestGenotypeOfPopulation();
//                best.setEvaluationValue(population.getBestGenotypeOfPopulation().getEvaluationValue());
                bestE = bestOfPop;
//                eval.evaluate(best);
                System.out.println("IF--> "+best);

//                best = population.getPopulation().get(indexOfFoundPop);
            }

//            indexOfFoundPop = -1;

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
        if (indexOfFoundPop != -1 ) {
            graph.setColors(population.getBestGenotypeOfPopulation());
        }
        graph.setColors(best);
        fileWriter.flush();
        fileWriter.close();
        return iter - 1;
    }

    public static void main(String[] args) throws IOException {

        BCP bcp = new BCP();
        int iter = bcp.algorithm();
//        Population pop = bcp.selection.contest(bcp.population);
//        System.out.println("\nSELECTED POPULATION= " + pop);
//        System.out.println("\n" + bcp.graph + "\n");
        System.out.println("ITERATION= " + iter + "\n" );
        System.out.println(bcp.graph.getGenotype()+"    MIN= "+bcp.graph.getGenotype().getMinColor().getValue() + "in= "+ bcp.graph.getGenotype().getNumOfInvalidEdges());

//        bcp.eval.evaluate(bcp.graph.getGenotype());
//        System.out.println(bcp.graph.getGenotype().getNumOfInvalidEdges() + "\nMAX COLOR VALUE= " + bcp.graph.getGenotype().getMaxColor().getValue());
//        System.out.print(bcp.graph.getGenotype());

    }
}
