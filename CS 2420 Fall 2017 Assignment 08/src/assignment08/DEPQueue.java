package assignment08;

import java.util.ArrayList;
import java.util.Collections;

/**The Double-Ended Priority Queue class utilizes the structure of heaps.
 * ArrayLists are created in order to store the objects behind the scenes
 * instead of using a binary trees to simply the work.  One array maintains a minimum heap 
 * condition, where the smallest value takes the root. Lastly, the other array maintains 
 * a maximum heap condition, where the largest value takes the root.
 * 
 * @author Steen Sia
 * @version 11/30/17
 */
public class DEPQueue
{
	/**This inner class is designed to keep the minimum and 
	 * maximum heap positions at all times for the corresponding data. 
	 */
	public static class HeapIndex 
	{
		String data;
		int minHeapInd;
		int maxHeapInd;

		public HeapIndex(String data, int minHeapInd, int maxHeapInd)
		{
			this.data = data;
			this.minHeapInd = minHeapInd;
			this.maxHeapInd = maxHeapInd;
		}
	}

	//Fields

	private ArrayList<HeapIndex> minHeap;
	private ArrayList<HeapIndex> maxHeap;

	//Statistics
	
	private long comparisonCount;
	private long swapCount;

	/**This constructor initializes both ArrayListshe that take in a HeapIndex type, 
	 * in order locate the position of a string at all times.
	 */
	public DEPQueue() 
	{
		minHeap = new ArrayList<HeapIndex>();
		maxHeap = new ArrayList<HeapIndex>();
	}

	/**This method appends a string to both lists and
	 * ensures a complexitiy of O(lg(n)).
	 * 
	 * @param data - the string to be inserted
	 */
	public void insert(String data)
	{	
		//Create a HeapIndex for the data and set minimum and maximum to be the size 
		//of the array, which will be adjusted when swaps are made.
		HeapIndex stringObject = new HeapIndex(data, minHeap.size(), minHeap.size());
		minHeap.add(stringObject);
		maxHeap.add(stringObject);

		//UpwardHeapify on both lists to make sure heap conditions are maintained
		minUpwardHeapify(minHeap.size()-1);
		maxUpwardHeapify(maxHeap.size()-1);
	}

	/**This method removes the highest priority in the minHeap, which
	 * is the root of the data, while also removing the same data in the max 
	 * heap. The data in the maxHeap is not guaranteed to be the lowest
	 * priority, therefore it is located and removed through its maxIndex.
	 * Remove executes in O(lg(n)) time.
	 * 
	 * @return String - data that was removed.
	 */
	public String removeMin()
	{
		//If the list is empty, nothing will be removed.  Simply return null.
		if(minHeap.size() == 0)
		{
			return null;
		}

		//Hold temporary values to keep track of object and indices
		HeapIndex temp = minHeap.get(0);
		String strTemp = minHeap.get(0).data;
		int tempMinInd = temp.minHeapInd;
		int tempMaxInd = temp.maxHeapInd;

		//If the list only has one element, simply remove the element from both lists
		if(minHeap.size() == 1)
		{
			maxHeap.remove(maxHeap.size()-1);
			minHeap.remove(minHeap.size()-1);
			return strTemp;
		}
		
		//removeMin on minHeap

		swapMinIndex(minHeap.get(0).minHeapInd, minHeap.get(minHeap.size()-1).minHeapInd);
		Collections.swap(minHeap, tempMinInd, minHeap.size()-1);
		swapCount++;
		
		//The element is at the last entry, simply remove and downward heapify from the root
		minHeap.remove(minHeap.size()-1);
		minDownwardHeapify(minHeap);

		//removeMin on maxHeap

		swapMaxIndex(maxHeap.get(tempMaxInd).maxHeapInd, maxHeap.get(maxHeap.size()-1).maxHeapInd);
		Collections.swap(maxHeap, tempMaxInd, maxHeap.size()-1);
		swapCount++;

		//Remove last element and upward heapify on the target index
		maxHeap.remove(maxHeap.size()-1);
		maxUpwardHeapify(tempMaxInd);

		return strTemp;
	}

