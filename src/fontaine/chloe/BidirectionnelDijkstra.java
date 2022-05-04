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

        Dijkstra currentDijkstra = forwardDijkstra;
        Dijkstra otherDijkstra = backwardDijkstra;

        int currentVertex = origin;

        while ((currentVertex = currentDijkstra.iteration(origin, destination)) != -1) {

            if (otherDijkstra.isVisited(currentVertex))
                break;

            Dijkstra tmp = currentDijkstra;
            currentDijkstra = otherDijkstra;
            otherDijkstra = tmp;

            int tmpOr = origin;
            origin = destination;
            destination = tmpOr;
        }

        totalWeight = forwardDijkstra.getTotalWeight() + backwardDijkstra.getTotalWeight();

        currentDijkstra.computePath(origin, destination);
        otherDijkstra.computePath(origin, destination);

        this.forwardPath.addAll(currentDijkstra.getPath());
        Collections.reverse(otherDijkstra.getPath());
        this.forwardPath.addAll(otherDijkstra.getPath());
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
}
