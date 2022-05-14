
/*
 * Le code rendu se situe uniquement dans ce package (event. sous-packages)
 */
package fontaine.chloe;

import graph.core.impl.Digraph;
import graph.core.impl.SimpleWeightedEdge;
import graph.core.impl.SimpleWeightedEdgeFactory;
import graph.reader.CartesianGraphReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    /*
     * NE PAS MODIFIER
     * Les fichiers de données sont à placer à la racine de ce répertoire
     */
    private static final String DATA_FOLDER = "data/";

    static private void test() {
        ConcreteVertex v0 = new ConcreteVertex(0, null);
        ConcreteVertex v1 = new ConcreteVertex(1, null);
        ConcreteVertex v2 = new ConcreteVertex(2, null);
        ConcreteVertex v3 = new ConcreteVertex(3, null);
        ConcreteVertex v4 = new ConcreteVertex(4, null);
        ConcreteVertex v5 = new ConcreteVertex(5, null);

        List<ConcreteVertex> vertices = new ArrayList<>(6);
        vertices.add(v0);
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);

        List<SimpleWeightedEdge> succV0 = new ArrayList<>(2);
        succV0.add(new SimpleWeightedEdge<>(v0, v1, 3));
        succV0.add(new SimpleWeightedEdge<>(v0, v3, 1));

        List<SimpleWeightedEdge> succV1 = new ArrayList<>(2);
        succV1.add(new SimpleWeightedEdge<>(v1, v2, 4));
        succV1.add(new SimpleWeightedEdge<>(v1, v3, 1));

        List<SimpleWeightedEdge> succV2 = new ArrayList<>(3);
        succV2.add(new SimpleWeightedEdge<>(v2, v1, 3));
        succV2.add(new SimpleWeightedEdge<>(v2, v4, 1));
        succV2.add(new SimpleWeightedEdge<>(v2, v5, 2));

        List<SimpleWeightedEdge> succV3 = new ArrayList<>(3);
        succV3.add(new SimpleWeightedEdge<>(v3, v1, 1));
        succV3.add(new SimpleWeightedEdge<>(v3, v2, 6));
        succV3.add(new SimpleWeightedEdge<>(v3, v4, 3));

        List<SimpleWeightedEdge> succV4 = new ArrayList<>(2);
        succV4.add(new SimpleWeightedEdge<>(v4, v2, 1));
        succV4.add(new SimpleWeightedEdge<>(v4, v5, 3));

        List<SimpleWeightedEdge> succV5 = new ArrayList<>(0);

        List<List<SimpleWeightedEdge>> listSucc = new ArrayList<>(6);
        listSucc.add(succV0);
        listSucc.add(succV1);
        listSucc.add(succV2);
        listSucc.add(succV3);
        listSucc.add(succV4);
        listSucc.add(succV5);

        Digraph graph = new Digraph(vertices, listSucc);

        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.start(0, 4);
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        var graph = new CartesianGraphReader<>(
                new VertexFact(),
                new SimpleWeightedEdgeFactory<>(new ConcreteEdgeWeighter()),
                DATA_FOLDER + "R10000_1.txt"
        ).graph();

        System.out.println("Biderectionnel");
        BidirectionnelDijkstra dijkstra1 = new BidirectionnelDijkstra(graph);
        dijkstra1.compute(0, 9999);
        dijkstra1.printResults();

        System.out.println();
        System.out.println();
        System.out.println("Classic");
        Dijkstra dijkstra2 = new Dijkstra(graph);
        dijkstra2.start(0, 9999);
        dijkstra2.printResults();
    }
}
