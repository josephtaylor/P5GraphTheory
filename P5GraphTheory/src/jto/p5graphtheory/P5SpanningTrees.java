package jto.p5graphtheory;

import java.util.ArrayList;
import java.util.TreeSet;
import processing.core.PApplet;

public class P5SpanningTrees extends PApplet
{
    private Graph graph;

    public void setup() {
        size(1000, 700);
        background(255);

        graph = new Graph();
        strokeWeight(1);
        smooth();
        frameRate(100);

    }

    public void draw() {
        background(255);
        if(graph.getVertices().size() > 0) {
            Graph triangulated = Graph.triangulatedGraph(graph);
            for(Edge e : triangulated.getEdges()) {
                stroke(0,0,0,100);
                line(e.getVertexA().getX(), e.getVertexA().getY(),
                    e.getVertexB().getX(), e.getVertexB().getY());
            }
            for(Vertex v : triangulated.getVertices()) {
                fill(255);
                ellipse(v.getX(), v.getY(), 5, 5);
            }
        }
    }

    public void mousePressed()
    {
        if(mouseButton == LEFT) {
            Vertex v = new Vertex(mouseX, mouseY);
            System.out.println(v.getX() + " " + v.getY());
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
        else {
            graph.clearVertices();
        }
    }

    public static void main(String args[])
    {
        PApplet.main(new String[] {"jto.p5graphtheory.P5SpanningTrees"});
    }
}
