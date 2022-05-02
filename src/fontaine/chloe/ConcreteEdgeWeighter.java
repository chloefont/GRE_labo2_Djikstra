package fontaine.chloe;

import graph.core.EdgeWeighter;
import graph.core.Vertex;

public class ConcreteEdgeWeighter implements EdgeWeighter<ConcreteVertex> {

    @Override
    public long weight(ConcreteVertex from, ConcreteVertex to) {
        int deltaX = to.position().x - from.position().x;
        int deltaY = to.position().y - from.position().y;
        return Math.round(Math.sqrt(deltaX * deltaX + deltaY * deltaY));
    }
}