	/**This method removes the highest priority in the maxHeap, which
	 * is the root of the data, while also removing the same data in the min
	 * heap. The data in the minHeap is not guaranteed to be the lowest
	 * priority, therefore it is located and removed through its minIndex.
	 * Remove executes in O(lg(n)) time.
	 * 
	 * @return String - data that was removed.
	 */
	public String removeMax()
	{
		// If the list is empty, nothing will be removed.  Simply return null.
		if(maxHeap.size() == 0)
		{
			return null;
		}

		//Hold temporary values to keep track of object and indices
		HeapIndex temp = maxHeap.get(0);
		String strTemp = maxHeap.get(0).data;
		int tempMinInd = temp.minHeapInd;
		int tempMaxInd = temp.maxHeapInd;

		//If the list only has one element, simply remove the element from both lists
		if(maxHeap.size() == 1)
		{
			minHeap.remove(minHeap.size()-1);
			maxHeap.remove(maxHeap.size()-1);
			return strTemp;
		}
		
		//removeMax on maxHeap
		
		swapMaxIndex(maxHeap.get(0).maxHeapInd, maxHeap.get(maxHeap.size()-1).maxHeapInd);
		Collections.swap(maxHeap, tempMaxInd, maxHeap.size()-1);
		swapCount++;

		//The element is at the last entry, simply remove and downward heapify from the root
		maxHeap.remove(maxHeap.size()-1);
		maxDownwardHeapify(maxHeap);

		//removeMax on minHeap
		
		swapMinIndex(tempMinInd, minHeap.get(minHeap.size()-1).minHeapInd);
		Collections.swap(minHeap, tempMinInd, minHeap.size()-1);
		swapCount++;

		//Remove last element and upward heapify on the target index
		minHeap.remove(minHeap.size()-1);
		minUpwardHeapify(tempMinInd);

		return strTemp;
	}

	/**This method returns the size of the double-ended
	 * priority queue.
	 * 
	 * @return int - size of the list
	 */
	public int size()
	{
		return maxHeap.size();
	}

	/**This method returns the number of comparisons made while
	 * data is heapified to their according location.
	 * 
	 * @return long - the number of comparisons
	 */
	public long getComparisonCount()
	{
		return comparisonCount;		
	}

	/**This method returns the number of swaps made while
	 * data is heapified to their according location.
	 *
	 * @return long - the number of swaps 
	 */
	public long getSwapCount()
	{
		return swapCount;
	}

	/**This private helper method is used to keep track and increment any comparisons made.
	 * 
	 * @param n - number
	 * @return int - number of comparisons
	 */
	private int count (int n)
	{
		comparisonCount++;
		return n;
	}
	
