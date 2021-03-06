package edu.emory.cs.utils.graph.flow;

import edu.emory.cs.utils.graph.Edge;
import edu.emory.cs.utils.graph.Graph;
import edu.emory.cs.utils.graph.Subgraph;

import java.util.List;

public class FordFulkerson extends MFAlgorithm {
    /**
     * @param graph  a graph.
     * @param source the source vertex.
     * @param target the target vertex.
     * @return the maximum flow from the source to the target vertices.
     */
    public MaxFlow getMaximumFlow(Graph graph, int source, int target) {
        MaxFlow mf = new MaxFlow(graph);
        Subgraph sub;
        double min;

        //Continuously find a new path to push flow from source to target
        while ((sub = getAugmentingPath(graph, mf, new Subgraph(), source, target)) != null) {
            //Get the edge with the minimum edge
            min = getMin(mf, sub.getEdges());

            //Update all forward path edges with -min.getWeight()
            mf.updateResidual(sub.getEdges(), min);
            //Update all backward path edges with +min.getWeight()
            updateBackward(graph, sub, mf, min);
        }

        return mf;
    }

    /**
     * @param graph Graph
     * @param sub   that contains both forward (and backward) edges
     * @param mf    Found MaxFlow
     * @param min   Found weight of edge with minimum weight within the path list
     */
    protected void updateBackward(Graph graph, Subgraph sub, MaxFlow mf, double min) {
        boolean found;

        for (Edge edge : sub.getEdges()) {
            found = false;

            for (Edge rEdge : graph.getIncomingEdges(edge.getSource())) {
                if (rEdge.getSource() == edge.getTarget()) {
                    mf.updateResidual(rEdge, -min);
                    found = true;
                    break;
                }
            }

            if (!found) {
                Edge rEdge = graph.setDirectedEdge(edge.getTarget(), edge.getSource(), 0);
                mf.updateResidual(rEdge, -min);
            }
        }
    }

    /**
     * @param mf   Found MaxFlow
     * @param path Found path from source to target
     * @return weight of edge with minimum weight within the path list
     */
    private double getMin(MaxFlow mf, List<Edge> path) {
        double min = mf.getResidual(path.get(0));
        int i, size = path.size();

        for (i = 1; i < size; i++)
            min = Math.min(min, mf.getResidual(path.get(i)));

        return min;
    }

    /**
     * @param graph  Graph
     * @param mf     MaxFlow currently found
     * @param sub    Subgraph that contains both forward (and backward) edges
     * @param source Source vertex
     * @param target Target vertex
     * @return Augmented subgraph
     */
    private Subgraph getAugmentingPath(Graph graph, MaxFlow mf, Subgraph sub, int source, int target) {
        if (source == target) return sub;
        Subgraph tmp;

        for (Edge edge : graph.getIncomingEdges(target)) {
            if (sub.contains(edge.getSource())) continue;    // cycle
            if (mf.getResidual(edge) <= 0) continue;    // no residual
            tmp = new Subgraph(sub);
            tmp.addEdge(edge);

            tmp = getAugmentingPath(graph, mf, tmp, source, edge.getSource());
            if (tmp != null) return tmp;
        }

        return null;
    }
}