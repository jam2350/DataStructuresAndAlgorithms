/**
 * LinkedList class implements a doubly-linked list.
 * Adapted from Weiss, Data sSructures and Algorithm Analysis in Java. 3rd ed. 
 * http://users.cis.fiu.edu/~weiss/dsaajava3/code/MyLinkedList.java
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>{

    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType> {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n ) {
            data = d; prev = p; next = n;
        }
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }

    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList() {
        doClear();
    }
    
    /**
     * Change the size of this collection to zero by 
     *  initializing the beginning and end marker. 
     */
    public void doClear() {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;
        
        theSize = 0;
        modCount++;
    }
    
    /**
     * @return the number of items in this collection.
     */
    public int size() {
        return theSize;
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
   
    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */    
    private Node<AnyType> getNode( int idx, int lower, int upper ) {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size() / 2 ) { // Search through list from the beginning
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        } else { // serch through the list from the end
            p = endMarker;
            for( int i = size(); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }

    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }


    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx ) {
        return getNode( idx ).data;
    }
          
    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal ) {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
 
    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */    
    private void addBefore( Node<AnyType> p, AnyType x ) {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
        modCount++;
    }   

    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add(int idx, AnyType x) {
        addBefore( getNode(idx, 0, size()), x );
    }

    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x ) {
        add( size( ), x );   
        return true;         
    }

    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p ) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        
        return p.data;
    }
    
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx ) {
        return remove( getNode( idx ) );
    }
    
    /**
     * Returns a String representation of this collection.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( ) {
        return new LinkedListIterator( );
    }
    
    public int indexOf (Object o) {
    	int i = 0;
    	//tracks the index of the object
    	int notInList = -1; 
    	LinkedListIterator iterator = new LinkedListIterator();
    	while (iterator.current.data.equals(o) != true && i < (size()-1)) {
    		//move along the linked list until the first occurrence of the object
    		iterator.next();
    		i++;
    	}
    	if (iterator.current.data.equals(o)) {
    		//if the object exists in the list
    		return i;
    	}
    	else {
    		//if the object is not in the list
    		return notInList;
    	}
    }
    
    public void reverse () {
    	for (int i = 0; i < (size()/2); i++) {
    		//go accross half the list swapping elements at opposite ends
    		AnyType temp = get (i);
    		//temporarily store element in first half of list
    		set (i, get(size() - 1 - i));
    		//store element from second half of list in first half of list
    		set (size() - 1 -i, temp);
    		//store element from first half in second half
    	}
    	
    	
    }
    public void removeDuplicates () {
    	for (int i = 0; i < size(); i++) {
    		//compare each element with remaining elements in list
    		for (int j=i+1; j < size(); j++)
    			if (get(i).equals(get(j))) {
    				//if two elements are the same, remove one
    				remove(j);
    				j--;		
    			}
    	}
    }
    
    //The running time of this method is O(N^2) because each for loop runs at most N time and they are nested loops so N*N = N^2. 
    //If the list is sorted, you can compare each element with the element next to it. 
    //If the two adjacent elements are equal, you remove one element and move on until you find an element that is not equal
    //to the previous one. Because you are traversing the list sequentially, the running time of this will be O(N). 
    
    public void interleave (MyLinkedList <AnyType> other) {
    	int listIdx = 0; //index of first list
    	int otherList = 0; //index of second list
    	while (listIdx<theSize) {
    		//traverse the first list while inserting elements of the other list
    		if (otherList<other.size()){
    			//traverse the second list
    			this.add(listIdx, other.getNode(otherList).data);
    			//insert the data from the current position of the second list into the current position of the first list  
    			otherList++;
    			//move over one in the second list
    		}
    		listIdx += 2;
    		//move over two so that the elements from the second list occur in every other position in the first list
    		
    	}
    	
    	while (otherList<other.size()) {
    		//if there are still more elements to be copied over in the second list
    		this.add(listIdx, other.getNode(otherList).data);
    		//add the element from the second list into the first list
    		otherList++;
    		listIdx++;
    		//move the position of both lists over one because only copying over from second list
    	}
    	
    		
    }
    
    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        
        public boolean hasNext( ) {
            return current != endMarker;
        }
        
        public AnyType next( ) {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        
        public void remove( ) {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;       
        }
    }
}

  
