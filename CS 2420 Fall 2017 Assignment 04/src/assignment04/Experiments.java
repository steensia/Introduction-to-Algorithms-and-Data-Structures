// Make sure this class is in package assignment04

package assignment04;

import java.math.BigInteger;
import java.util.*;

/**
 * <p>
 * This Experiments class contains experimental code: static utility methods for
 * performing various sort operations, code for performing comparisons and
 * measuring performance, and code for building random data sets.
 * </p>
 * 
 * <p>
 * Students are not allowed to modify the existing public 'interface' to this
 * class. Public method headers, public variables, and method contracts may not
 * change. (In other words, don't break the tester.)  Additionally, this class
 * provides a few set-up methods for students to use, but our tests will not
 * involve these set-up methods. (See below.)
 * </p>
 * 
 * <p>
 * This class is designed for experimental purposes only - it is not designed
 * for use in larger projects. Thus, some coding decisions are not appropriate
 * for anything other than experimentation: modifiable public static variables,
 * public static helper methods, etc.
 * </p>
 * 
 * @author [Steen Sia]
 * @version [10/19/17]
 */
final public class Experiments  
{
	/*
	 * Students may place additional static classes, functions, and variables in
	 * this class (private or public). You may store experimental data in static
	 * variables, but you cannot use static variables in place of parameters,
	 * return values, or local variables. Don't move values between method calls
	 * in static variables, and don't use them for things like loop counters or
	 * temporary values.
	 */

	/* Do not replace this constructor/code.  No other constructor allowed. */

	private Experiments () { throw new RuntimeException ("Cannot build Experiments objects - use the static methods."); }

	/* Required functions - Notice the use of a generic type parameter. */


	/**
	 * <p>
	 * Sorts (in ascending order using insertion sort) a range of data in in an
	 * array. The specified comparator is used to determine the relative
	 * ordering of pairs of elements.
	 * </p>
	 * 
	 * <p>
	 * The range of data is [startPos, endPos). (Remember, this mathematical
	 * syntax indicates that we include the element at startPos, but that the
	 * element at endPos is excluded. If you sort the range [4,8), elements 4,
	 * 5, 6, and 7 will be in ascending order.)
	 * </p>
	 * 
	 * <p>
	 * The sort is stable and in-place. There is no return value because the
	 * data array is passed by reference. (Both the caller and this method have
	 * a reference to the same array.)
	 * </p>
	 * 
	 * <p>
	 * This method performs no unnecessary or duplicate comparisons.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param data
	 *            an array of data elements
	 * @param startPos
	 *            the first position to sort (inclusive)
	 * @param endPos
	 *            the last position to sort (exclusive)
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 */
	public static <T> void insertionSort (T[] data, int startPos, int endPos, Comparator<T> comparator)
	{
		T swapElement;
		int leftPos;

		//Start at 1 so there is no unnecessary comparison
		for(int firstPos = startPos + 1; firstPos < endPos; firstPos++)
		{
			swapElement = data[firstPos]; //holds the startElement
			leftPos = firstPos - 1;
			//Shifts the leftElement up until the startElement goes where it belongs
			while(leftPos >= startPos && count(comparator.compare(swapElement, data[leftPos])) < 0)
			{
				data[leftPos+1] = data[leftPos];			
				leftPos--;
			}
			//Nothing is less than or equal to the left of the startElement, so it goes to its respective position
			data[leftPos+1] = swapElement; 
		}
	}

