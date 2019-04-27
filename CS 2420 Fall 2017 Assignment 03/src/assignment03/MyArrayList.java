package assignment03;

import java.util.NoSuchElementException;

public class MyArrayList<E> implements UtahList<E>
{
	// Constant(s)

	final int startSize = 5;
	// Fields

	private float relativeGrowthRate;
	//private int absoluteGrowthRate;
	private E[] backingArray;
	private int newSize;
	private int arrayIndex; //helper variable to initialize the element to retrieve, used in set/getElement.
	private int firstIndex; //arbitrary starting position to be chosen
	private int firstElement; //the element that replaces the previous first index during prepend
	private int elementCounter;
	private int arrayModificationCounter;


	@SuppressWarnings("unchecked")
	public MyArrayList()
	{
		backingArray = (E[]) new Object[startSize];
		newSize = (int) (backingArray.length * this.relativeGrowthRate);
		firstIndex = 0;
	}

	/**
	 * <p>
	 * This method ensures that the backing array has enough space to store some
	 * number of elements (a desired capacity). Iff the backing array is too
	 * small, this method creates a larger backing array and copies the list
	 * elements into it. The larger backing array reference is then copied into the
	 * backing array reference, and the capacity and first element locations are updated
	 * to be correct.
	 * </p>
	 * 
	 * <p>
	 * (Note: Since list elements will be copied into a larger array, the array
	 * modification count will be significantly increased.)
	 * </p>
	 * 
	 * @param desiredCapacity
	 *            the needed capacity of the backing array
	 */
	private void ensureCapacity (int desiredCapacity)
	{
		if(backingArray.length < desiredCapacity)
		{
			newSize = (int) (backingArray.length * relativeGrowthRate);


			@SuppressWarnings("unchecked")
			E[] newBackingArray = (E[]) new Object[newSize];

			for(int i = 0; i < backingArray.length; i++)
			{		
				newBackingArray[i] = this.getElement(i);
				arrayModificationCounter++;
			}
			backingArray = newBackingArray;
		}
	}
	@Override
	/**
	 * <p>
	 * Returns an element from the list. The first element is at position 0.
	 * Valid positions are [0..size).
	 * </p>
	 * 
	 * @param index
	 *            a position in the list
	 * @return the element from that position in the list
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds
	 */
	public E getElement(int index) 
	{
		arrayIndex =  (index + firstIndex + backingArray.length) % backingArray.length;

		if(index < 0 || index >= this.size())
		{
			throw new IndexOutOfBoundsException();
		}
		return backingArray[arrayIndex];	
	}

	@Override
	/**
	 * <p>
	 * Changes an existing element in the list to the given data value. The
	 * first element is at position 0. Valid positions are [0..size).
	 * </p>
	 * 
	 * @param index
	 *            a position in the list
	 * @param data
	 *            some data value
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds
	 */
	public void setElement(int index, E data) 
	{
		arrayIndex = (index + firstIndex + backingArray.length) % backingArray.length;

		if(index < 0 || index >= this.size())
		{
			throw new IndexOutOfBoundsException();
		}
		backingArray[arrayIndex] = data;
		arrayModificationCounter++;
	}

	@Override
	/**
	 * <p>
	 * Inserts an element (some data) in the specified position in the list.
	 * Valid positions are [0..size]. Any elements at or after this position are
	 * moved to one position later in the list to make space for this element,
	 * and the element count grows by 1. If an element is inserted at the end of
	 * the list (at position <i>size</i>), it is appended to the list.
	 * </p>
	 * 
	 * @param index
	 *            a position in the list
	 * @param data
	 *            some data value
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds
	 */
	public void insert(int index, E data) 
	{
		if(index < 0 || (index > this.size()) && this.size() > 0)
		{
			throw new IndexOutOfBoundsException();
		}

		if(index == 0)
		{
			prepend(data);
		}

		else if(index == elementCounter)
		{
			append(data);
		}
		else
		{
			ensureCapacity(elementCounter+1);
			elementCounter++;
			for(int i = elementCounter-2; i >= index; i--)
			{	
				setElement(i+1, getElement(i));
			}
			setElement(index, data);
		}
	}


	@Override
	/**
	 * <p>
	 * Removes and returns an element from the list. Subsequent elements are
	 * moved to one position earlier in the list (so that there is not a hole in
	 * the list), and the element count is decreased by 1. Valid positions are
	 * [0..size).
	 * </p>
	 * 
	 * @param index
	 *            a position in the list
	 * @return the removed element
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds
	 */
	public E remove(int index) 
	{

		if(index < 0 || (index > this.size()) && this.size() > 0)
		{
			throw new IndexOutOfBoundsException();
		}

		E itemToRemove = getElement(index);
		
		if(firstIndex == arrayIndex)
		{
			firstIndex++;
			elementCounter--;
		}

		else if(index == elementCounter)
		{
			elementCounter--;
		}
		
		else
		{
			for(int i = index; i <= elementCounter-2; i++)
			{	
				setElement(i, getElement(i+1));
			}
			elementCounter--;
		}
		return itemToRemove;
	}

