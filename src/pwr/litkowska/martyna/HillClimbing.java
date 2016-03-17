package pwr.litkowska.martyna;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Martyna on 2016-03-17.
 */
public class HillClimbing {

    private static final String FILE_NAME = "GEOM60.col";
    private static final int SIZE_OF_GENOTYPE = 60;
    private static final int MAX_GENERATE_RAND = 100;
    private static final double ALPHA_PARAM = 0.6;
    private static final String CSV_FILE_NAME = "HCstat60.csv";
    private static final String FILE_HEADER = "id,worst,average,best";

    private Graph graph;
    private Eval eval;


    private void prepare() throws IOException {

        graph = new Graph();
        graph.readGraph(FILE_NAME);
        eval = new Eval(graph, ALPHA_PARAM);
    }

    public Genotype search() throws IOException {

        this.prepare();

        FileWriter fileWriter = new FileWriter(CSV_FILE_NAME);
        fileWriter.append(FILE_HEADER.toString());
        fileWriter.append("\n");

        Genotype genotype = new Genotype(SIZE_OF_GENOTYPE, MAX_GENERATE_RAND);
        genotype.generateGenotype();



        eval.evaluate(genotype);
        double min = genotype.getEvaluationValue();
        double max = genotype.getEvaluationValue();
        double avg = genotype.getEvaluationValue();
        int iter = 0;
        boolean foundBetterGen = true;

        while (foundBetterGen) {
            Genotype genotype1 = new Genotype(SIZE_OF_GENOTYPE, MAX_GENERATE_RAND);
            genotype1.addColors(genotype.getGenotype());

            Genotype genotype1copy = new Genotype(SIZE_OF_GENOTYPE, MAX_GENERATE_RAND);
            genotype1copy.addColors(genotype.getGenotype());

            for (int i = 0; i<genotype1.getGenotype().size(); i++){
                int index = (int) (Math.random() * ((genotype1.getGenotype().size() - 1) + 1));
                genotype1copy.setColorValueAtIndex(index, this.getLowerColorValue(genotype.getGenotype().get(index).getValue()));
                eval.evaluate(genotype1copy);
                if(genotype1copy.getEvaluationValue()<genotype.getEvaluationValue()) {
                    genotype = new Genotype(SIZE_OF_GENOTYPE, MAX_GENERATE_RAND);
                    genotype.addColors(genotype1copy.getGenotype());
                    genotype.setEvaluationValue(genotype1copy.getEvaluationValue());
                    break;
                }
                genotype1copy = new Genotype(SIZE_OF_GENOTYPE, MAX_GENERATE_RAND);
                genotype1copy.addColors(genotype1.getGenotype());
                genotype1copy.setEvaluationValue(genotype1.getEvaluationValue());

                if(i==(genotype1.getGenotype().size()-1)){
                    foundBetterGen = false;
                }

            }

            min = genotype.getEvaluationValue();
            avg += genotype.getEvaluationValue();

            iter++;

            fileWriter.append(String.valueOf(iter + 1));
            fileWriter.append(",");
            fileWriter.append(String.valueOf(max));
            fileWriter.append(",");
            fileWriter.append(String.valueOf(avg / (iter+1)));
            fileWriter.append(",");
            fileWriter.append(String.valueOf(min));
            fileWriter.append("\n");
        }


        fileWriter.flush();
        fileWriter.close();
        System.out.println("ITER -- " + iter);
        return genotype;

    }

    private int getLowerColorValue(int value) {
        Color color = new Color();
        int lowerVal = color.generateColorValue(MAX_GENERATE_RAND);
        while (value < lowerVal && value != 0) {
            lowerVal = color.generateColorValue(MAX_GENERATE_RAND);
        }
        return lowerVal;

    }

    public static void main(String[] args) throws IOException {
        HillClimbing hillClimbing = new HillClimbing();
        hillClimbing.search();
        System.out.println(hillClimbing.graph.getGenotype());
    }
}
