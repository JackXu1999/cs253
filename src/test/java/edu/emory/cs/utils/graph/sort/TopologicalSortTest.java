package edu.emory.cs.utils.graph.sort;

import edu.emory.cs.utils.graph.Graph;
import org.junit.Test;

public class TopologicalSortTest {
    @Test
    public void test() {
//		Graph graph = new Graph(8);
//
//		graph.setDirectedEdge(0, 3, 1);
//		graph.setDirectedEdge(0, 4, 1);
//		graph.setDirectedEdge(1, 3, 1);
//		graph.setDirectedEdge(2, 4, 1);
//		graph.setDirectedEdge(3, 5, 1);
//		graph.setDirectedEdge(3, 6, 1);
//		graph.setDirectedEdge(3, 7, 1);
//		graph.setDirectedEdge(4, 6, 1);
//		graph.setDirectedEdge(2, 7, 1);
//
//		TopologicalSort s = new TopologicalSort();
//		System.out.println(s.sort(graph).toString());

        Graph graph = new Graph(5);

        graph.setDirectedEdge(0, 1, 1);
        graph.setDirectedEdge(0, 2, 1);
        graph.setDirectedEdge(2, 1, 1);
        graph.setDirectedEdge(2, 3, 1);
        graph.setDirectedEdge(3, 4, 1);
        graph.setDirectedEdge(4, 2, 1);

        System.out.println(graph.toString());
        System.out.println(graph.containsCycle());
    }
}
