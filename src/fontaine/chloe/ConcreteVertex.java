package fontaine.chloe;

import graph.core.Vertex;

public class ConcreteVertex implements Vertex {
    private final int id;

    public ConcreteVertex(int id) {
        this.id = id;
    }

    @Override
    public int id() {
        return 0;
    }
}
