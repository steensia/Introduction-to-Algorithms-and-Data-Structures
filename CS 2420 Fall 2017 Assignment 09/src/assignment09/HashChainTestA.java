package assignment09;

public class HashChainTestA {

	public static void main(String[] args) 
	{
		HashTableChain hash = new HashTableChain();
		for(int i = 0; i <= 10000; i++)
		{
			hash.insert(Math.random());
			if(i % 100 == 0)
			{
				System.out.println("E: " + hash.entries() + "\t" + "P: " + hash.probes() + "\t" + "L: " 
			+ (double)hash.entries()/hash.size() + "\t" + "A: " + (double)hash.probes()/hash.entries());
			}
		}
	}

}
