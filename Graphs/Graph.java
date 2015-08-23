import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;

public class Graph {

  // Keep a fast index to nodes in the map
  protected Map<String, Vertex> vertices;

  /**
   * Construct an empty Graph.
   */
  public Graph() {
    vertices = new HashMap<String, Vertex>();
  }

  public void addVertex(String name) {
    Vertex v = new Vertex(name);
    addVertex(v);
  }

  public void addVertex(Vertex v) {
    if (vertices.containsKey(v.name))
      throw new IllegalArgumentException(
          "Cannot create new vertex with existing name.");
    vertices.put(v.name, v);
  }

  public Collection<Vertex> getVertices() {
    return vertices.values();
  }

  public Vertex getVertex(String s) {
    return vertices.get(s);
  }

  /**
   * Add a new edge from u to v. Create new nodes if these nodes don't exist
   * yet. This method permits adding multiple edges between the same nodes.
   * 
   * @param u
   *          the source vertex.
   * @param w
   *          the target vertex.
   */
  public void addEdge(String nameU, String nameV, Double cost) {
    if (!vertices.containsKey(nameU))
      addVertex(nameU);
    if (!vertices.containsKey(nameV))
      addVertex(nameV);
    Vertex sourceVertex = vertices.get(nameU);
    Vertex targetVertex = vertices.get(nameV);
    Edge newEdge = new Edge(sourceVertex, targetVertex, cost);
    sourceVertex.addEdge(newEdge);
  }

  /**
   * Add a new edge from u to v. Create new nodes if these nodes don't exist
   * yet. This method permits adding multiple edges between the same nodes.
   * 
   * @param u
   *          unique name of the first vertex.
   * @param w
   *          unique name of the second vertex.
   */
  public void addEdge(String nameU, String nameV) {
    addEdge(nameU, nameV, 1.0);
  }
  
  public void addUndirectedEdge(String s, String t, Double cost) {
	  addEdge(s, t, cost);
	  addEdge(t, s, cost);
  }
  
  public void computeEuclideanCosts() {
	  //a method to compute euclidea distance between vertices and assign it to the edge between the vertices
	  Collection<Vertex> vertices = this.getVertices();
	  for (Vertex v : vertices){
		  List<Edge> edge = v.getEdges();
		  for(Edge e : edge){
			  int sourceX = e.sourceVertex.posX;
			  int sourceY = e.sourceVertex.posY;
			  int targetX = e.targetVertex.posX;
			  int targetY = e.targetVertex.posY;
			  e.cost = Math.sqrt(Math.pow((sourceX-targetX), 2) + Math.pow((sourceY-targetY), 2));
		  }
	  }
  }
  
