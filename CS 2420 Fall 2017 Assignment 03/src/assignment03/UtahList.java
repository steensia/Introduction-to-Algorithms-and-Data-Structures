package assignment03;

/**
 * <p>
 * This interface describes a simple list abstract data type. A UtahList is a
 * list ADT that also tracks the number of modifications required to perform
 * various list operations.
 * </p>
 * 
 * @author Peter Jensen
 * @version September 12, 2017
 */
public interface UtahList<E>
{
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
    public E getElement (int index);

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
    public void setElement (int index, E data);

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
    public void insert (int index, E data);

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
    public E remove (int index);

    /**
     * Returns the size of the list; returns the number of elements in the list.
     * 
     * @return the size of the list
     */
    public int size ();

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
    public int getModificationCount ();

    /**
     * <p>
     * Resets (to 0) the modification count for this list.
     * </p>
     */
    public void resetModificationCount ();
}
