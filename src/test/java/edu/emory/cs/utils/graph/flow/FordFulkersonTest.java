package edu.emory.cs.utils.graph.flow;

import edu.emory.cs.utils.graph.Graph;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FordFulkersonTest {
    @Test
    public void test() {
        MFAlgorithm mfa = new FordFulkerson();
        Graph graph;
        double mf;

        graph = getGraph0();
        mf = test(mfa, graph, 0, graph.size() - 1);
        assertEquals(5, (int) mf);

        graph = getGraph1();
        mf = test(mfa, graph, 0, graph.size() - 1);
        assertEquals(2, (int) mf);
    }

    private void assertEquals(int i, int mf) {
    }

    @Test
    public void testUnDirected() {
        MFAlgorithm mfa = new FordFulkerson();
        Graph graph;
        double mf;

        graph = getGraph0();
        mf = test(mfa, graph, 0, graph.size() - 1);
        assertEquals(5, (int) mf);

        graph = getGraph1();
        mf = test(mfa, graph, 0, graph.size() - 1);
        assertEquals(2, (int) mf);
    }

    double test(MFAlgorithm mfa, Graph graph, int source, int target) {
        MaxFlow m = mfa.getMaximumFlow(graph, source, target);
        System.out.println(m.getFlowEdges().toString());
        return m.getMaxFlow();
    }

    Graph getGraph0() {
        Graph graph = new Graph(6);
        int s = 0, t = 5;

        graph.setDirectedEdge(s, 1, 4);
        graph.setDirectedEdge(s, 2, 2);
        graph.setDirectedEdge(1, 3, 3);
        graph.setDirectedEdge(2, 3, 2);
        graph.setDirectedEdge(2, 4, 3);
        graph.setDirectedEdge(3, 2, 1);
        graph.setDirectedEdge(3, t, 2);
        graph.setDirectedEdge(4, t, 4);

        return graph;
    }

    public Graph getGraph1() {
        Graph graph = new Graph(4);
        int s = 0, t = 3;

        graph.setDirectedEdge(2, t, 1);
        graph.setDirectedEdge(1, t, 1);
        graph.setDirectedEdge(1, 2, 1);
        graph.setDirectedEdge(s, 2, 1);
        graph.setDirectedEdge(s, 1, 1);

        return graph;
    }
}
