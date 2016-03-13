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

    public double evaluate(Genotype genotype){
        this.graph.setColors(genotype);
        evaluationValue = alpha*genotype.getMaxColor().getValue() + (1-alpha)*graph.getNumOfInvalidEdges();
//        graph.getGenotype().setEvaluationValue(evaluationValue);
        genotype.setEvaluationValue(evaluationValue);
        return evaluationValue;
    }

    public void evaluatePopulation(Population population){
        for (Genotype genotype:population.getPopulation()){
            if (genotype.getGenotype().size()!=0)evaluate(genotype);
        }
    }

    public String toString(){
        return "EVAL = " + String.valueOf(evaluationValue) + "\nNUMBER OF INVALID EDGES = " + graph.getNumOfInvalidEdges();
    }
}
