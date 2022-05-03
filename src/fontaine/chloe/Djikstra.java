package fontaine.chloe;

import graph.core.Vertex;
import graph.core.impl.Digraph;
import graph.core.impl.SimpleWeightedEdge;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Djikstra {
    private Digraph<ConcreteVertex, SimpleWeightedEdge<ConcreteVertex>> graph;
    private PriorityQueue<ConcreteVertex> priorityQueue;
    private boolean[] visited;
    private ConcreteVertex[] predecessors;

    public Djikstra(Digraph graph, int firstVertex) {
        this.graph = graph;
        init(firstVertex);
    }

    private void init(int firstVertex) {
        this.visited = new boolean[graph.getNVertices()];
        this.predecessors = new ConcreteVertex[graph.getNVertices()];
        this.priorityQueue = new PriorityQueue<>(graph.getVertices());

        for (int i = 0; i < graph.getNVertices(); i++) {
            ConcreteVertex vertex = graph.getVertices().get(i);
            predecessors[vertex.id()] = null;

            if (vertex.id() == firstVertex)
                vertex.setWeight(0);
            else
                vertex.setWeight(Long.MAX_VALUE);
        }
    }

    public void start() {

        while (!priorityQueue.isEmpty()) {
            ConcreteVertex vertex = priorityQueue.poll();
            visited[vertex.id()] = true;

            if (vertex.weight() == Long.MAX_VALUE)
                break;

            for (SimpleWeightedEdge<ConcreteVertex> edge : graph.getSuccessorList(vertex.id())) {
                ConcreteVertex successor = edge.to();

                if (!visited[successor.id()] && (edge.weight() + vertex.weight()) < successor.weight()) {
                    successor.setWeight(edge.weight() + vertex.weight());
                    predecessors[successor.id()] = vertex;

                    //TODO pas très optimisé
                    priorityQueue.remove(successor);
                    priorityQueue.add(successor);
                }
            }
        }

        printPredecessors();
    }

    public void printPredecessors() {
        for (int i = 0; i < predecessors.length; i++) {
            System.out.print("(" + graph.getVertices().get(i).weight() + ", " + (predecessors[i] == null ? predecessors[i] : predecessors[i].id()) + ") ");

        }
    }
}
