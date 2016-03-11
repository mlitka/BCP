package pwr.litkowska.martyna;

/**
 * Created by Martyna on 2016-03-11.
 */
public class Eval {

    private Genotype genotype;
    private Graph graph;
    private double alpha;
    private double evaluationValue;

    public Eval(Genotype genotype, Graph graph, double alpha) {
        this.genotype = genotype;
        this.graph = graph;
        this.alpha = alpha;
        evaluationValue = 0;
    }

    public void evaluate(){
        evaluationValue = alpha*genotype.getMaxColor().getValue() + (1-alpha)*graph.getNumOfInvalidEdges();
        genotype.setEvaluationValue(evaluationValue);
    }

    public String toString(){
        return "EVAL = " + String.valueOf(evaluationValue) + "\nNUMBER OF INVALID EDGES = " + graph.getNumOfInvalidEdges();
    }
}
