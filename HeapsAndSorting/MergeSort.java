//Written by jam2350
public class MergeSort {
	//a method that sorts an array using non-recursive bottom-up mergesort
	public static <T extends Comparable<T>> void sort(T[] a){
		@SuppressWarnings("unchecked")
		T [] tmpArray = (T[]) new Comparable[a.length];
		//subArray to be sorted
		for (int i = 1; i<= a.length / 2; i *= 2){
			for (int j = i; j < a.length; j += 2*i) {
				merge(a, tmpArray, j-i, j, Math.min((j+i-1), (a.length-1)));
				//iteratively merge larger and larger chunks or subArrays that exist
				//within the array
			}
		}
	}
	private static <T extends Comparable<T>> void merge( T[] a, T[] tmpArray, int aCtr, int bCtr, int rightEnd ) {
	    int leftEnd = bCtr - 1;
	    int tmpPos = aCtr;
	    int numElements = rightEnd - aCtr + 1;
	// Main loop
	    while( aCtr <= leftEnd && bCtr <= rightEnd )
	        if( a[ aCtr ].compareTo( a[ bCtr ] ) <= 0 )
	            tmpArray[ tmpPos++ ] = a[ aCtr++ ];
	        else
	        	tmpArray[ tmpPos++ ] = a[ bCtr++ ];
	    while( aCtr <= leftEnd ) // Copy rest of first half
	    	tmpArray[ tmpPos++ ] = a[ aCtr++ ];
	    while( bCtr <= rightEnd ) // Copy rest of right half
	        tmpArray[ tmpPos++ ] = a[ bCtr++ ];
	    // Copy tmpArray back
	    for( int i = 0; i < numElements; i++, rightEnd-- )
	        a[ rightEnd ] = tmpArray[ rightEnd ];
	}
	public static void main (String[] args){
		Integer [] test = new Integer [8];
		int j = 0;
		for (int i=7; i>=0; i--){
			test[j]=i;
			j++;
		}
		for (int element : test) {
			System.out.print(element + " ");
		}
		MergeSort.sort(test);
		System.out.println(" ");
		for (int element : test) {
			System.out.print(element + " ");
		}
	}
}
