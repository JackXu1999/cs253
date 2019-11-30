package edu.emory.cs.utils.graph.path;

import edu.emory.cs.utils.graph.Graph;
import org.junit.Test;

public class PathDijkstraTest {
    @Test
    public void test() {
        Dijkstra d = new Dijkstra();
        Graph g = new Graph(6);

        g.setDirectedEdge(0, 1, 4);
        g.setDirectedEdge(0, 2, 2);
        g.setDirectedEdge(1, 2, 5);
        g.setDirectedEdge(1, 3, 10);
        g.setDirectedEdge(2, 4, 3);
        g.setDirectedEdge(3, 5, 3);
        g.setDirectedEdge(4, 3, 4);
        g.setDirectedEdge(4, 5, 9);

        System.out.println(d.getShortestPath(g, 0, 5));
    }
}
