import java.util.LinkedList;
import java.util.List;


public class Vertex implements Comparable<Vertex> {
  public String name;
  private List<Edge> adjacent;
  public int posX = 0;
  public int posY = 0;
  public boolean visited;
  public Vertex prev;
  public double distance;
  
  /**
   * Construct a new vertex containing an adjacency list.
   * 
   * @param vertexName
   *          a unique identifier for this vertex.
   * @param x
   *          the x coordinate for this vertex
   * @param y
   *          the y coordinate for this vertex
   */
  public Vertex(String vertexName, int x, int y) {
    name = vertexName;
    adjacent = new LinkedList<Edge>();
    posX = x;
    posY = y;
    visited = false;
    prev = null;
    //distances = new HashMap<>();
    distance = 0;
  }

  /**
   * Construct a new vertex containing an adjacency list.
   * 
   * @param vertexName
   *          a unique identifier for this vertex.
   */
  public Vertex(String vertexName) {
    this(vertexName, 0, 0);
  }

  /**
   * Retrieve the list of edges connected to this vertex.
   * 
   * @return a list of edges connected to this vertex.
   */
  public List<Edge> getEdges() {
    return adjacent;
  }

  /**
   * Connect an edge to this vertex.
   * 
   * @param e
   *          The new edge to connect to this vertex.
   */
  public void addEdge(Edge e) {
    adjacent.add(e);
  }

  public String toString() {
    return name;
  }

  @Override
	public int compareTo(Vertex v) {
		if (v.distance == this.distance){
			return 0;
		}
		else if(v.distance < this.distance){
			return 1;
		}
		else{
			return -1;
		}
	}

}
