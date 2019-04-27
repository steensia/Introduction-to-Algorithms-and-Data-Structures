package assignment09;

public class HashTestB2 {

	public static void main(String[] args) 
	{
		HashTable hash = new HashTable();
		hash.insert(3.13);
		for(int i = 0; i < 4999; i++)
		{
			hash.insert(Math.random());
		}
		hash.insert(3.14);
		for(int i = 5000; i < 10_000; i++)
		{
			hash.insert(Math.random());
		}
		hash.insert(3.15);
	
		System.out.println(hash.search(3.13) + " Searches: " + hash.searches());
		System.out.println(hash.search(3.14) + " Searches: " + hash.searches());
		System.out.println(hash.search(3.15) + " Searches: " + hash.searches());
	}
}
