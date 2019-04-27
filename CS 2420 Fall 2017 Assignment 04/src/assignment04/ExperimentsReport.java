package assignment04;

import java.util.Comparator;

public class ExperimentsReport {

	public static class comparator implements Comparator<String> 
	{
		@Override
		public int compare(String leftHandSide, String rightHandSide) 
		{
			return Experiments.compareMixedStrings(leftHandSide, rightHandSide);
		}
	}

	final static int sC = 49;
//	static long startTime = System.nanoTime();
	static long startTime = System.currentTimeMillis();
	public static void main(String[] args) 
	{
		for(int size = 0; size <= 30; size++)
		
		//for(int size = 0; size <= 30; size++)
		{
			//for(int seed = 0; seed < sC; seed++)
			for(int seed = 0; seed < sC; seed++)	
			{
				String[] data = Experiments.generateMixedStrings(size, seed);
				Experiments.quicksort(data, new comparator());
			}
			long elapsedTime = System.currentTimeMillis() - startTime;
			System.out.println("Size: " + size + "CC:" + Experiments.comparisonCount + "\t" + " Time: " + elapsedTime);
		}
		
//		for(int size = 10; size <= 10_000_000; size*=10)
//		{
//			for(int seed = 0; seed < sC; seed++)
//			{
//				String[] data = Experiments.generateMixedStrings(size, seed);
//				Experiments.quicksort(data, new comparator());
//			}
//			long elapsedTime = System.currentTimeMillis() - startTime;
//			System.out.println("Size " + size + ":" + "\t" + "CC:" + Experiments.comparisonCount/sC + "\t" + " Time: " + elapsedTime);
//		}
	}
	
//	int counter = 0;
//    while(true)
//    {
//        if(counter > 40)
//            break;
//    long average = 0;
//    long average2 = 0;
//    for(int j = 0; j < 200; j ++)
//    {
//        Integer[] sortedArray = Experiments.generateIntegers(1_000_000, (int)(Math.random()30));
//        long milli = System.currentTimeMillis()10;
//        Experiments.quicksortWithCutoff(sortedArray,  new comparableInts(), counter);
//        milli = System.currentTimeMillis()*10 - milli;
//        long count = Experiments.comparisonCount;
//        average += milli;
//        average2 += count;
//        //System.out.println("QuickSort: " + counter + " : "+ Experiments.comparisonCount);
//        Experiments.comparisonCount -= count;
//        //System.out.println("QuickSort: " + counter + " : "+ Experiments.comparisonCount);
//    }
//    System.out.println(counter + "\t" + average2/200 + "\t" + average/200);
//    average = 0;
//    average2 = 0;
//    counter ++;
//    }
	

}
