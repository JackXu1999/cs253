package edu.emory.cs.utils.graph.span;

import edu.emory.cs.utils.graph.Edge;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class MSTWord {
    private List<String> words;
    private List<float[]> vectors;

    public void readVectors(InputStream in) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        Pattern p = Pattern.compile("\t");
        String line, word;
        float[] vector;
        String[] t;

        words = new ArrayList<>();
        vectors = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            t = p.split(line);
            word = t[0];
            vector = new float[t.length - 1];

            for (int i = 1; i < t.length; i++)
                vector[i - 1] = Float.parseFloat(t[i]);

            words.add(word);
            vectors.add(vector);
        }

        reader.close();
    }

    public SpanningTree findMinimumSpanningTree() {
        SpanningTree tree = new SpanningTree();

        // TODO: This code produces a dummy graph
        Random rand = new Random();
        int size = words.size();

        for (int source = 0; source < size; source++) {
            int target = 0;

            while (true) {
                target = rand.nextInt(size);
                if (target != source) break;
            }

            tree.addEdge(new Edge(source, target, rand.nextFloat()));
        }

        return tree;
    }

    public float getEuclideanDistance(float[] v1, float[] v2) {
        // TODO:
        return 0;
    }

    public float getCosineDistance(float[] v1, float[] v2) {
        // TODO:
        return 0;
    }

    public void printSpanningTree(OutputStream out, SpanningTree tree) {
        PrintStream fout = new PrintStream(new BufferedOutputStream(out));
        fout.println("digraph G {");

        for (Edge edge : tree.getEdges())
            fout.printf("\"%s\" -> \"%s\"[label=\"%5.4f\"];\n", words.get(edge.getSource()), words.get(edge.getTarget()), edge.getWeight());

        fout.println("}");
        fout.close();
    }

    static public <SpanningTree> void main(String[] args) throws Exception {
        final String INPUT_FILE = "/Users/jdchoi/Documents/EmoryNLP/cs571/vsm/word_vectors.txt";
        final String OUTPUT_FILE = "/Users/jdchoi/Documents/EmoryNLP/cs571/vsm/word_vectors.dot";

        MSTWord mst = new MSTWord();

        mst.readVectors(new FileInputStream(INPUT_FILE));
        SpanningTree tree = (SpanningTree) mst.findMinimumSpanningTree();
        mst.printSpanningTree(new FileOutputStream(OUTPUT_FILE), (edu.emory.cs.utils.graph.span.SpanningTree) tree);
        System.out.println(((edu.emory.cs.utils.graph.span.SpanningTree) tree).getTotalWeight());
    }
}
