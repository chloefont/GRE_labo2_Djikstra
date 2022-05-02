package fontaine.chloe;

import graph.core.Vertex;
import graph.core.impl.Digraph;
import graph.core.impl.SimpleWeightedEdge;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Djikstra {
    private Digraph<ConcreteVertex, SimpleWeightedEdge<ConcreteVertex>> graph;
    // queue priorité
    //private PriorityQueue<ConcreteVertex> queue; implémenter Comparator
    private ConcreteVertex[] priorityQueue;
    private int priorityQueueSize;

    public Djikstra(Digraph graph, int firstVertex) {
        this.graph = graph;
        init(firstVertex);
    }

    private void init(int firstVertex) {
        priorityQueue = new ConcreteVertex[graph.getVertices().size()];
        this.priorityQueueSize = graph.getNVertices();
        int index = priorityQueueSize - 1;

        for (int i = 0; i < graph.getNVertices(); i++) {
            ConcreteVertex vertex = graph.getVertices().get(i);
            if (vertex.id() == firstVertex)
                vertex.setWeight(0);
            else
                vertex.setWeight(Long.MAX_VALUE);
            priorityQueue[index--] = vertex;
        }
    }

    public void start() {
        while (priorityQueueSize > 0) {

        }
    }

    public void printPriorityQueue() {
        for (int i = 0; i < priorityQueueSize; i++) {
            System.out.print(priorityQueue[i].id() + " ");
        }
    }
}
