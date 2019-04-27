package assignment09;

public class HashChainTestB {

	public static void main(String[] args) 
	{
		HashTableChain hash = new HashTableChain();
		for(int i = 0; i <= 4999; i++)
		{
			hash.insert(Math.random());
			hash.search(Math.random());
			if(i % 100 == 0)
			{
				System.out.println("E: " + hash.entries() + "\t" + "P: " + hash.probes() + "\t" +  "L: " 
						+ (double)hash.entries()/hash.size() + "\t" + "S: " + hash.searches() + "\t" + "A: " + (double)hash.searches()/hash.entries());
			}
		}
		for(int i = 5000; i <= 10000; i++)
		{
//			Double x = Math.random();
//			hash.insert(x);
//			hash.search(x);
			hash.insert(Math.random());
			hash.search(Math.random());
			if(i % 100 == 0)
			{
				System.out.println("E: " + hash.entries() + "\t" + "P: " + hash.probes() + "\t" +  "L: " 
						+ (double)hash.entries()/hash.size() + "\t" + "S: " + hash.searches() + "\t" + "A: " + (double)hash.searches()/hash.entries());
			}
		}
	}
}