	/**
	 * <p>
	 * Partitions an array around a pivot using the quicksort partition
	 * algorithm. This method chooses the element at to be the pivot element.
	 * The final position of the pivot element is returned to the caller.
	 * </p>
	 * 
	 * <p>
	 * To ensure consistency, partitioning proceeds as follows:
	 * </p>
	 * 
	 * <ul>
	 * <li>The element at <i>floor((startPos+endPos)/2)</i> is selected as the
	 *     pivot.</li>
	 * <li>The pivot is swapped with the leftmost element. The pivot is now at
	 *     startPos.</li>
	 * <li>The remaining elements are partitioned into smaller and then larger
	 *     elements in a single pass (using the std. partition algorithm).</li>
	 * <li>The pivot is swapped with the rightmost smaller element, moving it to
	 *     the middle of the partitions.</li>
	 * </ul>
	 * 
	 * <p>As a double-check:  Partitioning these elements (2 9 3 7 8 6 4) would produce 
	 * (6 4 3 2 7 8 9).  </p>
	 * 
	 * <p>
	 * This method performs no unnecessary or duplicate comparisons and does not
	 * create any new arrays.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param data
	 *            an array of data elements
	 * @param startPos
	 *            the first position to partition (inclusive)
	 * @param endPos
	 *            the last position to partition (exclusive)
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 * @return the position of the pivot element
	 */
	public static <T> int partition (T[] data, int startPos, int endPos, Comparator<T> comparator)
	{        
		int pivotPos =  (int) Math.floor((startPos+endPos)/2);
		int leftPos = startPos + 1; //index after the pivot index
		int	rightPos = endPos - 1; //index at the end of the list
		T swapElement, pivotElement;

		//Swap the pivotElement with the startElement so that it does not get in the way
		pivotElement = data[pivotPos];
		data[pivotPos] = data[startPos];
		data[startPos] = pivotElement;
		//Checks if the size of the array is one, then just return with no comparison
		if((endPos-startPos) == 1)
		{
			return pivotPos;
		}		
		//A list of 2 elements should only have one comparison which only swaps once else it is in order
		if(leftPos == rightPos)
		{		
			if(count(comparator.compare(data[rightPos], pivotElement)) < 0)
			{
			pivotElement = data[startPos];
			data[startPos] = data[rightPos];
			data[rightPos] = pivotElement;

			pivotPos = rightPos;
			
			return pivotPos;
			}
			else
			{
				return pivotPos;
			}
			
		}	
		while (true) 
		{
			//Retreat right index so long as the rightElement is greater than the pivotElement
			while (count(comparator.compare(data[rightPos], pivotElement)) > 0)
			{
				rightPos--;
			}
			//Advance the left index so long as it is less than the right index and the leftElement is less than the pivotElement
			while(leftPos < rightPos && count(comparator.compare(data[leftPos], pivotElement)) < 0)
			{
				leftPos++;
			}		
			//When the left index meets or exceeds the right index, there is no more comparisons need to be done
			if(leftPos >= rightPos) 
			{				
				break;								
			}
			//Swap the leftElement with the rightElement so long as elements left of the pivot are less and elements right of the pivot are greater than it
			swapElement = data[leftPos];
			data[leftPos] = data[rightPos];
			data[rightPos] = swapElement;			
			leftPos++;
			rightPos--;					
		}	
		//Swap the pivotElement back to its original position and return the right most element of the left partition as the pivotIndex
		pivotElement = data[startPos];
		data[startPos] = data[rightPos];
		data[rightPos] = pivotElement;

		pivotPos = rightPos;
		return pivotPos;
	}

	/**
	 * <p>
	 * Sorts (in ascending order) a subarray. Quicksort is used if the size of
	 * the subarray &gt; cutoff, otherwise insertion sort is used.
	 * </p>
	 * 
	 * <p>
	 * This method uses the partition and insertionSort methods above. This
	 * method is recursive.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param data
	 *            an array of data elements
	 * @param startPos
	 *            the first position to sort (inclusive)
	 * @param endPos
	 *            the last position to sort (exclusive)
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 * @param cutoff
	 *            the quicksort / insertion sort cutoff
	 */
	public static <T> void quicksortWithCutoff (T[] data, int startPos, int endPos, Comparator<T> comparator, int cutoff)
	{	
		int pivotPos;

		//Base case 1: If a list is a size of 0 or 1, it is a sorted list by definition
		if((endPos - startPos) < 2)
		{
			return;
		}	
		//Base case 2: If a list is less than the cutoff, it is more efficient to use insertionSort
		else if((endPos - startPos) <= cutoff)
		{
			insertionSort(data, startPos, endPos, comparator);
			return;
		}		
		//Reduction step: Partition the left and right sub-arrays until the array is sorted
		else
		{
			pivotPos = partition(data, startPos, endPos, comparator); 
			quicksortWithCutoff(data, startPos, pivotPos, comparator, cutoff);
			quicksortWithCutoff(data, pivotPos+1, endPos, comparator, cutoff);
		}
	}

	/**
	 * <p>
	 * Sorts (in ascending order) an array using quicksort.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param data
	 *            an array of data elements
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 */
	public static <T> void quicksort (T[] data, Comparator<T> comparator)
	{
		quicksortWithCutoff(data, 0, data.length, comparator, 1); 
	}
	
