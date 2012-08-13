package jto.p5graphtheory;

import java.util.TreeSet;
import processing.core.PApplet;

public class P5SpanningTrees extends PApplet
{
    private Graph graph;

    public void setup() {
        size(750, 750);
        background(255);

        graph = new Graph();
        strokeWeight(1);
        smooth();
        frameRate(100);

        for(int i = 0; i < 100; i++) {
            Vertex v = new Vertex(random(width), random(height));
            graph.addVertex(v);
            Object[] vertices = graph.getVertices().toArray();
            for(Object current : vertices) {
                if(!current.equals(v)) {
                    Edge e = new Edge(v, (Vertex) current);
                    graph.addEdge(e);
                    ((Vertex) current).addIncidentEdge(e);
                }
            }
        }
    }

    public void draw() {
        background(255);
        Graph spanningTree = Graph.chordalGraph(graph);
        TreeSet<Edge> edges = spanningTree.getEdges();
        for(Edge e : edges) {
            stroke(0,0,0,100);
            line(e.getVertexA().getX(), e.getVertexA().getY(),
                e.getVertexB().getX(), e.getVertexB().getY());
        }
    }

    public static void main(String args[])
    {
        PApplet.main(new String[] {"jto.p5graphtheory.P5SpanningTrees"});
    }
}
