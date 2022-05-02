package fontaine.chloe;

import graph.core.Vertex;
import graph.data.CartesianVertexData;

public class ConcreteVertex implements Vertex {
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

    public CartesianVertexData position() {
        return position;
    }

    void setWeight(long weight) {
        this.weight = weight;
    }
}
