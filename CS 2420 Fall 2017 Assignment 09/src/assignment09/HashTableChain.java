package assignment09;

import java.util.LinkedList;

public class HashTableChain 
{
	// Fields

	private Node[] arr;
	private int maxSize;

	private class Node
	{
		private Node next;
		Double key;

		public Node(Double key)
		{
			this.next = null;
			this.key = key;
		}
	}
	// Statistics

	private int probeCount;
	private int entryCount;
	private int searchCount;

	public HashTableChain()
	{
		maxSize = 1031024 / 100;
		arr = new Node[maxSize];
	}

	public void insert(Double element)
	{
		int pos = (element.hashCode() % maxSize + maxSize) % maxSize;
		Node current = arr[pos];
		
		if(current == null)
		{
			arr[pos] = new Node(element);
			entryCount++;
			probeCount++;
		}
		else
		{
			while(current.next != null)
			{
				current = current.next;
				probeCount++;
			}
			current.next = new Node(element);
			entryCount++;
			probeCount++;
		}
	}

	public Double search(Double target)
	{
		int h = target.hashCode();
		for(int i = 0; i < maxSize; i ++)
		{
			int pos = ((h + i) % maxSize + maxSize) % maxSize;
			Node current = arr[pos];
			
			while(current != null)
			{
				if(current.key.equals(target))
				{
					searchCount++;
					probeCount++;
					return arr[pos].key;
				}
				current = current.next;
				searchCount++;
				probeCount++;
			}
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