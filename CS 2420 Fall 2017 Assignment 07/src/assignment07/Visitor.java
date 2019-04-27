package assignment07;

/**
 * <p>
 * A Visitor is an object that can perform a 'visit'
 * on a Node object.  This is not otherwise defined,
 * a visit can be anything from printing out the data
 * in a node, scanning input for a node, validating
 * relative order of nodes, etc.
 * </p>
 * 
 * <p>
 * The Visitor object assumes that the Node object
 * is well-formed and that the Node is part of a
 * binary search tree.
 * </p>
 * 
 * <p>
 * Code within performVisit must not alter the tree as
 * the tree is being traversed.  Unspecified behavior
 * will occur if the tree is modified during a 
 * traversal.
 * </p>
 * 
 * @author Peter Jensen
 * @version November 5, 2017
 */
public interface Visitor<N>
{
    /**
     * Performs an action on the specified node.
     * 
     * @param n a Node to visit
     */
    void performVisit (N n);
}
