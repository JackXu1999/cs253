package edu.emory.cs.utils.graph.span;

import edu.emory.cs.utils.graph.Edge;
import edu.emory.cs.utils.graph.Graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MSTPrim implements MSTAlgorithm {
    @Override
    public SpanningTree getMinimumSpanningTree(Graph graph) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        SpanningTree tree = new SpanningTree();
        Set<Integer> visited = new HashSet<>();
        Edge edge;

        //Add all connecting vertices from start vertex to the queue
        add(queue, visited, graph, 0);

        while (!queue.isEmpty()) {
            edge = queue.poll();

            if (!visited.contains(edge.getSource())) {
                tree.addEdge(edge);

                //If a spanning tree is found, break.
                if (tree.size() + 1 == graph.size()) break;

                //Add all connecting vertices from current vertex to the queue
                add(queue, visited, graph, edge.getSource());
            }
        }

        return tree;
    }

    /**
     * @param queue   Queue of all vertices awaited to explore
     * @param visited Set of visited vertices
     * @param graph   Graph
     * @param target  Target vertex
     */
    private void add(PriorityQueue<Edge> queue, Set<Integer> visited, Graph graph, int target) {
        visited.add(target);

        for (Edge edge : graph.getIncomingEdges(target)) {
            if (!visited.contains(edge.getSource()))
                queue.add(edge);
        }
    }
}