	/**This private helper method utilizes recursion to maintain min heap conditions
	 * by comparing the current data to its parent and recurse up until the current
	 * is no longer of higher priority.
	 * 
	 * @param target - the index to start heapify
	 */
	private void minUpwardHeapify(int target)
	{
			//Calculate the parent index 
			int currentInd = target;
			int parentInd = (currentInd - 1) / 2;

			//Compare the current element with its parent and swap until there is higher priority
			while(parentInd != currentInd && currentInd < minHeap.size() && count(minHeap.get(parentInd).data.compareTo(minHeap.get(currentInd).data)) > 0)
			{
				swapMinIndex(minHeap.get(currentInd).minHeapInd, minHeap.get(parentInd).minHeapInd);
				Collections.swap(minHeap, parentInd, currentInd);
				swapCount++;

				//Inversely set currentInd and parentInd to each other for recursion
				currentInd = parentInd;
				parentInd = (currentInd - 1) / 2;
			}
	}
	/**This private helper method takes in the minHeap list and starts the heapify process
	 * at the parent root data to maintain minHeap conditions. The parent compares 
	 * itself to its children with higher priority, swap with that child and recurse 
	 * downward until there is no child with higher priority.
	 * 
	 * @param minHeap - the list to be heapified downwards
	 */
	private void minDownwardHeapify(ArrayList<HeapIndex> minHeap)
	{
		//Keep track if we need to swap and recurse
		boolean swapCheck = true;
		
		//Calculate the parent index
		int startInd = 0;
		int currentInd = (startInd - 1) / 2;

		//Keep recursing until the maxHeap conditions are met, parent must be the highest priority
		while(swapCheck)
		{
			swapCheck = false;

			//Calculate the children indices
			currentInd = startInd;
			int leftInd = 2*currentInd + 1;
			int rightInd = 2*currentInd + 2;

			//Check to see if there is a right child, if there is then a left child also exists
			if(rightInd < minHeap.size())
			{
				//Narrow down which child is higher priority
				if(count(minHeap.get(leftInd).data.compareTo(minHeap.get(rightInd).data)) < 0)
				{
					//The left child has higher priority, compare against parent and swap if needed.
					if(count(minHeap.get(currentInd).data.compareTo(minHeap.get(leftInd).data)) > 0)
					{
						int targetInd = swapMinIndex(minHeap.get(currentInd).minHeapInd, minHeap.get(leftInd).minHeapInd);
						Collections.swap(minHeap, currentInd, leftInd);
						swapCount++;

						//Set swapCheck to true to continue recursion on swapped index
						startInd = targetInd;
						swapCheck = true;
					}
				}
				else
				{
					//The right child has higher priority, compare against parent and swap if needed.
					if(count(minHeap.get(currentInd).data.compareTo(minHeap.get(rightInd).data)) > 0)
					{
						int targetInd = swapMinIndex(minHeap.get(currentInd).minHeapInd, minHeap.get(rightInd).minHeapInd);
						Collections.swap(minHeap, currentInd, rightInd);
						swapCount++;

						//Set swapCheck to true to continue recursion on swapped index
						startInd = targetInd;
						swapCheck = true;
					}
				}
			}
			//There is no right child, check if left child exists
			else if(leftInd < minHeap.size())
			{
				//The left child has higher priority, compare against parent and swap if needed.
				if(leftInd < minHeap.size() && count(minHeap.get(currentInd).data.compareTo(minHeap.get(leftInd).data)) > 0)
				{
					swapMinIndex(minHeap.get(currentInd).minHeapInd, minHeap.get(minHeap.size()-1).minHeapInd);
					Collections.swap(minHeap, currentInd, minHeap.size()-1);
					swapCount++;
				}
			}
		}
	}

	/**This private helper method utilizes recursion to maintain max heap conditions
	 * by comparing the current data to its parent and recurse up until the current
	 * is no longer of higher priority.
	 * 
	 * @param target - the index to start heapify
	 */
	private void maxUpwardHeapify(int target)
	{		
			//Calculate the parent index 
			int currentInd = target;
			int parentInd = (currentInd - 1) / 2;

			//Compare the current element with its parent and swap until there is higher priority
			while(parentInd != currentInd && currentInd < maxHeap.size() && count(maxHeap.get(parentInd).data.compareTo(maxHeap.get(currentInd).data)) < 0)
			{
				swapMaxIndex(maxHeap.get(currentInd).maxHeapInd, maxHeap.get(parentInd).maxHeapInd);
				Collections.swap(maxHeap, parentInd, currentInd);
				swapCount++;

				//Inversely set currentInd and parentInd to each other for recursion
				currentInd = parentInd;
				parentInd = (currentInd - 1) / 2;
			}
	}
	
