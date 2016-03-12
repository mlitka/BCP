package pwr.litkowska.martyna;

import sun.security.util.Debug;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Martyna on 2016-03-11.
 */
public class Graph {

    private List<Vertex> vertices;
    private List<Edge> edges;
    private int numberOfVertices;
    private int numberOfEdges;
    private Genotype genotype;

    public Graph() {
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        numberOfEdges = 0;
    }

    public void readGraph(String fileName) throws IOException {
        Scanner scanner = new Scanner(new File(fileName));
        String strLine;
        Scanner line = new Scanner("");
        while(scanner.hasNextLine()){
            strLine = scanner.nextLine();
            line = new Scanner(strLine);
            if(strLine.startsWith("c")){
                continue;
            } else if(strLine.startsWith("p")) {
                line.skip("p band");
                numberOfVertices = line.nextInt();
//                numberOfEdges = line.nextInt();
                makeVertices();
            } else if(strLine.startsWith("e")){
                line.skip("e");
                int id1 = line.nextInt();
                int id2 = line.nextInt();
                if(id1!=id2){
                    edges.add(new Edge(findVertById(id1), findVertById(id2), line.nextInt()));
                    numberOfEdges++;
                }
            } else {
                break;
            }
        }
        line.close();
        scanner.close();

    }

    public void makeVertices(){
        for(int i = 0; i<numberOfVertices; i++){
            vertices.add(new Vertex(i+1));
        }
    }

    public Vertex findVertById(int vertId){
        Vertex vertex = null;
        for(Vertex v:vertices){
            if(v.getId() == vertId){
                vertex = v;
                break;
            }
        }
        return vertex;
    }

    public boolean isValid(){
        boolean isValid = true;
        for (Edge e:edges){
            if(!e.isValid()){
                isValid = e.isValid();
            }
        }
        return isValid;
    }

    public int getNumOfInvalidEdges(){
        int invalidEdges = 0;
        for (Edge e:edges){
            if(!e.isValid()){
                invalidEdges += 1;
            }
        }
        return invalidEdges;
    }

    public void setColors(Genotype genotype)
    {
        for(int i = 0; i<genotype.getGenotype().size(); i++){
            findVertById(i+1).setColor(genotype.getGenotype().get(i));
        }
        this.genotype = genotype;
    }

    public String toString(){
       String toStr = "Verices: "+numberOfVertices+"\nEdges: "+numberOfEdges;
        for(Edge e:edges){
            toStr+="\n";
            toStr += e.toString();
        }
        return toStr;
    }

    public Genotype getGenotype() {
        return genotype;
    }

    public void setGenotype(Genotype genotype) {
        this.genotype = genotype;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    public void setNumberOfVertices(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public void setNumberOfEdges(int numberOfEdges) {
        this.numberOfEdges = numberOfEdges;
    }
}