  public void doBfs(String s) {
	  //method to do breadth first search
	  Vertex start = getVertex(s);
	  Collection <Vertex> maVerts = this.getVertices();
	  for ( Vertex v : maVerts){
		  //set all vertices distance to infinity, visited to false, and prev to null
		  v.distance = Double.POSITIVE_INFINITY;
		  v.visited = false;
		  v.prev = null;
	  }
	  start.distance = 0.0;
	  Queue<Vertex> q = new LinkedList<>();
	  q.add(start);
	  while(!q.isEmpty()){
		  Vertex u = q.remove();
		  u.visited = true;
		  List<Edge> edgyWedgie = u.getEdges();
		  for (Edge e : edgyWedgie){
			  if(e.targetVertex.distance == Double.POSITIVE_INFINITY){
				  e.targetVertex.prev = u;
				  e.targetVertex.distance = u.distance + 1.0;
				  q.add(e.targetVertex);		  
			  }
		  }
	  }
	  
  }
  public Graph getUnweightedShortestPath(String s, String t) {
	  //method to get unweighted shortest path from source to target
	  this.doBfs(s);
	  Vertex target = this.getVertex(t);
	  Vertex source = this.getVertex(s);
	  Vertex current = target;
	  Graph gigi = new Graph();
	  Collection <Vertex> maVerts = this.getVertices();
	  for (Vertex v : maVerts){
		  //copy over all vertices from this graph to new graph
		  String vName = v.name;
		  int vX = v.posX;
		  int vY = v.posY;
		  Vertex gigiV = new Vertex(vName, vX, vY);
		  gigi.addVertex(gigiV);
	  }
	  while (!((current.name).equals(source.name))){
		  //add in edges of shortest unweighted path to connect the source and target
		  gigi.addEdge(current.prev.name, current.name);
		  current = current.prev;
	  }
	  return gigi;
  }
  public void doDijkstra(String s){
	  //method to implement Dijkstra's algorithm
	  this.computeEuclideanCosts();
	  //find weight of every edge
	  Collection <Vertex> maVerts = this.getVertices();
	  for ( Vertex v : maVerts){
		  v.distance = Double.POSITIVE_INFINITY;
		  v.visited = false;
		  v.prev = null;
	  }
	  Vertex start = this.getVertex(s);
	  start.distance = 0.0;
	  start.visited = true;
	  PriorityQueue<Vertex> verts = new PriorityQueue<>();
	  verts.add(start);
	  while (!(verts.isEmpty())) {
		  Vertex u = verts.poll();
		  u.visited = true;
		  List<Edge> edgyWedgie = u.getEdges();
		  for (Edge e : edgyWedgie){
			  if(e.targetVertex.visited != true){
				  if((e.cost + u.distance) < e.targetVertex.distance){
					  e.targetVertex.distance = e.cost + u.distance;
					  e.targetVertex.prev = u;
				  }
				  verts.add(e.targetVertex);
			  }
		  }
	  }
  }
  public Graph getWeightedShortestPath(String s, String t){
	  //graph of weighted shortest path using Dijkstra's algorithm
	  this.doDijkstra(s);
	  Vertex target = this.getVertex(t);
	  Vertex source = this.getVertex(s);
	  Vertex current = target;
	  Graph gigi = new Graph();
	  Collection <Vertex> maVerts = this.getVertices();
	  for (Vertex v : maVerts){
		  String vName = v.name;
		  int vX = v.posX;
		  int vY = v.posY;
		  Vertex gigiV = new Vertex(vName, vX, vY);
		  gigi.addVertex(gigiV);
	  }
	  while (!((current.name).equals(source.name))){
		  //add in edges of paths with appropriate weight that are computed by euclidean
		  gigi.addEdge(current.prev.name, current.name, Math.sqrt(Math.pow((current.prev.posX-current.posX), 2) + Math.pow((current.prev.posY-current.posY), 2)));
		  if ((current.prev.name).equals(source.name)) {
			  gigi.addEdge(source.name,current.name, Math.sqrt(Math.pow((source.posX-current.posX), 2) + Math.pow((source.posY-current.posY), 2)));
		  }
		  current = current.prev;
	  }
	  return gigi;
  }
  public void doPrim(String s){
	  //method that does Prim's algortihm
	  this.computeEuclideanCosts();
	  Collection <Vertex> maVerts = this.getVertices();
	  for ( Vertex v : maVerts){
		  v.distance = Double.POSITIVE_INFINITY;
		  v.visited = false;
		  v.prev = null;
	  }
	  Vertex start = this.getVertex(s);
	  start.distance = 0.0;
	  start.visited = true;
	  PriorityQueue<Vertex> verts = new PriorityQueue<>();
	  verts.add(start);
	  while (!(verts.isEmpty())) {
		  Vertex u = verts.poll();
		  u.visited = true;
		  List<Edge> edgyWedgie = u.getEdges();
		  for (Edge e : edgyWedgie){
			  if(e.targetVertex.visited != true){
				  if(e.cost < e.targetVertex.distance){
					  e.targetVertex.distance =  e.cost;
					  e.targetVertex.prev = u;
				  }
				  verts.add(e.targetVertex);
			  }
		  }
	  }
	  
  }
  public Graph getMinimumSpanningTree(String s){
	  //method that makes a graph of minimum spanning tree
	  this.doPrim(s);
	  Graph gigi = new Graph();
	  Collection <Vertex> maVerts = this.getVertices();
	  for (Vertex v : maVerts){
		  String vName = v.name;
		  int vX = v.posX;
		  int vY = v.posY;
		  Vertex gigiV = new Vertex(vName, vX, vY);
		  gigi.addVertex(gigiV);
	  }
	  for (Vertex v : maVerts){
		  if(v.prev!=null){
			  //if edge between vertex and another add in the edge
			  gigi.addEdge(v.prev.name, v.name, Math.sqrt(Math.pow((v.prev.posX-v.posX), 2) + Math.pow((v.prev.posY-v.posY), 2)));
		  }
	  }
	  return gigi;
  }
  

  /**
     * 
     */
  public void printAdjacencyList() {
    for (String u : vertices.keySet()) {
      StringBuilder sb = new StringBuilder();
      sb.append(u);
      sb.append(" -> [ ");
      for (Edge e : vertices.get(u).getEdges()) {
        sb.append(e.targetVertex.name);
        sb.append("(");
        sb.append(e.cost);
        sb.append(") ");
      }
      sb.append("]");
      System.out.println(sb.toString());
    }
  }

  public static void main(String[] args) {
    Graph g = new Graph();
    g.addVertex(new Vertex("v0", 0, 0));
    g.addVertex(new Vertex("v1", 0, 1));
    g.addVertex(new Vertex("v2", 1, 0));
    g.addVertex(new Vertex("v3", 1, 1));

    g.addEdge("v0", "v1");
    g.addEdge("v1", "v2");
    g.addEdge("v2", "v3");
    g.addEdge("v3", "v0");
    g.addEdge("v0", "v2");
    g.addEdge("v1", "v3");

    g.printAdjacencyList();

    DisplayGraph display = new DisplayGraph(g);
    display.setVisible(true);
  }

}