	/**
	 * <p>
	 * Sorts (in ascending order) an array. When the array is treated as smaller
	 * subarrays, quicksort is used if the size of a subarray &gt; cutoff,
	 * otherwise insertion sort is used on that subarray.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param data
	 *            an array of data elements
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 * @param cutoff
	 *            the quicksort / insertion sort cutoff
	 */
	public static <T> void quicksortWithCutoff (T[] data, Comparator<T> comparator, int cutoff)
	{
		quicksortWithCutoff(data, 0, data.length, comparator, cutoff);
	}
	
	/**
	 * <p>
	 * Merges two sorted subarrays (from a source array) into one larger sorted
	 * subarray (in a destination array).
	 * </p>
	 * 
	 * <p>
	 * It is assumed that there are two sublists that occupy adjacent elements
	 * in the source array. The first sublist is the elements [startPosA,
	 * startPosB), and the second sublist is [startPosB, endPosB). Each sublist
	 * contains elements in sorted order, but the first element of the second
	 * sublist may be smaller than the last element of the first sublist.
	 * </p>
	 * 
	 * <p>
	 * The first subArray (starting at startPosA) must have a non-zero size. The
	 * other subarray may have a zero size.
	 * </p>
	 * 
	 * <p>
	 * The elements are merged (in ascending sorted order) into a single sublist
	 * in the destination array. The destination sublist has the indices
	 * [startPosA, endPosB). The merge operation is stable. Equal elements from
	 * the source array will be in the same relative order in the destination
	 * array.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param source
	 *            the source array
	 * @param dest
	 *            the destination array
	 * @param startPosA
	 *            sublist start
	 * @param startPosB
	 *            sublist start/end
	 * @param endPosB
	 *            sublist end
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 */
	public static <T> void twoWayMerge (final T[] source, final T[] dest, int startPosA, int startPosB, int endPosB, final Comparator<T> comparator)
	{
		int tempPosA = startPosA;
		int tempPosB = startPosB;
		int currentPos = startPosA; //index to keep track of where to insert an element to destination array
		
		for(int firstPos = startPosA; firstPos < endPosB; firstPos++)
		{	
			//Index can go out of bounds when merging, so set them to array length
			if(endPosB > source.length)
			{
				endPosB = source.length;
			}
			//Checks if sub-array A runs out of elements to drain
			if (tempPosA == startPosB) 
			{
				//Drain elements of sub-array A until none are left
				while(tempPosB < endPosB)
				{
					dest[currentPos] = source[tempPosB];
					tempPosB++;
					currentPos++;
				}
			}
			//Checks if sub-array A runs out of elements to drain
			if (tempPosB == endPosB) 
			{
				while(tempPosA < startPosB)
				{
					dest[currentPos] = source[tempPosA];
					tempPosA++;
					currentPos++;
				}
			} 
			//Compares elements in sub-arrays A & B and drains the lesser element to destination array
			else if(count(comparator.compare(source[tempPosA], source[tempPosB])) <= 0) 
			{
				dest[currentPos] = source[tempPosA];
				tempPosA++;
				currentPos++;
			}
			else 
			{
				dest[currentPos] = source[tempPosB];
				tempPosB++;
				currentPos++;
			}
		}
	}

