package edu.emory.cs.utils.graph.path;

public class Dijkstra extends AStar {
    /*
     * Dijkstra's algorithm is a more specific A* algorithm with the heuristic of 0
     */
    @Override
    protected double heuristic(int source, int target) {
        return 0;
    }
}