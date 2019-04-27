package assignment07;

/**
 * <p>
 * Objects built from this class represent generic
 * binary tree nodes.</p>
 * 
 * <p>
 * Students are required to use only this Node class in
 * their solution.  Students are not allowed to change any 
 * definitions in this file.  When you submit your solution,
 * we will copy in our own version of this file.  (Our
 * version may contain additional methods for testing.)
 * </p> 
 * 
 * <p>
 * Normally this Node class would be a nested class in a tree class.
 * I've moved it out for our testing purposes.  All the methods
 * and fields are set at package visibility, they can be used anywhere 
 * within package assignment07.
 * </p> 
 * 
 * @author Peter Jensen
 * @version November 5, 2017
 */
class Node<E>
{
    /* Fields - no changes allowed. */
    
    E       data;
    
    Node<E> parent;
    Node<E> left, right;
    
    int     height;  // The height of this subtree
    
    /* Methods - no changes allowed. */
    
    /**
     * <p>
     * Builds a node with the specified data.  The node is not
     * linked into any tree (all references null), and must be
     * manually linked in by the caller.
     * </p>
     * 
     * @param data data for this node
     */
    Node(E data)
    {
        this.data = data;
    }
    
    
    /**
     * <p>
     * Performs a pre-order traversal of the nodes in this
     * subtree.  The performVisit method in the visitor
     * object will be called once per visited node.
     * </p>
     * 
     * @param v a visitor object (with a performVisit method)
     */
    void visitPreOrder (Visitor<Node<E>> v)
    {
        v.performVisit(this);
        
        if (this.left != null)
            this.left.visitPreOrder (v);

        if (this.right != null)
            this.right.visitPreOrder (v);
    }
    
    /**
     * <p>
     * Performs an in-order traversal of the nodes in this
     * subtree.  The performVisit method in the visitor
     * object will be called once per visited node.
     * </p>
     * 
     * @param v a visitor object (with a performVisit method)
     */
    void visitInOrder (Visitor<Node<E>> v)
    {        
        if (this.left != null)
            this.left.visitInOrder (v);

        v.performVisit(this);
        
        if (this.right != null)
            this.right.visitInOrder (v);       
    }
    
    /**
     * <p>
     * Performs a post-order traversal of the nodes in this
     * subtree.  The performVisit method in the visitor
     * object will be called once per visited node.
     * </p>
     * 
     * @param v a visitor object (with a performVisit method)
     */
    void visitPostOrder (Visitor<Node<E>> v)
    {
        if (this.left != null)
            this.left.visitPostOrder (v);

        if (this.right != null)
            this.right.visitPostOrder (v);
        
        v.performVisit(this);        
    }
}
