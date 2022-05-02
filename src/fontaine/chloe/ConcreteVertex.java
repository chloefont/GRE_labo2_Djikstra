package fontaine.chloe;

import graph.core.Vertex;
import graph.data.CartesianVertexData;

public class ConcreteVertex implements Vertex {
    private final int id;
    private final CartesianVertexData position;

    public ConcreteVertex(int id, CartesianVertexData position) {
        this.id = id;
        this.position = position;

        System.out.println("ConcreteVertex(" + id + ", " + position.x + " " + position.y + ")");
    }

    @Override
    public int id() {
        return id;
    }

    public CartesianVertexData position() {
        return position;
    }
}
