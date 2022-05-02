package fontaine.chloe;

import graph.core.Vertex;
import graph.core.VertexFactory;
import graph.data.CartesianVertexData;

public class VertexFact implements VertexFactory<ConcreteVertex, CartesianVertexData> {

    public VertexFact () {
        super();
    }

    @Override
    public ConcreteVertex makeVertex(int id, CartesianVertexData additionalData) {
        CartesianVertexData data = new CartesianVertexData();
        data.x = additionalData.x;
        data.y = additionalData.y;
        return new ConcreteVertex(id, data);
    }
}
