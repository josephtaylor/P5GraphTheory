package jto.p5graphtheory;

import processing.core.PVector;

public class Triangle {

  private Vertex vertexA;
  private Vertex vertexB;
  private Vertex vertexC;

  public Triangle() {
      this(null, null, null);
  }

  public Triangle(Vertex vertexA, Vertex vertexB, Vertex vertexC) {
      this.vertexA = vertexA;
      this.vertexB = vertexB;
      this.vertexC = vertexC;
  }

  public Vertex getVertexA() {
      return vertexA;
  }

  public void setVertexA(Vertex vertex) {
      vertexA = new Vertex(vertex.getPVector());
  }

  public Vertex getVertexB() {
      return vertexB;
  }

  public void setVertexB(Vertex vertex) {
      vertexB = new Vertex(vertex.getPVector());
  }

  public Vertex getVertexC() {
      return vertexC;
  }

  public void setVertexC(Vertex vertex) {
      vertexC = new Vertex(vertex.getPVector());
  }



  public boolean sharesVertex(Triangle other) {
    return vertexA.equals(other.getVertexA()) ||
        vertexA.equals(other.getVertexB()) ||
        vertexA.equals(other.getVertexC()) ||
        vertexB.equals(other.getVertexA()) ||
        vertexB.equals(other.getVertexB()) ||
        vertexB.equals(other.getVertexC()) ||
        vertexC.equals(other.getVertexA()) ||
        vertexC.equals(other.getVertexB()) ||
        vertexC.equals(other.getVertexC());
  }

}
