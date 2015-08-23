public class Heap<T extends Comparable<T>> {
   
   private static final int DEFAULT_CAPACITY = 10;
   private T[] array; // array storing the heap
   public int currentSize; // curent number of elements in the heap
 
    /**
     * Construct an empty heap.
     * @param initial capacity of this heap. 
     */
   @SuppressWarnings("unchecked")
    public Heap( int capacity ) {
        currentSize = 0;
        array = (T[]) new Comparable[ capacity + 1];
    }

    /**
     * Construct an empty heap.
     */
    public Heap( ) {
        this( DEFAULT_CAPACITY );
    } 
   
 
    /** 
     * Make sure the array can fit a certain number of items. 
     * @param size the number of items.  
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity(int size) {
        if (size > array.length - 1) {
            T[] old = array;
            array = (T[]) new Comparable[old.length * 2 + 1];
            for (int i = 0; i < old.length; i++) 
               array[i] = old[i];
        }
    }

    /**
     * Insert into the heap, maintaining the heap order. 
     * Duplicates are allowed in this implementation.
     * @param x the item to insert.
     */
    public void insert( T x ) {
        ensureCapacity(currentSize+1);
 
        // Percolate up
        currentSize++;
        int hole = currentSize; // try to insert in last possible position.
        while (hole > 1 && x.compareTo(array[hole / 2]) < 0 ) { // True if x is larger than the parent of the current position
            array[hole] = array[hole / 2];
            hole = hole / 2;
        } 
        array[hole] = x;
    }
   
    public boolean isEmpty() {
        return currentSize == 0;
    }
 
    /**
     * Return the root of the heap (the min item), but do not remove it.
     */
    public T findMin() {
        if( isEmpty( ) )
            throw new ArrayIndexOutOfBoundsException( );
        return array[ 1 ];
    }
    

    /**
     * Assuming that the left and right subtree of i is a heap, 
     * Restore the heap order for the heap roote in i. 
     * @param the index from which we start percolate down.
     */ 
    public void percolateDown(int i) {
        int child;
        T tmp = array[i]; // the value at i

        boolean correctLocation = false;

        while (!correctLocation && i*2 <= currentSize) {
            child = i * 2; // left child

            // check if right child exists  
            if (child != currentSize && array[child + 1].compareTo(array[child]) < 0) 
                child ++; // if right child is smaller compare to right child instead
       
            // if tmp is greater than the smaller child we continue to percolate down.
            if (array[child].compareTo(tmp) < 0) { 
                array[i] = array[child]; // swap with smaller child
                i = child;
            } else 
                correctLocation = true;
        }
        array[i] = tmp;
    }

    /**
     * Return the root of the heap (the min item) and remove it, maintaining the heap order.
     */  
    public T deleteMin() {
        if( isEmpty( ) )
            throw new ArrayIndexOutOfBoundsException( );

        T minItem = findMin();      
      
        // Move the last item in the heap to the root.
        array[1] = array[currentSize];
        array[currentSize] = null;
        currentSize--; 
        // Call percolateDown on the root to restore heap order.
        percolateDown(1);
        return minItem;        
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i=0; i<array.length;i++) {
             sb.append(" ");
             sb.append(array[i]);
        }
        sb.append("]");
        return sb.toString();
    }

}