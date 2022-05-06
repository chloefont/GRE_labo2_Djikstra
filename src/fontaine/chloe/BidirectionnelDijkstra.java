package fontaine.chloe;

import graph.core.impl.Digraph;
import graph.core.impl.SimpleWeightedEdge;

import java.util.Collections;
import java.util.LinkedList;

public class BidirectionnelDijkstra {
    private Digraph<ConcreteVertex, SimpleWeightedEdge<ConcreteVertex>> graph;
    private Dijkstra forwardDijkstra;
    private Dijkstra backwardDijkstra;
    LinkedList<Integer> forwardPath;
    long totalWeight;

    public BidirectionnelDijkstra(Digraph<ConcreteVertex, SimpleWeightedEdge<ConcreteVertex>> graph) {
        this.graph = graph;
    }

    private void init() {
        forwardDijkstra = new Dijkstra(graph);
        backwardDijkstra = new Dijkstra(graph);
        totalWeight = 0;
        forwardPath = new LinkedList<Integer>();
    }

    public void compute(int origin, int destination) {
        init();
        forwardDijkstra.init(origin);
        backwardDijkstra.init(destination);
        int currentVertex = origin;;

        while (true) {
            currentVertex = forwardDijkstra.iteration(origin, destination);
            if (vertexAlreadyComputed(currentVertex))
                break;

            currentVertex = backwardDijkstra.iteration(destination, origin);
            if (vertexAlreadyComputed(currentVertex))
                break;
        }

        totalWeight = forwardDijkstra.getTotalWeight() + backwardDijkstra.getTotalWeight();

        forwardDijkstra.computePath(origin, currentVertex);
        backwardDijkstra.computePath(destination, currentVertex);

        this.forwardPath.addAll(forwardDijkstra.getPath());
        Collections.reverse(backwardDijkstra.getPath());
        backwardDijkstra.getPath().remove(0);
        this.forwardPath.addAll(backwardDijkstra.getPath());
    }

    public LinkedList<Integer> getPath() {
        return this.forwardPath;
    }

    public void printResults() {
        System.out.println("Nombre de sommets visités : " + forwardDijkstra.getNbVerticesComputed() + backwardDijkstra.getNbVerticesComputed());
        System.out.println("Poids du chemin : " + totalWeight);
        System.out.print("Chemin : ");
        Dijkstra.printPath(forwardPath);
    }

    private boolean vertexAlreadyComputed(int vertexId){
        // déjà traité si a été visité par les 2 listes
        return forwardDijkstra.isVisited(vertexId) && backwardDijkstra.isVisited(vertexId);
    }
}
