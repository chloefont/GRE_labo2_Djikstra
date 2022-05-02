package fontaine.chloe;

import graph.core.Vertex;
import graph.core.VertexFactory;
import graph.data.CartesianVertexData;

public class VertexFact implements VertexFactory<ConcreteVertex, CartesianVertexData /*TODO à changer*/> {

    public VertexFact () {
        super();
    }

    @Override
    public ConcreteVertex makeVertex(int id, CartesianVertexData additionalData) {
        return new ConcreteVertex(id);
    }
}
