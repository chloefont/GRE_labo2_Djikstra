
/*
 * Le code rendu se situe uniquement dans ce package (event. sous-packages)
 */
package fontaine.chloe;

import fontaine.chloe.ConcreteEdgeWeighter;
import fontaine.chloe.VertexFact;
import graph.core.impl.SimpleWeightedEdgeFactory;
import graph.reader.CartesianGraphReader;

import java.io.IOException;

public class Main {
    /*
     * NE PAS MODIFIER
     * Les fichiers de données sont à placer à la racine de ce répertoire
     */
    private static final String DATA_FOLDER = "data/";

    public static void main(String[] args) throws IOException {
        var graph = new CartesianGraphReader<>(
                new VertexFact(),
                new SimpleWeightedEdgeFactory<ConcreteVertex>(new ConcreteEdgeWeighter()),
                DATA_FOLDER + "R15_1.txt"
        ).graph();
    }
}
