package assignment04;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unused")
public class testExperiments
{

	private static class comparator implements Comparator<Integer> 
	{
		@Override
		public int compare(Integer leftHandSide, Integer rightHandSide) 
		{
			return leftHandSide.compareTo(rightHandSide);
		}
	}

	private static void clear()
	{
		Experiments.comparisonCount = 0;
	}

	private static Comparator<String> stringComparator;

	@Before 
	public void test()
	{
		stringComparator = Comparator.naturalOrder();
	}

	public static void main(String[] args)
	{
		for(int size = 1; size < 5; size++)
		{
			for(int seed =1; seed < 5; seed++)
			{
				String[] data = Experiments.generateMixedStrings(size, seed);
				Experiments.quicksort(data, stringComparator);
				try
				{
					FileWriter writer = new FileWriter("src/ExperimentsReport/quicksortData.txt");
					writer.write("Size /t Seed /t" + Experiments.comparisonCount);		
					writer.close();
				}
				catch(FileNotFoundException e)
				{
					e.printStackTrace();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}
	@Test
	public void testInsertionSortCheckString()
	{
		clear();
		String[] unsorted, sorted;
		unsorted = sorted = Experiments.generateMixedStrings(20, 1);
		Experiments.insertionSort(sorted, 0, 20, stringComparator);
		Experiments.sortCheck(unsorted, sorted, stringComparator, true);
		//System.out.println("InsertionSort with String: " + Experiments.comparisonCount);
	}

	@Test
	public void testQuickSortCheckString()
	{
		clear();
		String[] unsorted, sorted;
		unsorted = sorted = Experiments.generateMixedStrings(20, 1);
		Experiments.quicksort(sorted, stringComparator);
		Experiments.sortCheck(unsorted, sorted, stringComparator, true);
		//System.out.println("QuickSort with String: " + Experiments.comparisonCount);
	}

	@Test
	public void testQuickSortWithCutOffCheckString()
	{
		clear();
		String[] unsorted, sorted;
		unsorted = sorted = Experiments.generateMixedStrings(20, 1);
		Experiments.quicksortWithCutoff(sorted, stringComparator, 7);
		Experiments.sortCheck(unsorted, sorted, stringComparator, true);
		//System.out.println("QuickSortWithCutoff with String: " + Experiments.comparisonCount);
	}

	@Test
	public void testTwoWayMergeSortCheckString()
	{
		clear();
		String[] unsorted, sorted;
		unsorted = sorted = Experiments.generateMixedStrings(20, 1);
		Experiments.twoWayMergesort(sorted, stringComparator);
		Experiments.sortCheck(unsorted, sorted, stringComparator, true);
		//System.out.println("TwoWayMergeSort with String: " + Experiments.comparisonCount);
	}

	@Test
	public void testThreeWayMergeSortCheckString()
	{

		clear();
		String[] unsorted, sorted;
		unsorted = sorted = Experiments.generateMixedStrings(20, 1);
		Experiments.threeWayMergesort(sorted, stringComparator);
		Experiments.sortCheck(unsorted, sorted, stringComparator, true);
		//System.out.println("ThreeWayMergeSort with String: " + Experiments.comparisonCount);
	}
	
	@Test
	public void testInsertionSortCheckStringLargeList()
	{
		clear();
		String[] unsorted, sorted;
		unsorted = sorted = Experiments.generateMixedStrings(2000, 1);
		Experiments.insertionSort(sorted, 0, 2000, stringComparator);
		Experiments.sortCheck(unsorted, sorted, stringComparator, true);
		//System.out.println("InsertionSort with String: " + Experiments.comparisonCount);
	}

	@Test
	public void testQuickSortCheckStringLargeList()
	{
		clear();
		String[] unsorted, sorted;
		unsorted = sorted = Experiments.generateMixedStrings(2000, 1);
		Experiments.quicksort(sorted, stringComparator);
		Experiments.sortCheck(unsorted, sorted, stringComparator, true);
		//System.out.println("QuickSort with String: " + Experiments.comparisonCount);
	}

	@Test
	public void testQuickSortWithCutOffCheckStringLargeList()
	{
		clear();
		String[] unsorted, sorted;
		unsorted = sorted = Experiments.generateMixedStrings(2000, 1);
		Experiments.quicksortWithCutoff(sorted, stringComparator, 7);
		Experiments.sortCheck(unsorted, sorted, stringComparator, true);
		//System.out.println("QuickSortWithCutoff with String: " + Experiments.comparisonCount);
	}

	@Test
	public void testTwoWayMergeSortCheckStringLargeList()
	{
		clear();
		String[] unsorted, sorted;
		unsorted = sorted = Experiments.generateMixedStrings(2000, 1);
		Experiments.twoWayMergesort(sorted, stringComparator);
		Experiments.sortCheck(unsorted, sorted, stringComparator, true);
		//System.out.println("TwoWayMergeSort with String: " + Experiments.comparisonCount);
	}

	@Test
	public void testThreeWayMergeSortCheckStringLargeList()
	{

		clear();
		String[] unsorted, sorted;
		unsorted = sorted = Experiments.generateMixedStrings(2000, 1);
		Experiments.threeWayMergesort(sorted, stringComparator);
		Experiments.sortCheck(unsorted, sorted, stringComparator, true);
		//System.out.println("ThreeWayMergeSort with String: " + Experiments.comparisonCount);
	}

	//ComparisonCount check for Integer Arrays

	@Test
	public void testInsertionSortSortCheck() 
	{
		clear();
		Integer[] sorted, unsorted;
		sorted = unsorted = Experiments.generateIntegers(20, 1);
		Experiments.insertionSort(sorted, 0, 20, new comparator());
		Experiments.sortCheck(unsorted, sorted, new comparator(), true);
		long count = Experiments.comparisonCount;
		System.out.println("InsertionSort: " + count);
	}

	@Test
	public void testQuicksortSortCheck()
	{
		clear();
		Integer[] sorted, unsorted;
		sorted = unsorted = Experiments.generateIntegers(20, 1);
		Experiments.quicksort(sorted, new comparator());
		Experiments.sortCheck(unsorted, sorted, new comparator(), true);
		long count = Experiments.comparisonCount;
		System.out.println("QuickSort: " + count); 
	}

	@Test
	public void testQuicksortWithCutoffSortCheck()
	{
		clear();
		Integer[] sorted, unsorted;
		sorted = unsorted = Experiments.generateIntegers(20, 1);
		Experiments.quicksortWithCutoff(sorted, new comparator(), 7);
		Experiments.sortCheck(unsorted, sorted, new comparator(), true);
		long count = Experiments.comparisonCount;
		System.out.println("QuickSortWithCutOff: " + count); 
	}

	@Test
	public void testTwoWayMergeSortCheck()
	{
		clear();
		Integer[] unsorted, sorted;
		unsorted = sorted = Experiments.generateIntegers(20, 1);
		Experiments.twoWayMergesort(sorted, new comparator());
		Experiments.sortCheck(unsorted, sorted, new comparator(), true);
		System.out.println("TwoWayMergeSort: " + Experiments.comparisonCount);
	}

	@Test
	public void testThreeWayMergeSortCheck()
	{
		clear();
		Integer[] unsorted, sorted;
		unsorted = sorted = Experiments.generateIntegers(20, 1);
		Experiments.threeWayMergesort(sorted, new comparator());
		Experiments.sortCheck(unsorted, sorted, new comparator(), true);
		System.out.println("ThreeWayMergeSort: " + Experiments.comparisonCount);
	}
	
	@Test
	public void testInsertionSortSortCheckLargeList() 
	{
		clear();
		Integer[] sorted, unsorted;
		sorted = unsorted = Experiments.generateIntegers(2000, 1);
		Experiments.insertionSort(sorted, 0, 2000, new comparator());
		Experiments.sortCheck(unsorted, sorted, new comparator(), true);
		long count = Experiments.comparisonCount;
		//System.out.println("InsertionSort: " + count);
	}

	@Test
	public void testQuicksortSortCheckLargeList()
	{
		clear();
		Integer[] sorted, unsorted;
		sorted = unsorted = Experiments.generateIntegers(2000, 1);
		Experiments.quicksort(sorted, new comparator());
		Experiments.sortCheck(unsorted, sorted, new comparator(), true);
		long count = Experiments.comparisonCount;
		//System.out.println("QuickSort: " + count); 
	}

	@Test
	public void testQuicksortWithCutoffSortCheckLargeList()
	{
		clear();
		Integer[] sorted, unsorted;
		sorted = unsorted = Experiments.generateIntegers(2000, 1);
		Experiments.quicksortWithCutoff(sorted, new comparator(), 7);
		Experiments.sortCheck(unsorted, sorted, new comparator(), true);
		long count = Experiments.comparisonCount;
		//System.out.println("QuickSortWithCutOff: " + count); 
	}

	@Test
	public void testTwoWayMergeSortCheckLargeList()
	{
		clear();
		Integer[] unsorted, sorted;
		unsorted = sorted = Experiments.generateIntegers(2000, 1);
		Experiments.twoWayMergesort(sorted, new comparator());
		Experiments.sortCheck(unsorted, sorted, new comparator(), true);
		//System.out.println("TwoWayMergeSort: " + Experiments.comparisonCount);
	}

	@Test
	public void testThreeWayMergeSortCheckLargeList()
	{
		clear();
		Integer[] unsorted, sorted;
		unsorted = sorted = Experiments.generateIntegers(2000, 1);
		Experiments.threeWayMergesort(sorted, new comparator());
		Experiments.sortCheck(unsorted, sorted, new comparator(), true);
		//System.out.println("ThreeWayMergeSort: " + Experiments.comparisonCount);
	}

	//Other random tests on smaller integer arrays

	@Test
	public void testInsertionSort2() 
	{
		clear();
		Integer[] list = {4, 5, 9, -1, 5};
		Experiments.insertionSort(list, 0, list.length, new comparator());
	}

	@Test
	public void testPartitionMethod()
	{
		clear();
		Integer[] data = {2, 9, 3, 7, 8, 6, 4}; // should be {6, 4, 3, 2, 7, 8, 9} after partition
		Experiments.partition(data, 0, 7, new comparator());
		//System.out.println(Experiments.comparisonCount);
	}

	@Test
	public void testPartitionMethod2()
	{
		clear();
		Integer[] data = {1};
		Experiments.partition(data, 0, 1, new comparator());
		//System.out.println(Experiments.comparisonCount);
	}
	
	@Test
	public void testPartitionMethod3()
	{
		clear();
		Integer[] data = {1, 0};
		Experiments.partition(data, 0, 2, new comparator());
		//System.out.println(Experiments.comparisonCount);
	}


	@Test
	public void testQuickSort2()
	{
		clear();
		Integer[] unsorted, sorted;
		Integer[] data = {2, 9, 3, 7, 8, 6, 4};
		unsorted = sorted = data;
		Experiments.quicksort(data, new comparator());
		Experiments.sortCheck(unsorted, sorted, new comparator(), true);
	}

	@Test
	public void testTwoWayMergeSortCheck2()
	{
		clear();
		Integer[] unsorted, sorted;
		Integer[] list = {6, 7, 8, 1, 3, 4}; // should be {1, 3, 4, 6, 7, 8} after two way merge
		unsorted = sorted = list;
		Experiments.twoWayMergesort(sorted, new comparator());
		Experiments.sortCheck(unsorted, sorted, new comparator(), true);	
	}

	@Test
	public void testTwoWayMergeSortCheck3()
	{
		clear();
		Integer[] unsorted, sorted;
		Integer[] list = {6, 5, 4, 3, 2, 1}; // should be {1, 2, 3, 4, 5, 6} after two way merge
		unsorted = sorted = list;
		Experiments.twoWayMergesort(sorted, new comparator());
		Experiments.sortCheck(unsorted, sorted, new comparator(), true);	
	}

	@Test
	public void testTwoWayMerge()
	{
		clear();
		Integer[] list = {1, 5, 6, 3, 4, 9}; // should be {1, 3, 4, 5, 6, 9} after two way merge
		Integer[] empty = new Integer[6]; 
		Experiments.twoWayMerge(list, empty, 0, 3, 6, new comparator());
	}

	@Test
	public void testTwoWayMerge2()
	{
		clear();
		Integer[] list = {6, 7, 8, 1, 3, 4}; // should be {1, 3, 4, 6, 7, 8} after two way merge
		Integer[] empty = new Integer[6]; 
		Experiments.twoWayMerge(list, empty, 0, 3, 16, new comparator());
	}

	@Test
	public void testTwoWayMerge3()
	{
		clear();
		Integer[] list = {1, 2, 3, 4, 5, 6, 7}; // should be {1, 2, 3, 4, 5, 6} after two way merge
		Integer[] empty = new Integer[7]; 
		Experiments.twoWayMerge(list, empty, 0, 3, 7, new comparator());
	}

	@Test
	public void testThreeWayMerge()
	{
		clear();
		Integer[] list = {1, 2, 3, 4, 5, 6};
		Integer[] empty = new Integer[6]; 
		Experiments.threeWayMerge(list, empty, 0, 2, 4, 6, new comparator());
	}

	@Test
	public void testThreeWayMerge2()
	{
		clear();
		Integer[] list = {3, 4, 1, 2, 5, 6};
		Integer[] empty = new Integer[6]; 
		Experiments.threeWayMerge(list, empty, 0, 2, 4, 6, new comparator());
		//System.out.println(Experiments.comparisonCount);
	}

	@Test
	public void testThreeWayMerge3()
	{
		clear();
		Integer[] list = {3, 4, 5, 6, 1, 2};
		Integer[] empty = new Integer[6]; 
		Experiments.threeWayMerge(list, empty, 0, 2, 4, 6, new comparator());
		//System.out.println(Experiments.comparisonCount);
	}

	@Test
	public void testThreeWayMerge4()
	{
		clear();
		Integer[] list = {5, 6, 1, 2, 3, 4};
		Integer[] empty = new Integer[6]; 
		Experiments.threeWayMerge(list, empty, 0, 2, 4, 6, new comparator());
		//System.out.println(Experiments.comparisonCount);
	}


	@Test
	public void testThreeWayMerge5()
	{
		clear();
		Integer[] unsorted = {5, 6, 1, 2, 3, 4};
		Integer[] sorted = {1,2,3,4,5,6};

		Experiments.threeWayMergesort(unsorted, new comparator());
		Experiments.sortCheck(unsorted, sorted, new comparator(), true);
	}

	@Test
	public void testThreeWayMerge6()
	{
		clear();
		Integer[] list = Experiments.generateIntegers(5, 1);
		Integer[] list2 = {3,5,1,7,4};
		Integer[] empty = new Integer[5]; 
		Experiments.threeWayMerge(list2, empty, 0, 2, 4, 5, new comparator());
	}


}
