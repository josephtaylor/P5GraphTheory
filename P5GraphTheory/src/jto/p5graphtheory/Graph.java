package jto.p5graphtheory;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;
import processing.core.PApplet;
import java.util.TreeSet;

public class Graph
{
    private TreeSet<Vertex> vertices;
    private TreeSet<Edge> edges;

    public Graph()
    {
        edges = new TreeSet<Edge>();
        vertices = new TreeSet<Vertex>();
    }

    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    public void removeVertex(Vertex v) {
        vertices.remove(v);
        Iterator it = edges.iterator();
        while(it.hasNext()) {
            Edge nextEdge = (Edge) it.next();
            if(nextEdge.isIncidentTo(v)) {
                it.remove();
            }
        }
    }

    public void clearVertices() {
        vertices.clear();
        edges.clear();
    }

    public boolean containsVertex(Vertex v) {
        if(vertices.contains(v)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void addEdge(Vertex a, Vertex b) {
        Edge edge = new Edge(a, b);
        edges.add(edge);
        a.addIncidentEdge(edge);
        b.addIncidentEdge(edge);
        this.addVertex(a);
        this.addVertex(b);
    }

    public void addEdge(Edge e) {
        edges.add(e);
        this.addVertex(e.getVertexA());
        this.addVertex(e.getVertexB());
    }

    public void removeEdge(Vertex a, Vertex b) {
        edges.remove(new Edge(a, b));
    }

    public void removeEdge(Edge e) {
        edges.remove(e);
        for(Vertex v : vertices) {
            if(v.equals(e.getVertexA()) ||
                v.equals(e.getVertexB())) {
                v.removeIncidentEdge(e);
            }
        }
    }

    public void clearEdges()
    {
        edges.clear();
    }

    public boolean containsEdge(Edge e) {
        if(edges.contains(e)) {
            return true;
        }
        else {
            return false;
        }
    }

    public TreeSet<Vertex> getVertices()
    {
        return vertices;
    }

    public TreeSet<Edge> getEdges()
    {
        return edges;
    }

    /**
     * This will generate a complete <code>Graph</code> with the specified
     * number of vertices that are randomly distributed so that the coordinates
     * of each vertex lie somewhere on the canvas of the parent
     * <code>PApplet</code>.
     *
     * @param numberOfVertices The number of vertices.
     * @param parent the <code>PApplet</code> that is creating this
     * <code>Graph</code>.
     *
     * @return A random complete <code>Graph</code>.
     *
     */
    public static Graph randomCompleteGraph(int numberOfVertices,
        PApplet parent) {

        Graph graph = new Graph();

        for(int i = 0; i < numberOfVertices; i++) {
            graph.addVertex(new Vertex(
                parent.random(parent.width), parent.random(parent.height)));
        }

        for(Vertex a : graph.getVertices()) {
            for(Vertex b : graph.getVertices()) {
                if(!a.equals(b)) {
                    graph.addEdge(a, b);
                }
            }
        }

        return graph;
    }

    /**
     * This is an implementation of Kruskal's Algorithm for finding the minimal
     * spanning tree of a graph.</br>
     * This will work for disconnected graphs, but it will return a Graph
     * which contains multiple distinct trees.
     *
     * @param g The source <code>Graph</code> for the tree.
     * @return The minimal spanning tree.
     */
    public static Graph minimalSpanningTree(Graph g) {
        Graph spanningTree = new Graph();

        TreeSet<Edge> edges = g.getEdges();
        TreeSet<Vertex> vertices = g.getVertices();
        DisjointSet<Vertex> vertexSet = new DisjointSet<Vertex>();

        for(Vertex v : vertices) {
            vertexSet.makeSet(v);
        }
        if(edges.size() > 0) {
            Object[] edgeArray = edges.toArray();
            Edge firstEdge = (Edge) edgeArray[0];

            spanningTree.addEdge(firstEdge);
            vertexSet.union(firstEdge.getVertexA(), firstEdge.getVertexB());

            for(int i = 1; i < edgeArray.length; i++) {
                Edge edge = (Edge) edgeArray[i];
                if(vertexSet.find(edge.getVertexA()).contains(edge.getVertexB())) {
                    //This edge would create a cycle.
                }
                else {
                    spanningTree.addEdge(edge);

                    vertexSet.union(edge.getVertexA(), edge.getVertexB());
                }
            }
        }
        return spanningTree;
    }

    /**
     * This is an implementation of Kruskal's Algorithm for finding the minimal
     * spanning tree for the given collection of Vertex objects.</br></br>
     *
     * This will return a minimal spanning tree for the complete graph that
     * contains these vertices.
     *
     * @param vertices A <code>Collection</code> of <code>Vertex</code>
     * @return The minimal spanning tree.
     */
    public static Graph minimalSpanningTree(Collection<Vertex> vertices) {
        Graph source = new Graph();
        for(Vertex v : vertices) {
            source.addVertex(v);
        }
        for(Vertex a : source.getVertices()) {
            for(Vertex b : source.getVertices()) {
                source.addEdge(a, b);
            }
        }
        return minimalSpanningTree(source);
    }

    /**
     * This is a breadth-first algorithm for finding a spanning
     * tree of the given <code>Graph</code> starting from the specified
     * <code>Vertex</code>. The resulting tree contains all the vertices
     * in the original <code>Graph</code>.</br></br>
     *
     * If this method is passed a complete graph, the spanning tree that
     * results will just be a Graph with Edges connecting the initial
     * Vertex to the rest of the Vertices in the source Graph.
     *
     * @param graph The source <code>Graph</code>.
     * @param initialVertex The initial <code>Vertex</code>.
     *
     * @return A spanning tree containing the vertices of the source Graph.
     */
    @SuppressWarnings("unchecked")
    public static Graph spanningTree(Graph graph, Vertex initialVertex) {

        Graph spanningTree = new Graph();
        Queue<Vertex> vertexQueue = new LinkedList<Vertex>();

        vertexQueue.offer(initialVertex);
        spanningTree.addVertex(initialVertex);

        while(!vertexQueue.isEmpty()) {
            Vertex v = vertexQueue.remove();

            TreeSet<Edge> incidentEdges = v.getIncidentEdges();
            for(Edge edge : incidentEdges) {
                if(edge.getVertexA().equals(v)) {
                    if(!spanningTree.getVertices().contains(edge.getVertexB())) {
                        spanningTree.addEdge(edge);
                        vertexQueue.offer(edge.getVertexA());
                    }
                }
                else if(edge.getVertexB().equals(v)) {
                    if(!spanningTree.getVertices().contains(edge.getVertexA())) {
                        spanningTree.addEdge(edge);
                        vertexQueue.offer(edge.getVertexB());
                    }
                }
            }
        }

        return spanningTree;
    }

    public static Graph chordalGraph(Graph graph) {
        TreeSet<Vertex> vertices = graph.getVertices();
        if(vertices.size() > 0) {
            for(Vertex v : vertices) {
                while(v.getDegree() > 3) {
                    v.removeIncidentEdge((Edge) v.getIncidentEdges().pollLast());
                }
            }
        }
        Graph result = new Graph();
        for(Vertex v : vertices) {
            for(Object e : v.getIncidentEdges()) {
                Edge edge = (Edge) e;
                result.addEdge(edge);
            }
        }
        return result;
    }
}


