package edu.emory.cs.utils.graph.span;

import edu.emory.cs.utils.graph.Edge;
import edu.emory.cs.utils.graph.Graph;
import edu.emory.cs.utils.set.DisjointSet;

import java.util.PriorityQueue;

public class MSTKruskal implements MSTAlgorithm {
    @Override
    public SpanningTree getMinimumSpanningTree(Graph graph) {
        DisjointSet forest = new DisjointSet(graph.size());
        PriorityQueue<Edge> queue = createEdgePQ(graph);
        SpanningTree tree = new SpanningTree();
        Edge edge;

        while (!queue.isEmpty()) {
            edge = queue.poll();

            if (!forest.inSameSet(edge.getTarget(), edge.getSource())) {
                tree.addEdge(edge);

                // a spanning tree is found
                if (tree.size() + 1 == graph.size()) break;
                // merge forests
                forest.union(edge.getTarget(), edge.getSource());
            }
        }

        return tree;
    }

    /**
     * @param graph Graph
     * @return PriorityQueue that contains all edges in graph sorted by their weights
     */
    private PriorityQueue<Edge> createEdgePQ(Graph graph) {
        return new PriorityQueue<Edge>(graph.getAllEdges());
    }
}