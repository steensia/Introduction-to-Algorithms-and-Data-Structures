package assignment09;

public class HashTable 
{
	// Fields

	private Double[] arr;
	private int maxSize;
	private Double key;

	// Statistics

	private int probeCount;
	private int entryCount;
	private int searchCount;

	public HashTable()
	{
		maxSize = 1031024 / 100;
		arr = new Double[maxSize];
	}

	public void insert(Double key)
	{
		int pos = (key.hashCode() % maxSize + maxSize) % maxSize;
		int offset = 0;
		while(arr[pos] != null && arr[(pos + offset) % maxSize] != null)
		{
			offset++;
			probeCount++;
		}
		arr[((pos + offset) % maxSize)] = key;
		probeCount++;
		entryCount++;
	}

	public Double search(Double target)
	{
		int h = target.hashCode();
		int pos;
		for(int i = 0; i < maxSize; i ++)
		{
			pos = ((h + i) % maxSize + maxSize) % maxSize;
			if(arr[pos] == null)
			{
				searchCount++;
				probeCount++;
				return null;
			}
			if(target.equals(arr[pos]))
			{
				searchCount++;
				probeCount++;
				return arr[pos];
			}
			searchCount++;
			probeCount++;
		}
		return null;
	}

	public int size()
	{
		return arr.length;
	}
	public int probes()
	{
		return probeCount;
	}

	public int searches()
	{
		return searchCount;
	}
	public int entries()
	{
		return entryCount-1;
	}
}