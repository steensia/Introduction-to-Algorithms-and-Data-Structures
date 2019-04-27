package assignment03;

/**
 * <p>
 * This interface describes a simple stack abstract data type. A stack is
 * analogous to a pile of papers on your desk. You put (push) new papers onto
 * the top of the pile, and you remove (pop) the top paper from the pile. This
 * is a last-in, first-out data structure.
 * </p>
 * 
 * @author Peter Jensen
 * @version September 12, 2017
 */
public interface UtahStack<E>
{
	/**
	 * Puts a data element on the top of the stack.
	 * 
	 * @param data
	 *            an element to add to the top of the stack
	 */
	public void push (E data);

	/**
	 * Removes and returns the top element from the stack.
	 * 
	 * @return the data from the top of the stack
	 * @throws NoSuchElementException
	 *             if the stack is empty
	 */
	public E pop ();

	/**
	 * <p>
	 * Returns the number of elements in the stack.
	 * </p>
	 * 
	 * @return the size of the stack
	 */
	public int size ();

}