	/**
	 * <p>
	 * Merges three sorted subarrays (from a source array) into one larger
	 * sorted subarray (in a destination array).
	 * </p>
	 * 
	 * <p>
	 * It is assumed that there are three sublists that occupy adjacent elements
	 * in the source array. The first sublist is the elements [startPosA,
	 * startPosB), the second sublist is [startPosB, startPosC), and the third
	 * sublist is [startPosC, endPosC). Each sublist contains elements in sorted
	 * order, but the first element of any sublist may be smaller than the last
	 * element of any other sublist.
	 * </p>
	 * 
	 * <p>
	 * The first subArray (starting at startPosA) must have a non-zero size.
	 * The other subarrays may have a zero size.
	 * </p>
	 * 
	 * <p>
	 * The elements are merged (in ascending sorted order) into a single sublist
	 * in the destination array. The destination sublist has the indices
	 * [startPosA, endPosC). The merge operation is stable. Equal elements from
	 * the source array will be in the same relative order in the destination
	 * arrray.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param source
	 *            the source array
	 * @param dest
	 *            the destination array
	 * @param startPosA
	 *            sublist start
	 * @param startPosB
	 *            sublist start/end
	 * @param startPosC
	 *            sublist start/end
	 * @param endPosC
	 *            sublist end
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 */
	public static <T> void threeWayMerge (final T[] source, final T[] dest, int startPosA, int startPosB, int startPosC, int endPosC, final Comparator<T> comparator)
	{
		int tempPosA = startPosA;
		int tempPosB = startPosB;
		int tempPosC = startPosC;
		int currentPos = startPosA; //index to keep track of where to insert an element to destination array

		for(int firstPos = startPosA; firstPos < endPosC; firstPos++)
		{	
			//Index can go out of bounds when merging, so set them to array length
			if(endPosC > source.length)
			{
				endPosC = source.length;
			}
			//Check if sub-arrays A and B run out of elements to drain, then just drain sub-array C
			if(tempPosA == startPosB && tempPosB == startPosC)
			{
				while(tempPosC < endPosC)
				{
					dest[currentPos] = source[tempPosC];
					tempPosC++;
					currentPos++;
				}

				if(currentPos == endPosC)
				{
					break;
				}
			}
			//Check if sub-arrays A and C run out of elements to drain, then just drain sub-array B
			if(tempPosA == startPosB && tempPosC == endPosC)
			{
				while(tempPosB < startPosC)
				{
					dest[currentPos] = source[tempPosB];
					tempPosB++;
					currentPos++;
				}

				if(currentPos == endPosC)
				{
					break;
				}
			}
			//Check if sub-arrays B and C run out of elements to drain, then just drain sub-array A
			if(tempPosB == startPosC && tempPosC == endPosC)
			{	
				while(tempPosA < startPosB)
				{
					dest[currentPos] = source[tempPosA];
					tempPosA++;
					currentPos++;
				}

				if(currentPos == endPosC)
				{
					break;
				}
			}
			//Check if sub-array A run out of elements to drain, then only compare sub-array B to C till one sub-array remains
			if(tempPosA == startPosB && tempPosB < startPosC && tempPosC < endPosC) 
			{
				if(tempPosB < startPosC && count(comparator.compare(source[tempPosB], source[tempPosC])) <= 0)
				{
					{	
						dest[currentPos] = source[tempPosB];
						tempPosB++;
						currentPos++;
					}
				}
				else
				{
					{	
						dest[currentPos] = source[tempPosC];
						tempPosC++;
						currentPos++;
					}
				}
			}
			//Check if sub-array B run out of elements to drain, then only compare sub-array A to C till one sub-array remains
			if(tempPosB == startPosC && tempPosA < startPosB && tempPosC < endPosC) 
			{		
				if(tempPosA < startPosB && count(comparator.compare(source[tempPosA], source[tempPosC])) <= 0)
				{
					{	
						dest[currentPos] = source[tempPosA];
						tempPosA++;
						currentPos++;
					}
				}
				else
				{
					{	
						dest[currentPos] = source[tempPosC];
						tempPosC++;
						currentPos++;
					}
				}
			}
			//Check if sub-array C run out of elements to drain, then only compare sub-array A to B till one sub-array remains
			if(tempPosC == endPosC && tempPosA < startPosB && tempPosB < startPosC) 
			{
				if(tempPosA < startPosB && count(comparator.compare(source[tempPosA], source[tempPosB])) <= 0)
				{
					{	
						dest[currentPos] = source[tempPosA];
						tempPosA++;
						currentPos++;
					}
				}
				else
				{
					{	
						dest[currentPos] = source[tempPosB];
						tempPosB++;
						currentPos++;
					}
				}
			}
			//Checks to compare elements in sub-arrays A, B, and C till two sub-arrays remain
			while(tempPosA < startPosB && tempPosB < startPosC && tempPosC < endPosC)
			{
				if(count(comparator.compare(source[tempPosA], source[tempPosB])) <= 0)
				{
					if(count(comparator.compare(source[tempPosA], source[tempPosC])) <= 0)
					{
						dest[currentPos] = source[tempPosA];
						tempPosA++;
						currentPos++;
					}
					else
					{
						dest[currentPos] = source[tempPosC];
						tempPosC++;
						currentPos++;
					}
				}
				else 
				{
					if(count(comparator.compare(source[tempPosB], source[tempPosC])) <= 0)
					{
						{	
							dest[currentPos] = source[tempPosB];
							tempPosB++;
							currentPos++;
						}
					}
					else
					{
						{	
							dest[currentPos] = source[tempPosC];
							tempPosC++;
							currentPos++;
						}
					}
				}
			}
		}
	}

