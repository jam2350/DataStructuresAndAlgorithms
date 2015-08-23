import java.util.Map;
import java.util.Set;
import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.FileNotFoundException;

public class CreateIndex {
	public Map<String, LinkedList<Integer>> fileMap;
	public CreateIndex(File input) throws FileNotFoundException{
		fileMap = createMap(input);	
		//make map of pairs of words as keys with lists of line numbers as values
	}
	//method to see which lines a word appears on
	public LinkedList<Integer> getIndex(String word) {
		return fileMap.get(word);
		
	}
	//method that makes a map out of a text file
	private Map <String, LinkedList<Integer>> createMap(File text) throws FileNotFoundException {
		Map<String, LinkedList<Integer>> myMap = new TreeMap<>();
		Scanner scanFile = new Scanner(text);
		int lineNumber = 0;
		while (scanFile.hasNextLine()){
			String lineScan = scanFile.nextLine();
			String [] words = lineScan.split(" ");
			//store each word in line in an array
			for (String element : words){
				if (myMap.containsKey(element)==true){
					//if word already exists in map, add line number to list of values associated with word
					LinkedList<Integer> indices = myMap.get(element);
					indices.add(lineNumber);
					myMap.put(element, indices); 
				}
				else {
					//if the word is not in the map, make a new list to store all values associated with the word
					LinkedList<Integer> index = new LinkedList<>();
					index.add(lineNumber);
					myMap.put(element, index);
				}
			}
			lineNumber++;
			//go to the next line of the file
		}
		scanFile.close();
		return myMap;
		
	}
	//method to print the map of words and line numbers
	public void printMap(){
		Set <String> keys = fileMap.keySet();
		//create a set of keys
		for (String element : keys) {
			//print each key with its associated values
			System.out.println(element + ": " + fileMap.get(element));
		}
	}
	

public static void main(String[] args) {
	File myFile = new File(args[0]);
	CreateIndex myIndexer;
	try {
		myIndexer = new CreateIndex(myFile);
		myIndexer.printMap();
	} catch (FileNotFoundException e) {
		System.out.print("File Not Found");
	}
}

}