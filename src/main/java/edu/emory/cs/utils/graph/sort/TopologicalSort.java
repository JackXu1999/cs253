package edu.emory.cs.utils.graph.sort;

import edu.emory.cs.utils.graph.Edge;
import edu.emory.cs.utils.graph.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TopologicalSort {
    public List<Integer> sort(Graph graph) {
        Deque<Integer> global = graph.getVerticesWithNoIncomingEdges();
        Deque<Edge>[] outgoingEdgesAll = graph.getOutgoingEdges();
        List<Integer> order = new ArrayList<Integer>();

        while (!global.isEmpty()) {
            Deque<Integer> local = new ArrayDeque<>();
            int vertex = global.poll();

            //Add vertex to the sequence
            order.add(vertex);
            Deque<Edge> outgoingEdges = outgoingEdgesAll[vertex];

            while (!outgoingEdges.isEmpty()) {
                Edge edge = outgoingEdges.poll();
                List<Edge> incomingEdges = graph.getIncomingEdges(edge.getTarget());

                //Remove edge in all incomingEdges of the target vertex
                incomingEdges.remove(edge);

                //If the vertex has no incoming edges, add it to the local queue awaited to be added to the global deque
                if (incomingEdges.isEmpty())
                    local.add(edge.getTarget());
            }

            //Transfer all vertices in local to global
            while (!local.isEmpty())
                //Breath-first search
                global.addLast(local.removeFirst());
            //Depth-first search
//				global.addFirst(local.removeLast());
        }

        if (!graph.isEmpty()) throw new IllegalArgumentException("Cyclic graph.");
        return order;
    }
}
