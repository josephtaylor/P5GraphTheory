package jto.p5graphtheory;

import processing.core.PImage;
import java.util.TreeSet;
import processing.core.PApplet;


public class P5GraphTheory extends PApplet {

    private Graph graph;
    private int current;
    private PImage stars;

	public void setup() {
	    size(1024, 768);
	    background(0);

	    graph = new Graph();
	    strokeWeight(1);
	    smooth();
	    frameRate(100);

	    stars = loadImage("stars.jpg");
	}

	public void draw() {
	    image(stars, 0, 0);
	    if(graph.getVertices().size() > 0) {
	        TreeSet<Vertex> vertices = graph.getVertices();
	        for(Vertex a : vertices) {
	            for(Vertex b : vertices) {
	                if(!a.equals(b)) {
	                    graph.addEdge(a, b);
	                }
	            }
	        }
	    }
	    if(graph.getEdges().size() > 0) {
	        Object[] objVertices = graph.getVertices().toArray();
	        Vertex start = (Vertex) objVertices[(int) Math.floor(Math.random() *
	            (objVertices.length - 1))];
	        Graph spanningTree = Graph.minimalSpanningTree(graph);
	        TreeSet<Edge> edges = spanningTree.getEdges();
	        if(edges.size() > 0) {
	            Object[] edgeArray = edges.toArray();
	            for(Object edge : edgeArray) {
	                Edge e = (Edge) edge;
	                stroke(255, 255, 255, 200);
	                line(e.getVertexA().getX(), e.getVertexA().getY(),
	                    e.getVertexB().getX(), e.getVertexB().getY());
	                current = (current + 1) % (edgeArray.length);
	            }
	        }
	    }
//	    TreeSet<Vertex> vertices = spanningTree.getVertices();
//	    if(vertices.size() > 0) {
//	        for(Vertex v : vertices) {
//	            fill(255);
//	            ellipse(v.getX(), v.getY(), 5, 5);
//	        }
//	    }

	    if(keyPressed && key == ' ') {
	        saveFrame("stars#####.jpg");
	    }

	    if(mousePressed && mouseButton == LEFT) {
	        graph.addVertex(new Vertex(mouseX, mouseY));
	    }
	    if(mousePressed && mouseButton == RIGHT) {
	        if(keyPressed && key == 'c') {
	            graph.clearEdges();
	            graph.clearVertices();
	        }
	        current = 0;
	        background(255);
	    }
	}

	public void mouseDragged() {
	    graph.addVertex(new Vertex(mouseX, mouseY));
	}

	public static void main(String args[])
	{
	    PApplet.main(new String[] {"jto.p5graphtheory.P5GraphTheory"});
	}
}
