package assignment02;


import java.util.NoSuchElementException;

/**
 * <p>
 * A rolling list is just a list ADT backed by an array with performance roughly
 * equivalent or superior to a linked list for most operations.  This list
 * implementation can only hold double values.
 * </p>
 * 
 * <p>
 * This list ADT has a restricted set of operations: You can view and modify any
 * element of the list, but you can only add or remove elements from the front
 * or back end of the list.
 * </p>
 * 
 * <p>
 * The size and growth rate of the backing array is determined when each rolling
 * list is constructed. The initial size of the backing array is always 5. When
 * an element is added to the list, but the backing array is full, a new, larger
 * backing array is created and the elements are copied from the old array to
 * the new array. The size of the new backing array is determined as follows:
 * </p>
 * 
 * <p>
 * <i>newSize = ceiling(oldSize * relativeGrowthRate + absoluteGrowthRate)</i>
 * </p>
 * 
 * <p>
 * This class also keeps track of how many times modifications are made to the
 * backing array, and it provides static methods for getting and resetting this
 * count. (By keeping track of the number of times something was written into an
 * array, students can estimate the complexity of their solution.) As an absolute
 * rule, no unnecessary modifications should be made to the backing array.
 * </p>
 * 
 * <p>
 * For assignment 02, this class has additional specific implementation
 * requirements. Please review assignment 02 for details.
 * </p>
 * 
 * @author --- Steen Sia ---
 * @version --- September 9, 2017 ---
 */
public class RollingList
{
	// Constant(s)
	
	final int startSize = 5;

	// Fields

	private float relativeGrowthRate;
	private int absoluteGrowthRate;
	private double[] backingArray;
	private int newSize;
	private int arrayIndex; //helper variable to initialize the element to retrieve, used in set/getElement.
	private int firstIndex; //arbitrary starting position to be chosen
	private int firstElement; //the element that replaces the previous first index during prepend
	private int elementCounter;
	private int arrayModificationCounter;


	// Constructor

	/**
	 * <p>
	 * Creates a new rolling list with no elements, a backing array of size 5,
	 * and the specified growth rate. The growth rate parameters must ensure
	 * that the backing array always grows when needed.
	 * </p>
	 * 
	 * @param relativeGrowthRate
	 *            the growth rate multiplier
	 * @param absoluteGrowthRate
	 *            the growth rate constant
	 * @throws IllegalArgumentException
	 *             iff the growth rate does not ensure growth of the backing
	 *             array
	 */
	
	/*This constructor initializes the backing array which is the Data Structure
	 * that will be used for our Rolling List ADT that is restricted to doubles.  All the necessary 
	 * fields must be initialized and an arbitrary starting index will be chosen. The newSize is created
	 * but specific conditions must be met such that the array will grow bigger than the previous 
	 * array.  If these conditions prevent the array to grow, an IllegalArgumentException
	 * is thrown.
	 */
	public RollingList (float relativeGrowthRate, int absoluteGrowthRate)
	{
		this.relativeGrowthRate = relativeGrowthRate;
		this.absoluteGrowthRate = absoluteGrowthRate;
		this.backingArray = new double[startSize];
		this.firstIndex = 4;
		this.newSize = (int) Math.ceil(backingArray.length * this.relativeGrowthRate + this.absoluteGrowthRate);

		if(relativeGrowthRate < 1)
		{
			throw new IllegalArgumentException();
		}
		if(relativeGrowthRate == 1 && absoluteGrowthRate <= 0)
		{
			throw new IllegalArgumentException();
		}
		if(this.newSize <= 5)
		{
			throw new IllegalArgumentException();
		}
	}

	// Private utility methods

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
	
