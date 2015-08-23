import java.util.LinkedList;
public class PrefixTree {

	    public Node root;

	    /**
	     * Represent a binary subtree.
	     */
	     public class Node{

	        public char        data;  
	        public LinkedList<Node> children;  
	        public boolean lastLetter;
	        
	    
	        /**
	         * Construct a new binary node. 
	         */
	        public Node( char theData, LinkedList<Node> theChildren) {
	            data    = theData; 
	            theChildren    = children;
	            lastLetter = false;
	        }
	        public Node( char theData, LinkedList<Node> theChildren, boolean last) {
	            data    = theData; 
	            theChildren    = children;
	            lastLetter = last;
	        }
	        public Node(char theData) {
	            data    = theData; 
	            children    = new LinkedList<>();
	            lastLetter = false;
	        }
	        public Node(){
	        	data = ' ';
	        	children = new LinkedList<>();
	        	lastLetter = false;
	        }
	        
	        

	     }
	 
	   
	    public PrefixTree() {
	        root = new Node ();
	    }
	    public PrefixTree(Node startNode) {
	        root = startNode;
	    }
	    public void addWord(String word){
        	char [] letters = word.toCharArray();
        	Node currentNode = this.root;
        	for(int i=0; i<letters.length; i++){
        		int j = 0;
        		boolean found = false;
        		while(!found && j < currentNode.children.size()){
        			Node thisLetter = currentNode.children.get(j);
        			if(thisLetter.data == letters[i]){
        				found = true;
        				currentNode = thisLetter;
        			}
        			else {
        				j++;
        			}
        		}
        		if(!found){
        			this.addNewWord(word.substring(i), currentNode);
        			return;
        		}
        	}
        	currentNode.lastLetter = true;
        }
        public void addNewWord(String newWord, Node currentNode){
        	for (int i= 0; i<newWord.length(); i++){
        		Node nextLetter = new Node(newWord.charAt(i));
        		currentNode.children.add(nextLetter);
        		currentNode = nextLetter;
        	}
        	currentNode.lastLetter = true;
        }
        public PrefixTree findPrefix(String prefix){
        	char [] theLetters = prefix.toCharArray();
        	Node currentNode = this.root;
        	for(int i=0; i<theLetters.length; i++){
            	int j = 0;
            	boolean found = false;
            	while(!found && j < currentNode.children.size()){
            		Node thisLetter = currentNode.children.get(j);
            		if(thisLetter.data == theLetters[i]){
            			found = true;
            			currentNode = thisLetter;
            		}
            		else {
            			j++;
            		}
            	}
            	if(!found){
            		return null;
            	}
        	}
        	PrefixTree myTree = new PrefixTree(currentNode);
        	return myTree;
        }
        public boolean contains(String word){
        	PrefixTree myTree2 = findPrefix(word);
        	if(myTree2 != null && myTree2.root.lastLetter){
        		return true;
        	}
        	else{
        		return false;
        	}
        }
        public LinkedList<String> getList(LinkedList<String> theList){
        	LinkedList<String> maWords = theList;
        	return maWords;
        }
    }
        

	   

	   

	   
	


