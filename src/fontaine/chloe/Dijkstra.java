package fontaine.chloe;

import graph.core.impl.Digraph;
import graph.core.impl.SimpleWeightedEdge;

import java.util.*;

/**
 * Pour fonctionner, les sommets doivent se distribuer les ids de 0 à n-1
 */
public class Dijkstra {
    private class Node implements Comparable<Node> {
        private final ConcreteVertex vertex;
        long weight;
        Node previous;
        boolean visited;

        private Node(ConcreteVertex vertex, long weight) {
            this.vertex = vertex;
            this.weight = weight;
            this.previous = null;
            this.visited = false;
        }

        @Override
        public int compareTo(Node other) {
            if (this.weight < other.weight)
                return -1;
            if (this.weight > other.weight)
                return 1;
            return this.vertex.id() - other.vertex.id();
        }
    }

    private Digraph<ConcreteVertex, SimpleWeightedEdge<ConcreteVertex>> graph;
    private ArrayList<Node> nodes;
    private PriorityQueue<Node> priorityQueue;
    private int nbVerticesComputed;
    private LinkedList<Integer> path;
    private long totalWeight;
    private int origin = -1;
    private int destination = -1;

    public Dijkstra(Digraph graph) {
        this.graph = graph;
    }

    void init(int origin, int destination) {
        priorityQueue = new PriorityQueue<>(graph.getNVertices());
        nodes = new ArrayList<>(graph.getNVertices());
        nbVerticesComputed = 0;
        totalWeight = 0;
        this.origin = origin;
        this.destination = destination;

        for (ConcreteVertex vertex : graph.getVertices()) {
            if (vertex == null)
                continue;

            Node node = new Node(vertex, (vertex.id() == origin) ? 0 : Long.MAX_VALUE);
            priorityQueue.add(node);
            nodes.add(vertex.id(), node);
        }
    }

    public void start(int origin, int destination) {
        init(origin, destination);

        while (!priorityQueue.isEmpty()) {
            if (iteration(origin, destination) == -1)
                break;
        }
    }

    int iteration(int origin, int destination) {
        Node node = priorityQueue.poll();
        nodes.get(node.vertex.id()).visited = true;
        nbVerticesComputed++;

        if (node.weight == Long.MAX_VALUE)
            throw new RuntimeException("No path found between " + origin + " and " + destination);

        if (node.vertex.id() == destination) {
            computePath(origin, destination);
            totalWeight = node.weight;
            return -1;
        }

        for (SimpleWeightedEdge<ConcreteVertex> edge : graph.getSuccessorList(node.vertex.id())) {
            Node successorNode = nodes.get(edge.to().id());

            if (!successorNode.visited && (edge.weight() + node.weight) < successorNode.weight) {
                successorNode.weight = edge.weight() + node.weight;
                successorNode.previous = node;

                sortPriorityQueue(successorNode);
            }
        }
        return node.vertex.id();
    }

    void computePath(int origin, int destination) {

        this.path = new LinkedList<>();
        Node current = nodes.get(graph.getVertices().get(destination).id());
        path.addFirst(current.vertex.id());

        while (current.vertex.id() != origin) {
            current = current.previous;
            path.addFirst(current.vertex.id());
        }
    }

    private void sortPriorityQueue(Node vertexChanged) {
        //TODO pas très optimisé
        priorityQueue.remove(vertexChanged);
        priorityQueue.add(vertexChanged);
    }

    boolean isVisited(int vertex) {
        return nodes.get(vertex).visited;
    }

    public LinkedList<Integer> getPath() {
        return path;
    }

    public long getTotalWeight() {
        return totalWeight;
    }

    public int getNbVerticesComputed() {
        return nbVerticesComputed;
    }

    protected long getWeight(int vertex) {
        return nodes.get(vertex).weight;
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

    public int getOrigin() {
        return origin;
    }

    public int getDestination() {
        return destination;
    }
}
