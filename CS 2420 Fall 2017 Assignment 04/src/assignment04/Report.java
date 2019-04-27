package assignment04;

import java.util.Comparator;

public class Report {
	public static class comparator implements Comparator<String> 
	{
		@Override
		public int compare(String leftHandSide, String rightHandSide) 
		{
			return Experiments.compareMixedStrings(leftHandSide, rightHandSide);
		}
	}
	public static class comparatorD implements Comparator<Double> 
	{
		@Override
		public int compare(Double leftHandSide, Double rightHandSide) 
		{
			return leftHandSide.compareTo(rightHandSide);
		}
	}

	public static void main(String[] args) {

		long startTime1 = 0;
		long endTime1 = 0;
		long durationList1 = 0;
		//#1/2
		//		for (int size = 0; size <= 30; size++)
		//        {
		//            for (int seed = 0; seed <= 900; seed++)
		//            {
		//            	
		//                String[] array = Experiments.generateMixedStrings(size, seed);
		//                startTime1+= System.currentTimeMillis();
		//                Experiments.quicksort(array, new comparator());
		//                endTime1+= System.currentTimeMillis();   
		//            }
		//            durationList1 += (endTime1 - startTime1);
		//            System.out.println(size + "\t" + (Experiments.comparisonCount / 1000) + "\t" + durationList1);
		//            Experiments.comparisonCount = 0;
		//        }

		//		for (int size = 10; size <= 1_000_000; size*=10)
		//        {
		//            for (int seed = 0; seed <= 900; seed++)
		//            {
		//            	
		//                String[] array = Experiments.generateMixedStrings(size, seed);
		//                startTime1+= System.currentTimeMillis();
		//                Experiments.quicksort(array, new comparator());
		//                endTime1+= System.currentTimeMillis();   
		//            }
		//            durationList1 += (endTime1 - startTime1);
		//            System.out.println(size + "\t" + (Experiments.comparisonCount / 1000) + "\t" + durationList1);
		//            Experiments.comparisonCount = 0;
		//        }
		//

		//#3
//				for(int n = 3; n <= 30; n++)
//				{
//					System.out.println(n * (Math.log(n)/Math.log(2)) + "\t" + n);
//				}
//				
//				for(int n = 3; n <= 30; n++)
//				{
//					System.out.println((n*(n-1))/4 + "\t" + n);
//				}

		//#4
//		for (int size = 1; size <=1_000_000; size*=10)
//		{
//			for (int seed = 0; seed <= 25; seed++)
//			{
//				String[] array = Experiments.generateMixedStrings(size, seed);
//				startTime1+= System.currentTimeMillis();
//				Experiments.quicksortWithCutoff(array, new comparator(), 156);
//				endTime1+= System.currentTimeMillis();   
//			}
//			durationList1 += (endTime1 - startTime1);
//			System.out.println(size + "\t" + (Experiments.comparisonCount)/25 + "\t" + durationList1);
//			Experiments.comparisonCount = 0;
//		}
		
		//#5
		for (int size = 1; size <=1_000_000; size*=10)
		{
			for (int seed = 0; seed <= 25; seed++)
			{
				Double[] array = Experiments.generateDouble(size, seed);
				startTime1+= System.currentTimeMillis();
				Experiments.quicksortWithCutoff(array, new comparatorD(), 8);
				endTime1+= System.currentTimeMillis();   
			}
			durationList1 += (endTime1 - startTime1);
			System.out.println(size + "\t" + (Experiments.comparisonCount)/25 + "\t" + durationList1);
			Experiments.comparisonCount = 0;
		}
	}


}