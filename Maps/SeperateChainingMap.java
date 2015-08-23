import java.util.LinkedList;
public class SeperateChainingMap <K extends Comparable <K>,V> implements Map<K,V> {
	private LinkedList <Pair<K,V>> [] theTable;
	private int cellsOccupied;
	@SuppressWarnings("unchecked")
	public SeperateChainingMap(){
		theTable = (LinkedList<Pair<K, V>>[]) new LinkedList[10];
		cellsOccupied = 0;
	}
	//method that checks if load factor exceeds 1
	//and if so copies over array into an array of double the size
	@SuppressWarnings("unchecked")
	private void ensureCapacity(){
		int loadFactor= cellsOccupied/(theTable.length);
		if (loadFactor >= 1 ) {
			LinkedList <Pair<K,V>> [] newTable =(LinkedList<Pair<K, V>>[]) new LinkedList [theTable.length * 2];
			for(int i=0; i< theTable.length; i++) { 
				newTable[i] = theTable[i];
			}
			theTable = newTable;
		}
		
	}

	//method to add a pair to the SeperateChainingMap
	public void put(K key, V value) {
		ensureCapacity();
		Pair<K,V>thePair = new Pair<K,V>(key,value);
		// the pair to be added to the map
		if (theTable[key.hashCode() % theTable.length] == null) { 
			//if no keys have hashed to this index in theTable, make a linked list of pairs
			//at the index and add the pair to the linked list
			LinkedList<Pair<K,V>> valueList = new LinkedList<Pair<K,V>>();
			valueList.add(thePair);
			theTable[key.hashCode() % theTable.length]= valueList;
		}
		else {
			//other keys have hashed to this index and are in a linked list at this index in the array
			boolean keyExists = false;
			LinkedList<Pair<K,V>> bucketList = theTable[key.hashCode() % theTable.length];
			for (Pair <K,V> element : bucketList){
				if ((element.key).equals(key)){
					//the same key already exists in the array
					element.value = value;
					//replace the old value associated with this key with the new value being inserted
					keyExists = true;
				}
				if (keyExists != true){
					//the same key doesn't already exist in the array, add the pair to the list at that index
					theTable[key.hashCode() % theTable.length].add(thePair);
				}
			}
		}
		cellsOccupied++;
	}

	//method to get the value associated with a key
	public V get(K key) {
		if (theTable[key.hashCode() % theTable.length] == null){
			//the key doesn't exist in the map
			return null;
		}
		LinkedList<Pair<K,V>> myList = theTable[key.hashCode() % theTable.length];
		for(Pair<K,V> elements: myList){
			if(key.equals(elements.key)){
				//the key your searching for
				return elements.value;
			}
		}
		return null;
		//the key doesn't exist in the map
	}






