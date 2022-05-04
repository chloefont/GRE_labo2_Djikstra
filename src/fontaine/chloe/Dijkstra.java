package fontaine.chloe;

import graph.core.impl.Digraph;
import graph.core.impl.SimpleWeightedEdge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Pour fonctionner, les sommets doivent se distribuer les ids de 0 à n-1
 */
public class Dijkstra {
    private Digraph<ConcreteVertex, SimpleWeightedEdge<ConcreteVertex>> graph;
    private PriorityQueue<ConcreteVertex> priorityQueue;
    private boolean[] visited;
    private ConcreteVertex[] predecessors;
    private int nbVerticesComputed;
    private LinkedList<Integer> path;
    private long totalWeight;

    public Dijkstra(Digraph graph) {
        this.graph = graph;
    }

    void init(int origin) {
        this.visited = new boolean[graph.getNVertices()];
        this.predecessors = new ConcreteVertex[graph.getNVertices()];
        this.priorityQueue = new PriorityQueue<>(graph.getVertices());
        this.path = new LinkedList<>();
        nbVerticesComputed = 0;
        totalWeight = 0;

        for (int i = 0; i < graph.getNVertices(); i++) {
            ConcreteVertex vertex = graph.getVertices().get(i);
            predecessors[vertex.id()] = null;

            if (vertex.id() == origin) {
                vertex.setWeight(0);
                sortPriorityQueue(vertex);
            } else
                vertex.setWeight(Long.MAX_VALUE);
        }
    }

    public void start(int origin, int destination) {
            init(origin);

        while (!priorityQueue.isEmpty()) {
            if (iteration(origin, destination) == -1)
                break;
        }
    }

    int iteration(int origin, int destination) {
        ConcreteVertex vertex = priorityQueue.poll();
        visited[vertex.id()] = true;
        nbVerticesComputed++;

        if (vertex.weight() == Long.MAX_VALUE)
            throw new RuntimeException("No path found between " + origin + " and " + destination);

        if (vertex.id() == destination) {
            computePath(origin, destination);
            totalWeight = vertex.weight();
            return -1;
        }

        for (SimpleWeightedEdge<ConcreteVertex> edge : graph.getSuccessorList(vertex.id())) {
            ConcreteVertex successor = edge.to();

            if (!visited[successor.id()] && (edge.weight() + vertex.weight()) < successor.weight()) {
                successor.setWeight(edge.weight() + vertex.weight());
                predecessors[successor.id()] = vertex;

                sortPriorityQueue(successor);
            }
        }
        return vertex.id();
    }

    void computePath(int origin, int destination) {
        ConcreteVertex current = graph.getVertices().get(destination);
        path.addFirst(current.id());

        while (current.id() != origin) {
            current = predecessors[current.id()];
            path.addFirst(current.id());
        }
    }

    public void printPredecessors() {
        for (int i = 0; i < predecessors.length; i++) {
            System.out.print("(" + graph.getVertices().get(i).weight() + ", " + (predecessors[i] == null ? predecessors[i] : predecessors[i].id()) + ") ");
        }
    }

    private void sortPriorityQueue(ConcreteVertex vertexChanged) {
        //TODO pas très optimisé
        priorityQueue.remove(vertexChanged);
        priorityQueue.add(vertexChanged);
    }

    boolean isVisited(int vertex) {
        return visited[vertex];
    }

    public List<Integer> getPath() {
        return path;
    }

    public long getTotalWeight() {
        return totalWeight;
    }

    public int getNbVerticesComputed() {
        return nbVerticesComputed;
    }

    static public void printPath(LinkedList<Integer> path) {
        for (Integer i : path) {
            System.out.print(i + " ");
        }
    }

    public void printResults() {
        System.out.println("Nombre de sommets visités : " + nbVerticesComputed);
        System.out.println("Poids du chemin : " + totalWeight);
        System.out.print("Chemin : ");
        printPath(path);
    }
}
