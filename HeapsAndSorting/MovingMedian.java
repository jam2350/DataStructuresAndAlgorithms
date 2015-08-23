
import java.io.IOException;

public interface MovingMedian {

  /**
   * Reads in a file given a filename string. In the case of this assignment,
   * the file is aapl.txt. Unless you have strong preferences, I recommend that
   * thefile should be read into an array of doubles
   * 
   * @param filename
   * @throws IOException
   */
  public void readFile(String filename) throws IOException;

  /**
   * Get an array of moving medians for all the numbers read from the file. The
   * first (window - 1) numbers should be skipped, since we can only start at
   * the window-th number. For example, if the file contains 20 numbers and the
   * window size is 5, we must skip the first 4 numbers and only start
   * calculating from the 5th number. The 1st median would be the median of the
   * 1st to the 5th number.
   * 
   * You can assume that window will always be odd. (hence there's no need to do
   * the averaging for even medians)
   * 
   * @param window
   * @return
   */
  public double[] getMovingMedians(int window);

  /**
   * Finds the median from an array of doubles. Start represents the
   * zero-indexed index to start calculating from (inclusive). End represents
   * the index to stop calculating (exclusive).
   * 
   * Eg. for an array of 1 5 4 3 2 8 7 9 6, with start = 0 and end = 9, the
   * median should be 5. With start = 2 and end = 5, the median should be 3
   * (since the array under consideration is 4 3 2)
   * 
   * @param numbers
   *          an entire array of doubles
   * @param start
   *          zero-indexed starting index (inclusive)
   * @param end
   *          zero-indexed ending index (exclusive)
   * @return the median of the array of numbers in the start and end index
   *         subset of numbers
   */
  public double getMedian(double[] numbers, int start, int end);

}