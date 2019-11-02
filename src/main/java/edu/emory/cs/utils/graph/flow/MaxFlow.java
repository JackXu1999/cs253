package edu.emory.cs.utils.graph.flow;

import edu.emory.cs.utils.graph.Edge;
import edu.emory.cs.utils.graph.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxFlow {
    private Map<Edge, Double> m_flows;
    private double d_maxFlow;

    public MaxFlow(Graph graph) {
        init(graph);
    }

    public void init(Graph graph) {
        m_flows = new HashMap<>();
        d_maxFlow = 0;

        for (Edge edge : graph.getAllEdges())
            m_flows.put(edge, 0d);
    }

    //	============================== Getter ==============================
    public double getResidual(Edge edge) {
        return edge.getWeight() - m_flows.get(edge);
    }

    public double getMaxFlow() {
        return d_maxFlow;
    }

    public List<Edge> getFlowEdges() {
        List<Edge> edges = new ArrayList<>();
        double r;
        Edge e;

        for (Edge edge : m_flows.keySet()) {
            r = m_flows.get(edge);

            if (r > 0) {
                e = new Edge(edge.getSource(), edge.getTarget(), r);
                edges.add(e);
            }
        }

        return edges;
    }

    //	============================== Setter ==============================
    public void updateResidual(List<Edge> path, double flow) {
        for (Edge edge : path) updateResidual(edge, flow);
        d_maxFlow += flow;
    }

    public void updateResidual(Edge edge, double flow) {
        Double prev = m_flows.get(edge);
        if (prev == null) prev = 0d;
        m_flows.put(edge, prev + flow);
    }
}