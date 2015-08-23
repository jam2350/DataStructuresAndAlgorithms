import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class MapReader {
	public MapReader(){
		
	}
	public static Graph readGraph(String vertexfile, String edgefile) throws FileNotFoundException{
		File vertices = new File(vertexfile);
		Scanner vertexScanner = new Scanner(vertices);
		File edges = new File(edgefile);
		Scanner edgeScanner = new Scanner(edges);
		Graph myGraph = new Graph();
		while(vertexScanner.hasNextLine()){
			@SuppressWarnings("resource")
			Scanner lineScanner = new Scanner(vertexScanner.nextLine()).useDelimiter(",");
			String vName = lineScanner.next();
			int vXPos = lineScanner.nextInt();
			int vYPos = lineScanner.nextInt();
			Vertex v = new Vertex(vName, vXPos, vYPos);
			myGraph.addVertex(v);
			lineScanner.close();
		}
		while(edgeScanner.hasNextLine()){
			@SuppressWarnings("resource")
			Scanner lineScanner = new Scanner(edgeScanner.nextLine()).useDelimiter(",");
			String city1 = lineScanner.next();
			String city2 = lineScanner.next();
			myGraph.addUndirectedEdge(city1, city2, 1.0);
			lineScanner.close();
		}
		vertexScanner.close();
		edgeScanner.close();
		return myGraph;
	}
	public static void main(String [] args){
		try {
			Graph g = MapReader.readGraph(args[0], args[1]);
			g.printAdjacencyList();
			DisplayGraph display = new DisplayGraph(g);
		    display.setVisible(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
