package edu.emory.cs.utils.graph.span;

import edu.emory.cs.utils.graph.Graph;
import edu.emory.cs.utils.graph.path.Dijkstra;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MSTWordXu extends MSTWord {
    public MSTWordXu(InputStream in) {
        super(in);
    }
    @Override
    protected Graph createGraph() {
        graph = new Graph(vertices.size());
        double dotProduct = 0;
        double sumA = 0;
        double sumB = 0;
        double aLength;
        double bLength;
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                for (int k = 0; k < vertices.get(i).getVector().length; k++) {
                    double tmp = (vertices.get(i).getVector()[k]) * (vertices.get(j).getVector()[k]);
                    dotProduct += tmp;
                    sumA += Math.pow(vertices.get(i).getVector()[k], 2);
                    sumB += Math.pow(vertices.get(j).getVector()[k], 2);
                }
                aLength = Math.sqrt(sumA);
                bLength = Math.sqrt(sumB);
                double cosine_sim = (dotProduct / (aLength * bLength));
                double weight = 1 - cosine_sim;
                graph.setUndirectedEdge(i, j, weight);
                sumA = 0;
                sumB = 0;
                dotProduct = 0;
            }
        }
        return graph;
    }

    @Override
    public SpanningTree getMinimumSpanningTree() {
        MSTAlgorithm prim = new MSTPrim();
        SpanningTree tree;
        tree = prim.getMinimumSpanningTree(graph);
        return tree;
    }

    @Override
    public List<String> getShortestPath(int source, int target) {
        Dijkstra d = new Dijkstra();
        Integer array[] = (d.getShortestPath(graph, source, target)).clone();
        List<String> result = new ArrayList<>();
        int k = source;
        while (k != target) {
            result.add(vertices.get(k).getWord());
            k = array[k];
        }
        result.add(vertices.get(target).getWord());
        return result;
    }

    static public void main(String[] args) throws Exception {
        final String INPUT_FILE = "src/main/resources/word_vectors.txt";
        final String OUTPUT_FILE = "src/main/resources/word_vectors.dot";

        MSTWord mst = new MSTWordXu(new FileInputStream(INPUT_FILE));
        SpanningTree tree = mst.getMinimumSpanningTree();
        mst.printSpanningTree(new FileOutputStream(OUTPUT_FILE), tree);
        System.out.println((mst.getShortestPath(12, 303)));
        System.out.println(tree.getTotalWeight());
    }
}
