package pwr.litkowska.martyna;

/**
 * Created by Martyna on 2016-03-11.
 */
public class Eval {

    private Graph graph;
    private double alpha;
    private double evaluationValue;

    public Eval(Graph graph, double alpha) {
        this.graph = graph;
        this.alpha = alpha;
        evaluationValue = 0;
    }

    public double evaluate(Genotype genotype) {
        genotype.normalize();
        this.graph.setColors(genotype);
        evaluationValue = (genotype.getMaxColor().getValue()-genotype.getMinColor().getValue())*alpha + (1-alpha)*(graph.getNumOfInvalidEdges());
//        evaluationValue = evaluationValue*evaluationValue*evaluationValue;
//        graph.getGenotype().setEvaluationValue(evaluationValue);
        genotype.setEvaluationValue(evaluationValue);
        genotype.setNumOfInvalidEdges(graph.getNumOfInvalidEdges());

        System.out.println("INVALID!!  " + graph.getSumOfInvalidEdges());
        return evaluationValue;
    }

    public double evaluatePopulation(Population population) {
        double sum = 0;
        for (Genotype genotype : population.getPopulation()) {
            sum += evaluate(genotype);
        }
        return sum;
    }

    public double getAvgOfPop(Population population) {
        return this.evaluatePopulation(population) / population.getNumOfGenotypesInPop();
    }

    public String toString() {
        return "EVAL = " + String.valueOf(evaluationValue) + "\nNUMBER OF INVALID EDGES = " + graph.getNumOfInvalidEdges();
    }
}
