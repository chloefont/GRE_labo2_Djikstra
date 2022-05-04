package fontaine.chloe;

import graph.core.Vertex;
import graph.data.CartesianVertexData;

import java.util.Comparator;

public class ConcreteVertex implements Vertex, Comparable<ConcreteVertex> {
    private final int id;
    private final CartesianVertexData position;
    private long weight;

    public ConcreteVertex(int id, CartesianVertexData position) {
        this.id = id;
        this.position = position;
        this.weight = 0;
    }

    @Override
    public int id() {
        return id;
    }

    public long weight() {
        return weight;
    }

    public CartesianVertexData position() {
        return position;
    }

    void setWeight(long weight) {
        this.weight = weight;
    }

//    @Override
//    public int compare(ConcreteVertex v1, ConcreteVertex v2) {
//        if (v1.weight < v2.weight)
//            return -1;
//        if (v1.weight > v2.weight)
//            return 1;
//        return v1.id - v2.id;
//    }

    @Override
    public int compareTo(ConcreteVertex other) {
        if (this.weight < other.weight)
            return -1;
        if (this.weight > other.weight)
            return 1;
        return this.id - other.id;
    }

    public String toString() {
        return "(" + id + ", " + weight + ")";
    }
}