	/**This private helper method takes the maxHeap list and starts the heapify process
	 * at the parent root data to maintain maxHeap conditions. The parent compares 
	 * itself to its children with higher priority, swap with that child and recurse 
	 * downward until there is no child with higher priority.
	 * 
	 * @param minHeap - the list to be heapified downwards
	 */
	private void maxDownwardHeapify(ArrayList<HeapIndex> maxHeap)
	{
		//Keep track if we need to swap and recurse
		boolean swapCheck = true;
		
		//Calculate the parent index
		int startInd = 0;
		int currentInd = (startInd - 1) / 2;

		//Keep recursing until the maxHeap conditions are met, parent must be the highest priority
		while(swapCheck)
		{
			swapCheck = false;
			
			//Calculate the children indices
			currentInd = startInd;
			int leftInd = 2*currentInd + 1;
			int rightInd = 2*currentInd + 2;

			//Check to see if there is a right child, if there is then a left child also exists
			if(rightInd < maxHeap.size())
			{
				//Narrow down which child is higher priority
				if(count(maxHeap.get(leftInd).data.compareTo(maxHeap.get(rightInd).data)) > 0)
				{
					//The left child has higher priority, compare against parent and swap if needed.
					if(count(maxHeap.get(currentInd).data.compareTo(maxHeap.get(leftInd).data)) < 0)
					{
						int targetInd = swapMaxIndex(maxHeap.get(currentInd).maxHeapInd, maxHeap.get(leftInd).maxHeapInd);
						Collections.swap(maxHeap, currentInd, leftInd);
						swapCount++;
						
						//Set swapCheck to true to continue recursion on swapped index
						startInd = targetInd;
						swapCheck = true;
					}
				}
				else
				{
					//The right child has higher priority, compare against parent and swap if needed.
					if(count(maxHeap.get(currentInd).data.compareTo(maxHeap.get(rightInd).data)) < 0)
					{
						int targetInd = swapMaxIndex(maxHeap.get(currentInd).maxHeapInd, maxHeap.get(rightInd).maxHeapInd);
						Collections.swap(maxHeap, currentInd, rightInd);
						swapCount++;
						
						//Set swapCheck to true to continue recursion on swapped index
						startInd = targetInd;
						swapCheck = true;
					}
				}
			}
			//There is no right child, check if left child exists
			else if(leftInd < maxHeap.size())
			{
				//The left child has higher priority, compare against parent and swap if needed.
				if(leftInd < maxHeap.size() && count(maxHeap.get(currentInd).data.compareTo(maxHeap.get(leftInd).data)) < 0)
				{
					swapMaxIndex(maxHeap.get(currentInd).maxHeapInd, maxHeap.get(maxHeap.size()-1).maxHeapInd);
					Collections.swap(maxHeap, currentInd, maxHeap.size()-1);
					swapCount++;
				}
			}
		}
	}
	
	/**This private helper method swaps the max indices for the maxHeap
	 * 
	 * @param firstIndex - first max index
	 * @param secondIndex - second max index
	 * @return int - target index
	 */
	private int swapMaxIndex(int firstIndex, int secondIndex)
	{
		int tempOneInd = firstIndex;
		int tempTwoInd = secondIndex;
		
		maxHeap.get(firstIndex).maxHeapInd = maxHeap.get(secondIndex).maxHeapInd;
		maxHeap.get(secondIndex).maxHeapInd = tempOneInd;
		
		int targetIndex = tempTwoInd;
		return targetIndex;
	}
	
	/**This private helper method swaps the min indices for the minHeap
	 * 
	 * @param firstIndex - first max index
	 * @param secondIndex - second max index
	 * @return int - target index
	 */
	private int swapMinIndex(int firstIndex, int secondIndex)
	{
		int tempOneInd = minHeap.get(firstIndex).minHeapInd;
		int tempTwoInd = minHeap.get(secondIndex).minHeapInd;
		
		minHeap.get(firstIndex).minHeapInd = minHeap.get(secondIndex).minHeapInd;
		minHeap.get(secondIndex).minHeapInd = tempOneInd;
		
		int targetIndex = tempTwoInd;
		return targetIndex;
	}
}
