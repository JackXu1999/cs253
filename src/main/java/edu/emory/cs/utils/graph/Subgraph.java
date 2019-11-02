package edu.emory.cs.utils.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subgraph
{
    private List<Edge> l_edges;
    private Set<Integer> s_vertices;

    public Subgraph()
    {
        l_edges    = new ArrayList<>();
        s_vertices = new HashSet<>();
    }

    public Subgraph(Subgraph graph)
    {
        l_edges    = new ArrayList<>(graph.getEdges());
        s_vertices = new HashSet<>(graph.getVertices());
    }

    public List<Edge> getEdges()
    {
        return l_edges;
    }

    public Set<Integer> getVertices()
    {
        return s_vertices;
    }

    public void addEdge(Edge edge)
    {
        l_edges.add(edge);
        s_vertices.add(edge.getSource());
        s_vertices.add(edge.getTarget());
    }

    public boolean contains(int vertex)
    {
        return s_vertices.contains(vertex);
    }
}