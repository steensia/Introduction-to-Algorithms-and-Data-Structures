package assignment07;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 * A small application to help me test my search tree.
 * I set up a tree with composite numbers (and their divisors)
 * in it.
 * </p>
 *  
 * @author Peter Jensen
 * @version November 9, 2017
 */
public class ApplicationPrimeTest
{
    
    /**
     * Represents a number paired with one of its divisors.
     */
    static class Pair
    {
        int number;
        int divisor;
        
        Pair (int n, int d)
        {
            this.number = n;
            this.divisor = d;
        }
    }

    /**
     * @param args
     */
    public static void main (String[] args) throws IOException
    {
        // Allow for easy debugging of balanced trees.
        //   Balanced:
        //      "It took 3873 comparisons to build the tree."
        //      "Tree height: 107"
        //      "It took 37171 comparisons to separate primes and composites."
        //    
        //   Unbalanced:
        //      "It took 28053 comparisons to build the tree."
        //      "Tree height: 10"
        //      "It took 354850 comparisons to separate primes and composites."
        
        boolean keepTreesBalanced = false;
        
        // Set a bounds.
        
        final int maximumNumber = 100;
        
        // Make a comparator for comparing pairs.  Only compare
        //   the numbers.  (Ignore the divisors.)  
        
        Comparator<Pair> numberCompare = (a,b) -> (int) Math.signum(a.number-b.number);
        
        // Make another comparator that compares number and divisors.
        
        Comparator<Pair> numberDivisorCompare = (a,b) -> a == b ? (int) Math.signum(a.divisor-b.divisor) : (int) Math.signum(a.number-b.number);
        
        // Make a visitor for printing pairs.
        
        Visitor<Node<Pair>> printVisitor = (n) -> System.out.println(n.data.number + " is divisible by " + n.data.divisor);
        
        // Make a search tree for collecting the pairs (ordered by number).
        //   Students may experiment with turning the balancing on or off (above).
        
        SearchTree<Pair> numberTree = new SearchTree<Pair>(numberCompare, keepTreesBalanced);

        SearchTree.comparisonCount = 0;        
        {        
            // One divides everything.
            
            for (int i = 1; i <= maximumNumber; i++)
                numberTree.add(new Pair(i, 1));
            
            // For each possible divisor, mark it's multiples.
            
            for (int j = 2; j <= maximumNumber; j++)
                for (int i = j; i <= maximumNumber; i += j)
                    numberTree.add(new Pair(i, j));
        }        
        System.out.println ("It took " + SearchTree.comparisonCount + " comparisons to build the tree.");
        System.out.println ("Tree height: " + numberTree.getHeight());
        
        // At this point, the tree contains pairs as data values.
        //   Each pair has a number n and a divisor d.  All divisors
        //   of n form matched pairs that are stored in the tree.
        //
        // Debug, print out divisors of 30.  For fun, add them to another search
        //   tree.  (Not wise, but I'm testing here!)
                    
        List<Pair> divisors = numberTree.find(new Pair(30,1));
        SearchTree<Pair> tempTree = new SearchTree<Pair>(numberCompare, keepTreesBalanced);        
        for (Pair p : divisors)
            tempTree.add(p);
        tempTree.visitPreOrder(printVisitor);
        
        // Create two separate trees, one for primes, one for composites.
        //   Have the composites tree ordered differently.
        
        SearchTree<Pair> primes     = new SearchTree<Pair>(numberCompare, keepTreesBalanced);  
        SearchTree<Pair> composites = new SearchTree<Pair>(numberDivisorCompare, keepTreesBalanced);  
        
        SearchTree.comparisonCount = 0; 
        {        
            // Copy everything in the original tree into both trees.
            //   This is very inefficient, but it makes a good test of the tree.
            //   Use a different traversal for testing only.
            
            numberTree.visitPostOrder(n -> primes.add(n.data));
            numberTree.visitInOrder(n -> composites.add(n.data));
            
            // Now, for each number n in the original tree, remove nodes from either
            //   the primes or composites tree, as appropriate.  Hugely inefficient
            //   on purpose.  (It makes a better test of the search tree.)
            
            numberTree.visitPreOrder(node ->
            {
                List<Pair> pairs = numberTree.find(node.data);  // Get all numbers that match n (that match node.data).
                
                // If the number of divisors is less than 3, the number is not composite.
                
                if (pairs.size() < 3)
                    for (Pair p : pairs)
                        composites.removeValue(p);    
                
                // If the number of divisors is not 2, the number is not prime.
                
                if (pairs.size() != 2)
                    for (Pair p : pairs)
                        primes.removeReference(p);                    
            }
            );            
        }        
        System.out.println ("It took " + SearchTree.comparisonCount + " comparisons to separate primes and composites.");

        // Print out the primes.
        
        System.out.print("Primes: ");
        primes.visitInOrder(n -> System.out.print((n.data.divisor == 1) ? (n.data.number + " ") : ""));
        System.out.println();
        
        // Print out the composites with their divisors.  Should be in increasing order of 
        //   number and divisor.
        
        System.out.println("Composites: ");
        composites.visitInOrder(printVisitor);
    }

}
