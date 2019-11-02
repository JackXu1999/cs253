package edu.emory.cs.utils.graph.span;

import edu.emory.cs.utils.graph.Graph;

public interface MSTAlgorithm {
    /**
     * @param graph a graph containing at least one spanning tree.
     * @return a minimum spanning tree.
     */
    public SpanningTree getMinimumSpanningTree(Graph graph);
}