package assignment07;

/**
 * <p>
 * A simple visitor class that prints out data
 * from visited nodes.  This is for an example only.
 * I use lambda expressions in my test code to
 * construct this class more easily.  (Lambdas
 * help implement simple interfaces easily.)
 * </p>
 * 
 * @author Peter Jensen
 * @version November 5, 2017
 */
public class PrintVisitor<E> implements Visitor<Node<E>>
{
    /**
     * <p>
     * Prints out the data in the specified node, followed
     * by a single space character.
     * </p>
     * 
     * @param n a Node to visit
     */
    public void performVisit (Node<E> n)
    {
        System.out.print(n.data + " ");
    }

}
