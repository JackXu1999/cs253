package edu.emory.cs.utils.graph.flow;

import edu.emory.cs.utils.graph.Graph;

public abstract class MFAlgorithm {
    /**
     * @param graph  a graph.
     * @param source the source vertex.
     * @param target the target vertex.
     * @return the maximum flow from the source to the target vertices.
     */
    public abstract MaxFlow getMaximumFlow(Graph graph, int source, int target);
}