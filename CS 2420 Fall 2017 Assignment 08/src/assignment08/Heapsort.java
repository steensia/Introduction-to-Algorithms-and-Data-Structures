package assignment08;

import java.util.ArrayList;
import java.util.Collections;

/**
 * <p> A quick test of heapsort.  This version
 * is very similar to the version presented in class,
 * except the remove step has been optimized slightly
 * to reduce the code size. 
 * </p>
 * 
 * @author Peter Jensen
 * @version November 13, 2017
 */
public class Heapsort
{
    /**
     * Main
     */
    public static void main (String[] args)
    {        
        // Build an array filled with random doubles.
        
        double [] data = new double[1000];

        
        for (int i = 0; i < 1000; i++)
            data[i] = Math.random();
                
        // Build heap, largest values first.
        
        for (int n = 1; n < data.length; n++)
        {
            // Adding element at data[n] to the heap.
            
            int current = n;
            int parent = (current-1)/2;
            
            // As long as there is a parent of lower priority, swap child and parent.
            //   (Keep moving up the heap.)
            
            while (current != parent && data[parent] < data[current])
            {
                double temp = data[parent];
                data[parent] = data[current];
                data[current] = temp;
                
                current = parent;
                parent = (current-1)/2;
            }            
        }
        
        // Destroy the heap.
        
        for (int n = data.length-1; n > 0; n--)
        {
            // We need to swap the highest priority element
            //   with the element at position n.  Then, we
            //   need to fix the heap from position 0.
            
            int swapPos = 0;
            int fixPos = n;
            
            // As long as an element needs to be swapped
            
            while (fixPos != swapPos)
            {                
                // Swap it.
                
                double temp = data[swapPos];
                data[swapPos] = data[fixPos];
                data[fixPos] = temp;                
                
                // Fix the heap from the swap position.
                
                fixPos = swapPos;      
                
                int left = fixPos*2+1, right = left+1;                
                
                if (left >= n) 
                    break;  // No more children
                
                // Search for higher priority children.
                
                if (left < n && data[left] < data[swapPos])
                    swapPos = left;
                if (right < n && data[right] < data[swapPos])
                    swapPos = right;
            }
        }
        
        // Print out the sorted array, verify that it's sorted.
        
        double last = data[0];
        for (double d : data)
        {
            System.out.println(d);
            if (d < last)
                throw new RuntimeException ("Not sorted");
        }

    }

}