	/**
	 * <p>
	 * Mergesorts (via two-way splits and merges) the data array into ascending
	 * order.
	 * </p>
	 * 
	 * <p>
	 * This function guarantees non-recursive behavior, and guarantees that it
	 * only creates a single additional array, and that the additional array is
	 * the same length as the original data array.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param data
	 *            an array of elements
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 */
	public static <T> void twoWayMergesort (T[] data, Comparator<T> comparator)
	{
		@SuppressWarnings("unchecked")
		T[] dest = (T[]) new Object[data.length]; 

		int layerCount = 0; // keep track of which merge needs to be done

		//Log base 2 algorithm: 1, 2, 4, 8, etc.
		for(int outerLayer = 1; outerLayer <= data.length; outerLayer*=2)
		{
			layerCount++;
			for(int startPosA = 0; startPosA < data.length; startPosA += 2*outerLayer)
			{
				int startPosB = startPosA + outerLayer;
				int endPosB = startPosB + outerLayer;

				//Indexes can go out of bounds when merging, so set them to array length
				if(startPosB > data.length)
				{
					startPosB = data.length;
				}
				if(endPosB > data.length)
				{
					endPosB = data.length;
				}
				//Merges alternate until the array is sorted then break
				if(layerCount % 2 == 0)
				{
					twoWayMerge(dest, data, startPosA, startPosB, endPosB, comparator);
				}
				else
				{
					twoWayMerge(data, dest, startPosA, startPosB, endPosB, comparator);
				}
			}
		}
		//Determine that the dest array is sorted and just need to copy contents into source array
		if(layerCount % 2 != 0)
		{
			for(int currentPos = 0; currentPos < data.length; currentPos++)
			{
				data[currentPos] = dest[currentPos];
			}
		}
	}

	/**
	 * <p>
	 * Mergesorts (via three-way splits and merges) the data array into
	 * ascending order.
	 * </p>
	 * 
	 * <p>
	 * This function guarantees non-recursive behavior, and guarantees that it
	 * only creates a single additional array, and that the additional array is
	 * the same length as the original data array.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param data
	 *            an array of elements
	 * @param comparator
	 *            the comparison function object to use to compare elements
	 */
	public static <T> void threeWayMergesort (T[] data, Comparator<T> comparator)
	{
		@SuppressWarnings("unchecked")
		T[] dest = (T[]) new Object[data.length]; 

		int layerCount = 0; // keep track of which merge needs to be done

		//Log base 3 algorithm: 1, 3, 9, 27, etc.
		for(int outerLayer = 1; outerLayer <= data.length; outerLayer*=3)
		{
			layerCount++;
			for(int startPosA = 0; startPosA < data.length; startPosA += 3*outerLayer)
			{
				int startPosB = startPosA + outerLayer;
				int startPosC = startPosB + outerLayer;
				int endPosC = startPosC + outerLayer;

				//Indexes can go out of bounds when merging, so set them to array length
				if(startPosB > data.length)
				{
					startPosB = data.length;
				}
				if(startPosC > data.length)
				{
					startPosC = data.length;
				}
				if(endPosC > data.length)
				{
					endPosC = data.length;
				}
				//Merges alternate until the array is sorted then break
				if(layerCount % 2 == 0)
				{
					threeWayMerge(dest, data, startPosA, startPosB, startPosC, endPosC, comparator);
				}
				else
				{
					threeWayMerge(data, dest, startPosA, startPosB, startPosC, endPosC, comparator);
					
				}
			}
		}
		//Determine that the dest array is sorted and just need to copy contents into source array
		if(layerCount % 2 != 0)
		{
			for(int currentPos = 0; currentPos < data.length; currentPos++)
			{
				data[currentPos] = dest[currentPos];
			}
		}
	}

	/* Utility functions for testing. */

	private static String[] words = {"alpha", "beta", "gamma", "delta", "omega", "sigma", "bob"};

