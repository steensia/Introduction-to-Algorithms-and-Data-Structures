package assignment08;

import java.util.*;

/**
 * <p>
 * A test application for testing the DEPQueue.  This tester is only testing
 * random scenarios so while it is likely that most boundary cases are tested,
 * it is not guaranteed.  (Strings of size other than three are not and will not
 * be tested.)  To improve the likelihood that all conditions are tested, expand
 * the number of tests and target queue sizes, and/or change the code to allow for
 * strings of a random length.
 * </p>
 * 
 * @author Peter Jensen
 * @version November 17, 2017
 */
public class TestDEPQueue01
{
    // A small helper class to prevent equal strings from
    // ever being equal when inserted into a TreeSet<String>.

    private static class UniquePair implements Comparable<UniquePair>
    {
        static int count = 0;

        String     data;
        int        tiebreak;

        UniquePair (String data)
        {
            this.data = data;
            this.tiebreak = count++;
        }

        public int compareTo (UniquePair other)
        {
            int result = this.data.compareTo(other.data);
            return result != 0 ? result : this.tiebreak - other.tiebreak;
        }
    }

    /**
     * Main
     */
    public static void main (String[] args)
    {
        // Parameters for this test run.
        
        Random rand = new Random(12345);
        int tests = 10_000_000;           // Number of tests to run.
        int peakSize = 500_000;         // Target heap size half way through the tests.
        boolean debugging = false; // Change to true for more debugging output.                
       
        // Statistics.
        
        long addCount = 0;
        long addTotalComparisons = 0;
        long addTotalSwaps = 0;
        long removeCount = 0;
        long removeTotalComparisons = 0;
        long removeTotalSwaps = 0;
        
        // Start a timer.
        
        long startTime = System.nanoTime();
        
        // Our queue under test.
        
        DEPQueue myQueue = new DEPQueue();
       
        // A Java data structure for validating our queue's operation.
        
        TreeSet<UniquePair> sortedData = new TreeSet<UniquePair>(); 
        
        // Randomly add or remove elements over and over again.
        
        for (int operationCount = 1; operationCount <= tests; operationCount++)
        {           
            // Calculate a target size that starts small and peaks in the middle
            //   of the test run.  Use it to calculate a threshold to help determine
            //   which random operation to apply.  (If the queue is much smaller
            //   than the target size, make the likelihood of adding elements greater.)
           
            double targetSize = Math.round((0.5 - Math.abs(operationCount*1.0/tests - 0.5)) * 2 * peakSize);
            if (targetSize <= 0)
                targetSize = 1;
            double addThreshold = (myQueue.size()+0.5)/(targetSize*2);

            // Randomly select an operation to apply to the queue.

            if (rand.nextDouble() > addThreshold)
            {
                // Add a random string to the queue.                                
                // First, create a random three-letter string.
               
                StringBuilder sb = new StringBuilder();
                sb.append((char)('a' + rand.nextInt(26)));
                sb.append((char)('a' + rand.nextInt(26)));
                sb.append((char)('a' + rand.nextInt(26)));
                String word = sb.toString();
               
                // Debugging output
                
                if (debugging)                    
                    System.out.printf("Adding \"%s\"", word);

                // Next, add it to our priority queue.
                //   Collect statistics as we go.
               
                addTotalComparisons -= myQueue.getComparisonCount();  // Added back in below
                addTotalSwaps       -= myQueue.getSwapCount();        // Added back in below
                
                myQueue.insert(word);   
                
                addCount++;
                addTotalComparisons += myQueue.getComparisonCount();
                addTotalSwaps       += myQueue.getSwapCount();                                

                // Finally, add it to a sorted list of data for later verification.
               
                sortedData.add(new UniquePair(word));
               
                // Debugging output
               
                if (debugging)
                    System.out.printf  (", queue size now %d.\n", myQueue.size());
                                      
                // Verify that the size is now correct.
                
                if (myQueue.size() != sortedData.size())
                    throw new RuntimeException ("Size is not correct, expected " + sortedData.size() + " but reported " + myQueue.size() + ".");
                
            }
            else
            {
                // Randomly choose to remove min or max element.
               
                boolean removeMin = rand.nextBoolean();
               
                // Debugging output

                if (debugging)
                    System.out.printf  ("Removing %s", removeMin ? "minimum" : "maximum");                
                
                // Do we expect an exception?
               
                boolean exceptionExpected = myQueue.size() == 0;
               
                // Remove a word from the queue.
               
                String word = "";
               
                // Do the removal.
                
                try
                {
                    // Start statistics collection.
                    
                    removeTotalComparisons -= myQueue.getComparisonCount();  // Added back in below
                    removeTotalSwaps       -= myQueue.getSwapCount();        // Added back in below
                    
                    // Remove the min or max value from our queue.
                    
                    word = removeMin ? myQueue.removeMin() : myQueue.removeMax();
                    
                    // Remove the corresponding min or max value from the sorted list.
                    
                    Iterator<UniquePair> it = removeMin ? sortedData.iterator() : sortedData.descendingIterator();                
                    String expectedWord = it.next().data;
                    it.remove();
                    
                    // Make sure they match.
                    
                    if (!word.equals(expectedWord))
                        throw new RuntimeException ("Incorrect word removed, expected \"" + expectedWord + "\", got \"" + word + "\".");
                
                    // Debugging output
                    
                    if (debugging)
                        System.out.printf  (" value \"%s\", queue size now %d.\n", word, myQueue.size());
                }
                catch (NoSuchElementException e)
                {
                    // If an exception was not expected, rethrow it to terminate the test.
                   
                    if (!exceptionExpected)
                        throw (e);
                    
                 // Debugging output

                    if (debugging)
                        System.out.printf  (", got an exception as expected (because size==0).\n");                    
                }
                finally
                {
                    // Finish statistics collection (must not skip).
                    
                    removeCount++;
                    removeTotalComparisons += myQueue.getComparisonCount();
                    removeTotalSwaps       += myQueue.getSwapCount();
                }
                              
                // Verify that the size is now correct.
                
                if (myQueue.size() != sortedData.size())
                    throw new RuntimeException ("Size is not correct, expected " + sortedData.size() + " but reported " + myQueue.size() + ".");                               
            }
        }
        
        // Stop timing the code.
        
        long endTime = System.nanoTime();
        
        // Output statistics.
        
        System.out.println();
        System.out.println("Test complete - no errors.");
        System.out.println();
        System.out.printf ("Elapsed time (in seconds):   %12.2f  \n", (endTime-startTime)/1e9);
        System.out.printf ("Total comparisons:           %,12d   \n", (addTotalComparisons + removeTotalComparisons));
        System.out.printf ("Total swaps:                 %,12d   \n", (addTotalSwaps + removeTotalSwaps));
        System.out.printf ("Avg. comparisons per add:    %,12.2f \n", (addTotalComparisons*1.0/addCount));
        System.out.printf ("Avg. swaps per add:          %,12.2f \n", (addTotalSwaps*1.0/addCount));
        System.out.printf ("Avg. comparisons per remove: %,12.2f \n", (removeTotalComparisons*1.0/removeCount));
        System.out.printf ("Avg. swaps per remove:       %,12.2f \n", (removeTotalSwaps*1.0/removeCount));
    }
}
