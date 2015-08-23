import java.io.FileNotFoundException;


public class TestBfs {
	public static void main(String [] args){
		try {
			Graph g = MapReader.readGraph(args[0], args[1]);
			Graph shorty = g.getUnweightedShortestPath(args[2], args[3]);
			DisplayGraph display = new DisplayGraph(shorty);
		    display.setVisible(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