	/**
	 * <p>
	 * Makes an array of random strings. The only guarantee is that strings are
	 * not null.  (Students may not rely on the random distributions given in this method.)
	 * </p>
	 * 
	 * @param size
	 *            the desired array size
	 * @param seed
	 *            the random seed to use
	 * @return an array of random strings
	 */
	public static String[] generateMixedStrings (int size, long seed)
	{
		Random r = new Random(seed);

		// Regardless of the final array size, generate 1000 strings.

		String[] elements = new String[1000];

		for (int i = 0; i < elements.length - 10; i++)
		{
			elements[i] = "";
			while (elements[i].length() == 0 || r.nextBoolean())
				if (r.nextBoolean())
					elements[i] += words[r.nextInt(words.length)];
				else
					elements[i] += r.nextInt(99)+1;
		}

		// Make sure there are a few duplicates but with different object references.

		for (int i = elements.length - 10; i < elements.length; i++)
			elements[i] = new String(elements[r.nextInt(elements.length - 10)]);

		// Build the array, populate it with choices from the 1000 prebuilt strings.
		//   (This helps keep the memory footprint smaller, and it increases the
		//   likelihood of duplicates in the final data.)

		String[] result = new String[size];

		for (int i = 0; i < size; i++)
			result[i] = elements[r.nextInt(elements.length)];

		return result;
	}

	/**
	 * <p>
	 * Determines the relative order of two strings with embedded numbers.
	 * If a &lt; b, -1 is returned.  If a &gt; b, 1 is returned.  If a == b, 0 is returned.
	 * </p>
	 * 
	 * @param a a string that may contain an embedded number
	 * @param b another string that may contain an embedded number
	 * @return -1, 0, or 1 if a is less than, equal to, or greater than b
	 */
	public static int compareMixedStrings (String a, String b)
	{
		// Students are not allowed to change this code.  It is inefficient on 
		//   purpose.

		if (a.equals(b))
			return 0;

		int aPos = 0;
		int bPos = 0;

		while (true)
		{
			if (aPos < a.length() && bPos == b.length())
				return 1;
			if (aPos == a.length() && bPos < b.length())
				return -1;

			if (Character.isDigit(a.charAt(aPos)) && !Character.isDigit(b.charAt(bPos)))
				return -1;
			if (!Character.isDigit(a.charAt(aPos)) && Character.isDigit(b.charAt(bPos)))
				return 1;

			if (!Character.isDigit(a.charAt(aPos)) && a.charAt(aPos) < b.charAt(bPos))
				return -1;
			if (!Character.isDigit(a.charAt(aPos)) && a.charAt(aPos) > b.charAt(bPos))
				return 1;
			if (!Character.isDigit(a.charAt(aPos)) && a.charAt(aPos) == b.charAt(bPos))
			{
				aPos++;
				bPos++;
				continue;
			}

			String aDigits = "", bDigits = "";

			while (aPos < a.length() && Character.isDigit(a.charAt(aPos)))
				aDigits += a.charAt(aPos++);

			while (bPos < b.length() && Character.isDigit(b.charAt(bPos)))
				bDigits += b.charAt(bPos++);

			BigInteger aInteger = new BigInteger(aDigits);
			BigInteger bInteger = new BigInteger(bDigits);

			int result = aInteger.compareTo(bInteger);

			if (result != 0)
				return result;
		}        
	}

	/**
	 * <p>
	 * Makes an array of random integers. The only guarantee is that integers are
	 * not null.  (Students may not rely on the random distributions given in this method.)
	 * </p>
	 * 
	 * @param size
	 *            the desired array size
	 * @param seed
	 *            the random seed to use
	 * @return an array of random integers
	 */
	public static Integer[] generateIntegers (int size, long seed)
	{
		Random r = new Random(seed);

		Integer[] result = new Integer[size];

		for (int i = 0; i < size; i++)
			result[i] = r.nextInt(9000)+1000;

		return result;
	}
	
	public static Double[] generateDouble (int size, long seed)
	{
		Random r = new Random(seed);
		
		Double[] result = new Double[size];

		for (int i = 0; i < size; i++)
			result[i] = r.nextDouble();

		return result;
	}

	/* A bit of code to track comparisons */

	// Incremented every time a comparator is used within this class.

	public static long comparisonCount;

