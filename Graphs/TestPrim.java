import java.io.FileNotFoundException;


public class TestPrim {
	public static void main(String [] args){
		try {
			Graph g = MapReader.readGraph(args[0], args[1]);
			Graph shorty = g.getMinimumSpanningTree(args[2]);
			shorty.printAdjacencyList();
			DisplayGraph display = new DisplayGraph(shorty);
		    display.setVisible(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
