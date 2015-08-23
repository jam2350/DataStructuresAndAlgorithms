import java.util.Stack;
public class TwoStackQueue<T> implements Queue<T> {
	private Stack<T> inbox;
	private Stack<T> outbox;
	
	public TwoStackQueue() {
		inbox = new Stack<T>();
		outbox = new Stack<T>();	
	}
	public void enqueue(T x) {
		inbox.push(x);
		//Time Complexity: O(1) because push() is a constant time operation
	}
	public T dequeue() {
		if (outbox.empty()) {
			while(inbox.empty()!=true){
				outbox.push(inbox.pop());
			}
			//Time Complexity: O(N) where N is number of letters in inbox. The while loop will run N times
			//And inside the while loop are two constant time operations (push and pop), O(1)+O(1)=O(2) and then that N times
			//is O(2N) which is linear, O(N).
		}
		return outbox.pop();
		
	}
	//This TwoStackQueue ensures that Quebert removes mail from outbox and when outbox is empty it fills it with inbox.
	//Thus when refilling outbox (with letters from inbox), the top of inbox, the most recent letters are now at the bottom
	//of outbox and the older letters which are at the bottom of inbox are not at the top of outbox and will therefore be 
	//removed first when Quebert dequeues.
	
}

