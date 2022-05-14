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
    private long bestPathLength;
    private LinkedList<Integer> bestPath;

    private int origin = -1;

    private int destination = -1;

    public BidirectionnelDijkstra(Digraph<ConcreteVertex, SimpleWeightedEdge<ConcreteVertex>> graph) {
        this.graph = graph;
    }

    private void init() {
        forwardDijkstra = new Dijkstra(graph);
        backwardDijkstra = new Dijkstra(graph);
        bestPathLength = Long.MAX_VALUE;
        bestPath = new LinkedList<Integer>();
    }

    public void compute(int origin, int destination) {
        init();
        forwardDijkstra.init(origin, destination);
        backwardDijkstra.init(destination, origin);

        Dijkstra currentDijkstra = forwardDijkstra;
        int currentVertexId = origin;

        while (true) {
            currentVertexId = currentDijkstra.iteration(currentDijkstra.getOrigin(), currentDijkstra.getDestination());
            if (vertexAlreadyComputed(currentVertexId)) {
                updateBestPath(currentDijkstra, currentVertexId);
                break;
            }

            updateBestPath(currentDijkstra, currentVertexId);

            currentDijkstra = currentDijkstra == forwardDijkstra ? backwardDijkstra : forwardDijkstra;
        }
    }

    public LinkedList<Integer> computePath(int currentVertexId, int linkVertexId) {
        int delta = -1;

        if (forwardDijkstra.isVisited(linkVertexId) && backwardDijkstra.isVisited(linkVertexId)) {
            forwardDijkstra.computePath(forwardDijkstra.getOrigin(), linkVertexId);
            backwardDijkstra.computePath(backwardDijkstra.getOrigin(), linkVertexId);
            delta = -2; // - 2 car on ne veut pas ajouter à double le sommet de lien
        } else if (forwardDijkstra.isVisited(linkVertexId)) {
            forwardDijkstra.computePath(forwardDijkstra.getOrigin(), linkVertexId);
            backwardDijkstra.computePath(backwardDijkstra.getOrigin(), currentVertexId);
        } else {
            forwardDijkstra.computePath(forwardDijkstra.getOrigin(), currentVertexId);
            backwardDijkstra.computePath(backwardDijkstra.getOrigin(), linkVertexId);
        }

        backwardDijkstra.computePath(backwardDijkstra.getOrigin(), linkVertexId);
        bestPath = forwardDijkstra.getPath();


        for (int i = backwardDijkstra.getPath().size() + delta; i >= 0; i--) {
            bestPath.add(backwardDijkstra.getPath().get(i));
        }

        return bestPath;
    }

    public void printResults() {
        System.out.println("Nombre de sommets visités : " + (forwardDijkstra.getNbVerticesComputed() + backwardDijkstra.getNbVerticesComputed()));
        System.out.println("Poids du chemin : " + bestPathLength);
        System.out.print("Chemin : ");
        Dijkstra.printPath(bestPath);
    }

    private boolean vertexAlreadyComputed(int vertexId){
        // déjà traité si a été visité par les 2 listes
        return forwardDijkstra.isVisited(vertexId) && backwardDijkstra.isVisited(vertexId);
    }

    private void updateBestPath(Dijkstra currentDijkstra, int currentVertex) {
        Dijkstra other = (currentDijkstra == forwardDijkstra) ? backwardDijkstra : forwardDijkstra;

        for (SimpleWeightedEdge<ConcreteVertex> succ : graph.getSuccessorList(currentVertex)) {
            if (other.isVisited(succ.to().id())){
                long newPathLength = other.getWeight(succ.to().id()) + currentDijkstra.getWeight(currentVertex) + succ.weight();
                if (newPathLength < bestPathLength) {
                    bestPath = computePath(currentVertex, succ.to().id());
                    bestPathLength = newPathLength;
                }
            }
        }
    }
}
