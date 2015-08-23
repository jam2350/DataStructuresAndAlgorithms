import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
public class ReverseIndex {
	public static Map<Integer,LinkedList<String>> reverse(Map<String,LinkedList<Integer>> map){
		Map<Integer,LinkedList<String>> reverseMap = new TreeMap<>();
		Set <String> keys = map.keySet();
		for (String element : keys){
			LinkedList<Integer> lineNumbers = new LinkedList<>();
			lineNumbers = map.get(element);
			for (Integer line : lineNumbers){
				if (reverseMap.containsKey(line)==true){
					//if number already exists in map, add word to list of words associated with line
					LinkedList<String> words = reverseMap.get(line);
					words.add(element);
					reverseMap.put(line, words); 
				}
				else {
					//if the number is not in the map, make a new list to store all words associated with line
					LinkedList<String> firstWord = new LinkedList<>();
					firstWord.add(element);
					reverseMap.put(line, firstWord);
				}

			}
		}
		return reverseMap;
		
	}
	public static void printReverseMap(Map<Integer,LinkedList<String>> reverseMap){
		Set <Integer> keys = reverseMap.keySet();
		//create a set of keys
		for (Integer element : keys) {
			//print each key with its associated values
			System.out.println(element + ": " + reverseMap.get(element));
		}
	}
	public static void main(String[] args) {
		File myFile = new File(args[0]);
		CreateIndex myIndexer;
		try {
			myIndexer = new CreateIndex(myFile);
			ReverseIndex.printReverseMap(ReverseIndex.reverse(myIndexer.fileMap));
		} catch (FileNotFoundException e) {
			System.out.print("File Not Found");
		}
	}


}
