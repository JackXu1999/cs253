package edu.emory.cs.utils.graph.path;

import edu.emory.cs.utils.graph.Edge;
import edu.emory.cs.utils.graph.Graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public abstract class AStar {
    public Integer[] getShortestPath(Graph graph, int source, int target) {
        PriorityQueue<VertexDistancePair> queue = new PriorityQueue<>();
        Integer[] previous = new Integer[graph.size()];
        double[] distances = new double[graph.size()];
        Set<Integer> visited = new HashSet<>();

        init(distances, previous, target);
        queue.add(new VertexDistancePair(target, 0));

        while (!queue.isEmpty()) {
            VertexDistancePair u = queue.poll();
            visited.add(u.vertex);

            for (Edge edge : graph.getIncomingEdges(u.vertex)) {
                //Vertex that can be reached through current vertex
                int v = edge.getSource();

                //If the vertex has yet been visited
                if (!visited.contains(v)) {
                    //Calculated distance from target to v
                    double dist = distances[u.vertex] + edge.getWeight();

                    if (dist < distances[v]) {
                        distances[v] = dist + heuristic(v, target);
                        previous[v] = u.vertex;
                        queue.add(new VertexDistancePair(v, dist));
                    }
                }
            }
        }

        return previous;
    }

    private void init(double[] distances, Integer[] previous, int target) {
        for (int i = 0; i < distances.length; i++) {
            //Set distance from target to target as the heuristic value
            if (i == target)
                distances[i] = heuristic(i, target);
            else {
                //Initialize all distance to infinity
                distances[i] = Double.MAX_VALUE;
                //Initialize all previous vertices to null
                previous[i] = null;
            }
        }
    }

    protected abstract double heuristic(int source, int target);

    private class VertexDistancePair implements Comparable<VertexDistancePair> {
        public int vertex;
        public double distance;

        public VertexDistancePair(int vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(VertexDistancePair p) {
            double diff = this.distance - p.distance;
            if (diff > 0) return 1;
            if (diff < 0) return -1;
            return 0;
        }
    }
}