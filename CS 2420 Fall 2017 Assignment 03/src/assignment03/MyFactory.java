package assignment03;

/**
 * <p>
 * This factory class contains methods for creating objects of some abstract
 * data type. A UtahFactory object has no state (no fields) and no constructor.
 * (The default constructor will be used when a new UtahFactory is created.)
 * Each of the factory methods builds one new object (a data structure and/or
 * one abstract data type), and returns the new object reference to the caller.
 * </p>
 * 
 * <p>
 * To use this class, build a new UtahFactory object, then call the methods to
 * build the objects (to build the data structures / abstract data types). When
 * you make a new UtahFactory object, you'll supply a type parameter. This type
 * parameter will dictate the generic type of the resulting data structures.
 * Create additional factory objects (with different type parameters) if you
 * want different types of data structures.
 * </p>
 * 
 * @author Peter Jensen
 * @version September 12, 2017
 */
public class MyFactory<E>
{
    // This class has no fields and no constructor.

    /**
     * <p>
     * Builds and returns a reference to an object that implements the UtahList
     * interface and that satisfies all associated contracts. The returned
     * object is guaranteed to have performance equivalent to an array list:
     * O(1) time cost for insertions or removals to either end of the list, O(n)
     * time cost for removals or insertions in the middle of the list, and O(1)
     * time cost for get/set operations.
     * </p>
     * 
     * @return a valid UtahList object with performance equivalent to an array
     *         list
     */
    public UtahList<E> makeArrayList ()
    {
        throw new RuntimeException("This method has not yet been written by the student.");
    }

    /**
     * <p>
     * Builds and returns a reference to an object that implements the UtahList
     * interface and that satisfies all associated contracts. The returned
     * object is guaranteed to have performance equivalent to a doubly-linked
     * list: O(1) time cost for insertions or removals to either end of the
     * list, O(n) time cost for removals or insertions in the middle of the
     * list, and O(n) time cost for get/set operations.
     * </p>
     * 
     * @return a valid UtahList object with performance equivalent to a linked
     *         list
     */
    public UtahList<E> makeLinkedList ()
    {
        throw new RuntimeException("This method has not yet been written by the student.");
    }

    /**
     * <p>
     * Builds and returns a reference to an object that implements the UtahStack
     * interface and that satisfies all associated contracts. The new UtahStack
     * object will use the given backing list for storage, and the performance
     * for the UtahStack will be identical to the performance of the backing
     * list.
     * </p>
     * 
     * <p>
     * If the backing list has elements in it, the existing elements will be the
     * elements in the stack, with the top of the stack being the element at
     * position 0 in the list, and the bottom of the stack being the last
     * element in the list. (The backing list is used as-is.)
     * </p>
     * 
     * @param backinglist
     *            any UtahList
     * @return a valid UtahStack object that uses the given list for storage
     */
    public UtahStack<E> makeStack (UtahList<E> backingList)
    {
        throw new RuntimeException("This method has not yet been written by the student.");
    }

    /**
     * <p>
     * Builds and returns a reference to an object that implements the UtahQueue
     * interface and that satisfies all associated contracts. The new UtahQueue
     * object will use the given backing list for storage, and the performance
     * for the UtahQueue will be identical to the performance of the backing
     * list.
     * </p>
     * 
     * <p>
     * If the backing list has elements in it, the existing elements will be the
     * elements in the queue, with the front of the queue being the element at
     * position 0 in the list. (The backing list is used as-is.)
     * </p>
     * 
     * @param backingList
     *            any UtahList
     * @return a valid UtahQueue object that uses the given list for storage
     */
    public UtahQueue<E> makeQueue (UtahList<E> backingList)
    {
        throw new RuntimeException("This method has not yet been written by the student.");
    }

}
