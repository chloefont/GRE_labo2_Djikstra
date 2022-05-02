package fontaine.chloe;

import graph.core.EdgeWeighter;
import graph.core.Vertex;

public class ConcreteEdgeWeighter implements EdgeWeighter<ConcreteVertex> {

    @Override
    public long weight(ConcreteVertex from, ConcreteVertex to) {
        return 0;
    }
}