	@Override
	/**
	 * Returns the size of the list; returns the number of elements in the list.
	 * 
	 * @return the size of the list
	 */
	public int size() 
	{
		return elementCounter;
	}

	@Override
	/**
	 * <p>
	 * Returns the list modification count. This allows us to track and evaluate
	 * the performance of this list. (It is not normally part of a list ADT.)
	 * </p>
	 * 
	 * <p>
	 * For array-backed lists, this corresponds to the total number of array
	 * modifications (any writes to array locations). For linked lists, this
	 * corresponds to the total number of times a node is merged or removed from
	 * the list.
	 * </p>
	 * 
	 * @return a count of the number of times the backing array has changed
	 */
	public int getModificationCount() 
	{
		return arrayModificationCounter;
	}

	@Override
	/**
	 * <p>
	 * Resets (to 0) the modification count for this list.
	 * </p>
	 */
	public void resetModificationCount() 
	{
		arrayModificationCounter = 0;
	}

	/**
	 * 
	 * <p>
	 * Adds (appends) a value to the end of the list (increasing the element
	 * count). The last entry is the entry with an element index of (length of
	 * list) - 1.
	 * </p>
	 * 
	 * @param value
	 *            any double
	 */

	/*This method calls upon the ensureCapacity method so that it knows if it has to grow
	 * before placing a value at the end of the list.  The number of elements is 
	 * increased and so will its modification count which is accounted in the setElement method.
	 * The setElement method is used so that a value to the right of the last index is set
	 * to the value chosen be the user(keeps adding at the end of the list).
	 */
	private void append (E value)
	{
		ensureCapacity(elementCounter+1);
		elementCounter++;
		setElement(elementCounter-1, value); //elementCounter-1 ensures that the value after the last index is added.
	}

	/**
	 * <p>
	 * Adds (inserts) a value before the first element in the list (increasing
	 * the element count). The value becomes the first entry. The first entry is
	 * the entry with an element index of 0.
	 * </p>
	 * 
	 * @param value
	 *            any double
	 */

	/*This method also calls upon the ensureCapacity method so that it knows if it needs to
	 * grow or it can simply insert a value and it becomes the new first Index. The formula used here
	 * ensures that old first Index refers to the value inserted to be the new index which makes the
	 * old first Index, the 2nd Index. Likewise, the number of elements and the array's modification count
	 * increases since it alters the array.
	 */
	private void prepend (E value)
	{	
		ensureCapacity(elementCounter+1);
		firstElement = ((firstIndex -1) + backingArray.length) % backingArray.length;
		backingArray[firstElement] = value;		
		elementCounter++;
		firstIndex = firstElement;
		arrayModificationCounter++;
	}


	/**
	 * <p>
	 * Removes the first entry in the list (decreasing the element count). The
	 * first entry is the entry with an element index of 0.
	 * </p>
	 * 
	 * @throws NoSuchElementException
	 *             if the list is empty prior to this call
	 */

	/*Similarly, the user should not be able to remove an element from an empty array, but a
	 * NoSuchElementException is thrown if they choose to do so. This utilizes the firstElement field like
	 * the prepend method, but it provides a different formula. removeFirst ensures that the current first Index
	 * is removed and the index to the right of it claims the first Index. The number of elements is then 
	 * decreased because of the removal.
	 */
	public void removeFirst ()
	{
		if(this.size() == 0)
		{
			throw new NoSuchElementException();
		}
		firstElement = ((firstIndex + 1) + backingArray.length) % backingArray.length;
		firstIndex = firstElement;
		elementCounter--;
	}

	/**
	 * <p>
	 * Removes the last entry in the list (decreasing the element count). The
	 * last entry is the entry with an element index of (length of list) - 1.
	 * </p>
	 * 
	 * @throws NoSuchElementException
	 *             if the list is empty prior to this call
	 */

	/*This method first ensures that the user is not removing an element when there is no element
	 * to be removed in the first place.  This simply decrements the element count and the
	 * user will not be able to access that index since our array handles it to stop referring
	 * to that index.
	 */
	public void removeLast ()
	{
		if(this.size() == 0)
		{
			throw new NoSuchElementException();
		}
		elementCounter--;
	}
}
