package fontaine.chloe;

import graph.core.Vertex;
import graph.data.CartesianVertexData;

import java.util.Comparator;

public class ConcreteVertex implements Vertex {
    private final int id;
    private final CartesianVertexData position;

    public ConcreteVertex(int id, CartesianVertexData position) {
        this.id = id;
        this.position = position;
    }

    @Override
    public int id() {
        return id;
    }

    public CartesianVertexData position() {
        return position;
    }

//    @Override
//    public int compare(ConcreteVertex v1, ConcreteVertex v2) {
//        if (v1.weight < v2.weight)
//            return -1;
//        if (v1.weight > v2.weight)
//            return 1;
//        return v1.id - v2.id;
//    }

    public String toString() {
        return "(" + id + ")";
    }
}
