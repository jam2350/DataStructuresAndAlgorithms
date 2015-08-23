//written by jam230
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

public class SlowMedian implements MovingMedian {
	private double[] theNumbers;
	//array of all the numbers contained in the file

	//a method that stores the numbers in a file in an array
	public void readFile(String filename) throws IOException {
		File numbers = new File(filename);
		Scanner lineScanner = new Scanner(numbers);
		//Scanner to calculate how big to make array by counting lines
		int lines;
		for(lines = 0; lineScanner.hasNextLine(); lines++) {
			lineScanner.nextLine();
		}
		//O(N), loop will iterate through the whole file N (the amount of numbers) times
		lineScanner.close();
		Scanner numberScanner = new Scanner(numbers);
		//Scanner to store numbers in an array
		theNumbers = new double[lines];
		for( int i = 0; numberScanner.hasNextDouble(); i++){
			theNumbers[i] = numberScanner.nextDouble();
		}
		//O(N), loop will iterate through the whole file N (the amount of numbers) times
		numberScanner.close();	
	}

	//method to get array of all moving medians for a given window
	public double[] getMovingMedians(int window) {
		double [] movingMedians = new double [theNumbers.length - window];
		//array to store all moving medians
		for (int i=0; i< movingMedians.length; i++){
			movingMedians [i] = getMedian(theNumbers, i, window+i);
		}
		//O(getMedian * N-window) because iterates through the whole array moving
		return movingMedians;
	}

	public double getMedian(double[] numbers, int start, int end) {
		double [] subArray = new double[end - start];
		for (int i = 0; i< subArray.length; i++){
			subArray[i] = numbers[start+i];
		}
		//O(1*window) because loop iterates through all of subArray
		Arrays.sort(subArray);
		//O(windowLogwindow) because to sort an array of length k takes O(klogk) time
		return subArray[subArray.length/2];
		//return the median of the sorted array
		//running time of full method: O(window + windowLogwindow)
	}
	
	public static void main(String [] args){
		SlowMedian tester = new SlowMedian();
		try {
			tester.readFile(args[0]);
			//O(N)
			double [] theMedians = tester.getMovingMedians(5);
			//O(5 + 5log5 * N-5)
			for (double element : theMedians){
				System.out.println(element);
			}
			//O(N-5)
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//The running time of my program is O(N) + O(N), to read the file twice, + O(5 + 5log5 * (N-5)), to find the median in a all
	//subarrays of length window beginning after window, + O(N-5), to traverse and print the list of medians. In sum:
	//O(N + N + 5 + 5log5 * (N-5) + N - 5) = O(3N + (N-5)5log5) = O(N).

}
