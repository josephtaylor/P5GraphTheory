package jto.p5graphtheory;

import java.util.TreeSet;
import processing.core.PVector;

public class Vertex implements Comparable
{
    private PVector vector;
    private TreeSet<Edge> incidentEdges;
    private TreeSet<Vertex> adjacentVertices;
    private boolean visited;
    private int degree;

    public Vertex(PVector vector) {
        this.vector = new PVector();
        this.vector.set(vector);
        incidentEdges = new TreeSet<Edge>();
        adjacentVertices = new TreeSet<Vertex>();
        visited = false;
        degree = 0;
    }

    public Vertex(int x, int y, int z) {
        PVector localVector = new PVector(x, y, z);
        this.vector = new PVector();
        this.vector.set(localVector);
        incidentEdges = new TreeSet<Edge>();
        adjacentVertices = new TreeSet<Vertex>();
        visited = false;
        degree = 0;
    }

    public Vertex(int x, int y) {
        this(x, y, 0);
    }

    public Vertex(float x, float y, float z) {
        PVector localVector = new PVector(x, y, z);
        this.vector = new PVector();
        this.vector.set(localVector);
        incidentEdges = new TreeSet<Edge>();
        adjacentVertices = new TreeSet<Vertex>();
        visited = false;
        degree = 0;
    }

    public Vertex(float x, float y) {
        this(x, y, 0);
    }

    public void addIncidentEdge(Edge edge) {
        incidentEdges.add(edge);
        if(!edge.getVertexA().equals(this)) {
            adjacentVertices.add(edge.getVertexA());
            degree++;
        }
        else {
            adjacentVertices.add(edge.getVertexB());
            degree++;
        }

    }

    public void removeIncidentEdge(Edge edge) {
        boolean success = true;
        success &= incidentEdges.remove(edge);
        if(this.equals(edge.getVertexA())) {
            adjacentVertices.remove(edge.getVertexB());
            degree--;
        }
        else if(this.equals(edge.getVertexB())) {
            adjacentVertices.remove(edge.getVertexA());
            degree--;
        }

    }

    public TreeSet getAdjacentVertices()
    {
        return adjacentVertices;
    }

    public TreeSet getIncidentEdges()
    {
        return incidentEdges;
    }

    public PVector getPVector() {
        return vector;
    }

    public float getX()
    {
        return vector.x;
    }

    public float getY()
    {
        return vector.y;
    }

    public float getZ()
    {
        return vector.z;
    }

    public int getDegree() {
        return degree;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean hasBeenVisited() {
        return visited;
    }

    public boolean isAdjacentTo(Vertex v) {
        return adjacentVertices.contains(v);
    }

    public boolean isIncidentTo(Edge e) {
        return incidentEdges.contains(e);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Vertex)) {
            return false;
        }
        Vertex otherVertex = (Vertex) obj;
        Float thisX = vector.x;
        Float thisY = vector.y;
        Float otherX = otherVertex.getPVector().x;
        Float otherY = otherVertex.getPVector().y;
        return((thisX.equals(otherX)) && thisY.equals(otherY));
    }

    public int compareTo(Object o)
    {
        Vertex otherVertex = (Vertex) o;
        PVector origin = new PVector(0, 0, 0);
        float thisLength = Math.abs(vector.dist(origin));
        float otherLength = Math.abs(otherVertex.getPVector().dist(origin));

        if(thisLength < otherLength) {
            return -1;
        }
        else if(thisLength > otherLength) {
            return 1;
        }
        else {
            return 0;
        }

    }



}
