package assignment03;

/**
 * <p>
 * This interface describes a simple queue ADT. A queue of elements in analogous
 * to a line at the store: You enter the line at the back (enqueue), and you
 * exit the line at the front (dequeue). This is a first-in, first-out data
 * structure.
 * </p>
 * 
 * @author Peter Jensen
 * @version September 12, 2017
 */
public interface UtahQueue<E>
{
	/**
	 * <p>
	 * Adds an element to the end of the queue.
	 * </p>
	 * 
	 * @param data
	 *            some data to put in the queue
	 */
	public void enqueue (E data);

	/**
	 * <p>
	 * Removes and returns the data from the front of the queue; removes and
	 * returns the element that has been in the queue the longest.
	 * </p>
	 * 
	 * @return the data that was removed from the queue
	 * @throws NoSuchElementException
	 *             if the queue is empty
	 */
	public E dequeue ();

	/**
	 * <p>
	 * Returns the number of queued elements.
	 * </p>
	 * 
	 * @return the size of the queue
	 */
	public int size ();

}
