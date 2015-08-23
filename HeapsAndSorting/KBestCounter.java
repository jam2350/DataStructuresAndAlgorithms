//written by jam2350
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

public class KBestCounter<T extends Comparable<T>> {
	private int amount;
	//"k" amount of numbers
	private Heap <T> maximums;
	//heap storing maximums seen so far
	public KBestCounter (int k){
		maximums = new Heap<>(k);
		amount = k;	
	}
	//method to analyze whether to add an element into heap of maximums or not
	public void count(T x) {
		if (maximums.currentSize < amount) {
			//if heap isn't completely filled, insert x
			maximums.insert(x);
		}
		//O(logk)
		else if(maximums.findMin().compareTo(x) < 0){
			//if min of heap is less than current number, replace with current number
			maximums.deleteMin();
			maximums.insert(x);
		}
		//O(logk) to insert an element in to a heap
	}
	
	//method to return heap in a sorted list
	public List<T> kbest() {
		@SuppressWarnings("unchecked")
		T[] sorted = (T []) new Comparable[amount];
		//array to store sorted heap
		List<T> listSort = new LinkedList <T> ();
		for (int i =0; i< amount; i++){
			sorted[(amount - 1) - i] = maximums.deleteMin();
			//fill array with minimums from back to front of array 
		}
		//O(k) because loop iterates across array of length k
		for (T element: sorted){
			listSort.add(element);
			//transfer elements of array to list
		}
		//O(k) because iterates through array of length k
		for (T element : listSort){
			maximums.insert(element);
			//re-build the heap of maximums
			//O(logk) to insert an element into heap
		}
		//O(logk * k bc loop runs k times) = O(klogk)
		return listSort;
	}
	
	public static void main(String[] args) {

		  List<Integer> list = new LinkedList<>();

		  for (int i = 1000; i > 0; i--) {
		    list.add(i);
		  }

		  Iterator<Integer> stream = list.iterator();

		  int k = 10;
		  KBestCounter<Integer> counter = new KBestCounter<>(k);
		  for (int i = 0; i < 100; i++) {
		    counter.count(stream.next());
		  }
		  List<Integer> kbest = counter.kbest();
		  // print k largest after 100 elements
		  System.out.println(kbest);
		  for (int i = 0; i < 100; i++) {
		    counter.count(stream.next());
		  }
		  kbest = counter.kbest();
		  // print k largest after 200 elements
		  System.out.println(kbest);
		}

}