	/**
	 * <p>
	 * Increments the comparisonCount, then returns it's parameter. Thus, this
	 * function can wrap calls to a comparator to simplify counting comparisons:
	 * </p>
	 * 
	 * <pre>
	 * comparator.compare(x, y);
	 * </pre>
	 * 
	 * <p>
	 * becomes
	 * </p>
	 * 
	 * <pre>
	 * count(comparator.compare(x, y));
	 * </pre>
	 * 
	 * 
	 * @param v
	 *            any int
	 * @return the same int
	 */
	private static int count (int v)
	{
		comparisonCount++;
		return v;
	}

	/**
	 * <p>
	 * A method for testing if sorted data is correct and matches unsorted data.
	 * </p>
	 * 
	 * <p>
	 * The implementation of this method is left somewhat uncommented / vague.
	 * It is not intended to replace a student's own investigations / testing.
	 * It will tell you something is wrong, but it is up to you to discover the
	 * details of your errors.
	 * </p>
	 * 
	 * @param <T>
	 *            a sortable element type
	 * @param unsorted
	 *            the unsorted array
	 * @param sorted
	 *            the sorted array
	 * @param comparator
	 *            the comparator to use for checking order
	 * @param checkStability
	 *            true if this method should check for sort stability
	 */
	public static <T> void sortCheck (T[] unsorted, T[] sorted, Comparator<T> comparator, boolean checkStability)
	{
		// Did the sort work?

		for (int i = 1; i < sorted.length; i++)
			if (comparator.compare(sorted[i-1], sorted[i]) > 0)
				throw new RuntimeException ("Elements out of order at positions " + (i-1) + " and " + i + ": " + sorted[i-1] + " " + sorted[i]);                        

		// Check for unmatched elements.

		IdentityHashMap<Object,Object> unsortedElements = new IdentityHashMap<Object,Object>();
		IdentityHashMap<Object,Object> sortedElements = new IdentityHashMap<Object,Object>();

		for (T e : unsorted)
			unsortedElements.put(e, null);

		for (T e : sorted)
		{
			if (!unsortedElements.containsKey(e))
				throw new RuntimeException ("An unknown element has appeared in the sorted array: " + e);        
			sortedElements.put(e, null);
		}       

		for (T e : unsorted)
		{
			if (!sortedElements.containsKey(e))
				throw new RuntimeException ("At least one copy of an element reference does not appear in the sorted array: " + e);
		}       

		// Check for stability.  
		// Note that it is not possible to test stability on equal references.  A side effect
		//   of this test is that missing/duplicate elements will be detected.

		if (checkStability)
		{
			Map<T,Set<Integer>> unsortedLocations = new HashMap<T,Set<Integer>>();
			Map<T,Set<Integer>> sortedLocations = new HashMap<T,Set<Integer>>();

			// Build sets of locations for equal values.

			for (int i = 0; i < unsorted.length; i++)
			{
				if (!unsortedLocations.containsKey(unsorted[i]))
					unsortedLocations.put(unsorted[i], new TreeSet<Integer>());
				unsortedLocations.get(unsorted[i]).add(i);
			}

			for (int i = 0; i < sorted.length; i++)
			{
				if (!sortedLocations.containsKey(sorted[i]))
					sortedLocations.put(sorted[i], new TreeSet<Integer>());
				sortedLocations.get(sorted[i]).add(i);
			}

			// Compare references at locations in increasing location order.  The
			//   references should be identical.

			for (T equalElement : unsortedLocations.keySet())
			{
				Iterator<Integer> unsortedPositionIterator = unsortedLocations.get(equalElement).iterator();
				Iterator<Integer> sortedPositionIterator   = sortedLocations.get(equalElement).iterator();

				while (unsortedPositionIterator.hasNext() || sortedPositionIterator.hasNext())
				{
					if (!unsortedPositionIterator.hasNext())
						throw new RuntimeException ("At least one copy of an element reference has been duplicated in the sorted array: " + equalElement+ " at sorted position " + sortedPositionIterator.next());
					if (!sortedPositionIterator.hasNext())
						throw new RuntimeException ("At least one copy of an element reference does not appear in the sorted array: " + equalElement + " at unsorted position " + unsortedPositionIterator.next());
					int unsortedPos = unsortedPositionIterator.next();
					int sortedPos = sortedPositionIterator.next();
					if (unsorted[unsortedPos] != sorted[sortedPos])
						throw new RuntimeException ("An element sorted unstably:  Unsorted:  " + unsorted[unsortedPos] + " at " + unsortedPos + "  Sorted: " + sorted[sortedPos] + " at " + sortedPos);                    
				}
			}                        
		}
	}

}
