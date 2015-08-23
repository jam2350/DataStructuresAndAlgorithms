import java.io.FileNotFoundException;


public class TestDijkstra {
	public static void main(String [] args){
		try {
			Graph g = MapReader.readGraph(args[0], args[1]);
			Graph shorty = g.getWeightedShortestPath(args[2], args[3]);
			shorty.printAdjacencyList();
			DisplayGraph display = new DisplayGraph(shorty);
		    display.setVisible(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	//New  York and Las Vegas: different paths using unWeightedPath with BFS than with WeightedPath using Dijkstra's algorithm
	//VEGAS BABY!!!!

}