	/*This method utilizes newSize formula and creates a new array according to that value. However, the 
	 * condition must be met that the desiredCapacity exceeds the array's length.  A loop goes through to inspect 
	 * and copy all the elements in the previous array into the bigger array using the getElement method. During 
	 * this process, there is a modification every time an element is copied. Then, we must set the firstIndex 
	 * to 0 since the getElement method organizes the elements in order. Finally, the old backingArray 
	 * becomes the new backingArray since we want to keep referring to that backing Array. 
	 */
	private void ensureCapacity (int desiredCapacity)
	{
		if(backingArray.length < desiredCapacity)
		{
			newSize = (int) Math.ceil(backingArray.length * this.relativeGrowthRate + this.absoluteGrowthRate);
			double[] newBackingArray = new double[newSize];

			for(int i = 0; i < backingArray.length; i++)
			{		
				newBackingArray[i] = this.getElement(i);
				arrayModificationCounter++;
			}
			firstIndex = 0;
			backingArray = newBackingArray;
		}
	}

	// Supported list ADT operations.

	/**
	 * <p>
	 * Retrieves a value from the list.
	 * </p>
	 * 
	 * @param i
	 *            the element to retrieve
	 * @return 
	 *            the value of the specified element
	 * @throws IndexOutOfBoundsException
	 *  	      if the index i is out of bounds        
	 */
	
	/*This method utilizes the arrayIndex formula such that user will be able to retrieve any element
	 * from the list, but behind the scenes in our backing array it is like a spinning wheel that simply
	 * wraps around the elements. This method throws an IndexOutOfBoundsException when the user tries to
	 * retrieve, i.e. the 4th element of the array when it only has 3 elements. Similarly, they should not
	 * be accessing a negative index.
	 */
	public double getElement (int i)
	{ 
		arrayIndex =  (i + firstIndex + backingArray.length) % backingArray.length;

		if(i < 0 || i >= this.size())
		{
			throw new IndexOutOfBoundsException();
		}
		return backingArray[arrayIndex];	
	}

	/**
	 * <p>
	 * Changes an element's value in this list. Note: This will increase the
	 * array modification count.
	 * </p>
	 * 
	 * @param i
	 *            the element to change
	 * @param value
	 *            the new value (any double) for this element
	 * @throws IndexOutOfBoundsException
	 *  	      if the index i is out of bounds
	 */
	
	/*This method works similarly to the getMethod in which it utilizes the arrayIndex formula
	 * but instead of retrieving the value, it is simply replaces its value chosen by the user.
	 * Likewise, an IndexOutOfBoundsException is thrown when a user tries to replace an element
	 * that is above its number of elements or below 0. 
	 */
	public void setElement (int i, double value)
	{
		arrayIndex = (i + firstIndex + backingArray.length) % backingArray.length;

		if(i < 0 || i >= this.size())
		{
			throw new IndexOutOfBoundsException();
		}
		backingArray[arrayIndex] = value;
		arrayModificationCounter++;
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
	public void append (double value)
	{
		ensureCapacity(elementCounter+1);
		elementCounter++;
		this.setElement(elementCounter-1, value); //elementCounter-1 ensures that the value after the last index is added.
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
	public void prepend (double value)
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
	 * Returns the number of elements in this rolling list.
	 * </p>
	 * 
	 * @return 
	 *         the number of elements in this list
	 */
	
	/*This method simply returns the number of elements in the array. */
	public int size ()
	{
		return elementCounter;
	}

	// Public utility methods (instrumentation)

	/**
	 * <p>
	 * Clears (sets to 0) the array modification count.
	 * </p>
	 */    
	
	/*This method sets the array modification counter to its default of 0*/
	public void resetArrayModificationCount ()
	{
		arrayModificationCounter = 0;
	}

	/**
	 * <p>
	 * Returns the array modification count.
	 * </p>
	 * 
	 * @return 
	 *         a count of the number of times the backing array has changed
	 */
	
	/*This method returns the amount of times the array has been modified
	 * This includes the amount of times a value has been prepended, appended, 
	 * and copied when the array grows.
	 */
	public int getArrayModificationCount ()
	{
		return arrayModificationCounter;
	}

	/**
	 * <p>
	 * Returns the percentage of the backing array that is unused. Return values
	 * will be between [0.0 and 1.0].
	 * </p>
	 * 
	 * @return 
	 *         the percentage of the backing array that is wasted space
	 */
	
	/*This method simply returns the percentage of the backing array that is not used
	 * One subtracted by the number of elements over its length.
	 */
	public double wastedSpace ()
	{
		return 1.0 - ((double) elementCounter / backingArray.length) ;
	}
}
