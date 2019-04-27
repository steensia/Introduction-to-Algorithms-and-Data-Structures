package assignment07;

import java.util.*;

/**
 * <p>
 * A generic implementation of a binary search tree. The search tree stores
 * references to data objects. This search tree allows and keeps multiple
 * 'equal' data objects with different or identical references. (This search
 * tree is suitable for implementing multimaps.)
 * </p>
 * 
 * <p>
 * When the search tree is constructed, a comparator must be provided for
 * comparing data values.
 * </p>
 * 
 * <p>
 * Data objects must be immutable (with respect to comparisons.)
 * </p>
 * 
 * <p>
 * Students will adapt this code so that the search tree implementation uses an
 * AVL tree data structure. This will guarantee O(lg(n)) performance for add,
 * find, and remove operations. (The default implementation only guarantees O(n)
 * performance).
 * </p>
 * 
 * @author Peter Jensen and (Steen Sia and Tom Nguyen)
 * @version November 5, 2017 (11/18/17)
 */
public class SearchTree<E> {
	/* Fields - students may not add fields */

	private Node<E> root;
	private int size;

	private Comparator<E> comparator;
	private boolean keepBalanced;

	/*
	 * Comparison count is an internal detail. I've set it to package level
	 * visibility.
	 */

	static int comparisonCount; // I didn't bother with a count()
	// method this time.

	/*
	 * Methods - students may add private helper methods, but must not remove or
	 * change the headers or contracts of the public methods.
	 */

	/**
	 * <p>
	 * Constructs an empty search tree. The caller must provide a comparator
	 * object for comparing data values of type E (whatever type that is).
	 * </p>
	 * 
	 * @param comparator
	 *            a comparator for comparing data values
	 * @param keepBalanced
	 *            if true, the tree will be an AVL tree
	 */
	public SearchTree(Comparator<E> comparator, boolean keepBalanced) {
		// Save the comparator for future use.

		this.comparator = comparator;
		this.keepBalanced = keepBalanced;

		// These would normally be the defaults. I'm setting
		// these explicitly for clarity. The tree starts empty,
		// and an empty tree has no nodes.

		this.root = null;
		this.size = 0;
	}

	/**
	 * <p>
	 * Returns the number of entries in this search tree.
	 * </p>
	 * 
	 * @return the size of the tree
	 */
	public int getSize() {
		return size;
	}

	/**
	 * <p>
	 * Returns the height of this tree (-1 if the tree is empty).
	 * </p>
	 *
	 * <p>
	 * This method is provided for testing only, as it exposes the internals of
	 * the tree. Normally, a collection class would fully hide its
	 * implementation details. I'm exposing them for testing and debugging
	 * purposes.
	 * </p>
	 * 
	 * <p>
	 * Because this method is for testing only, I've set its visibility to
	 * package level. It can be used (only) within any other class in package
	 * assignment07.
	 * </p>
	 * 
	 * @return the height of this tree
	 */
	int getHeight() {
		return root == null ? -1 : root.height;
	}

	/**
	 * <p>
	 * Performs a pre-order traversal of the nodes in this tree. The
	 * performVisit method in the visitor object will be called once per visited
	 * node.
	 * </p>
	 * 
	 * <p>
	 * This method is provided for testing only, as it exposes the internals of
	 * the tree. Normally, a collection class would fully hide its
	 * implementation details. I'm exposing them for testing and debugging
	 * purposes.
	 * </p>
	 * 
	 * <p>
	 * Because this method is for testing only, I've set its visibility to
	 * package level. It can be used (only) within any other class in package
	 * assignment07.
	 * </p>
	 * 
	 * @param v
	 *            a visitor object (with a performVisit method)
	 */
	void visitPreOrder(Visitor<Node<E>> v) {
		// Let the node class do the work recursively.

		if (root != null)
			root.visitPreOrder(v);
	}

	/**
	 * <p>
	 * Performs an in-order traversal of the nodes in this tree. The
	 * performVisit method in the visitor object will be called once per visited
	 * node.
	 * </p>
	 * 
	 * <p>
	 * This method is provided for testing only, as it exposes the internals of
	 * the tree. Normally, a collection class would fully hide its
	 * implementation details. I'm exposing them for testing and debugging
	 * purposes.
	 * </p>
	 * 
	 * <p>
	 * Because this method is for testing only, I've set its visibility to
	 * package level. It can be used (only) within any other class in package
	 * assignment07.
	 * </p>
	 * 
	 * @param v
	 *            a visitor object (with a performVisit method)
	 */
	void visitInOrder(Visitor<Node<E>> v) {
		// Let the node class do the work recursively.

		if (root != null)
			root.visitInOrder(v);
	}

	/**
	 * <p>
	 * Performs a post-order traversal of the nodes in this tree. The
	 * performVisit method in the visitor object will be called once per visited
	 * node.
	 * </p>
	 * 
	 * <p>
	 * This method is provided for testing only, as it exposes the internals of
	 * the tree. Normally, a collection class would fully hide its
	 * implementation details. I'm exposing them for testing and debugging
	 * purposes.
	 * </p>
	 * 
	 * <p>
	 * Because this method is for testing only, I've set its visibility to
	 * package level. It can be used (only) within any other class in package
	 * assignment07.
	 * </p>
	 * 
	 * @param v
	 *            a visitor object (with a performVisit method)
	 */
	void visitPostOrder(Visitor<Node<E>> v) {
		// Let the node class do the work recursively.

		if (root != null)
			root.visitPostOrder(v);
	}

	/**
	 * <p>
	 * Returns a list of all data object references stored in this binary search
	 * tree that are equal to the target data object. The list may be empty.
	 * Equality is determined using the tree's comparator on each data object.
	 * </p>
	 * 
	 * <p>
	 * Complexity is O(lg(size)) for single matches. For multiple matches, the
	 * complexity will be the larger of O(lg(size)) and the match count.
	 * </p>
	 * 
	 * @param target
	 *            the data to find in the tree
	 * @return a list of matching data objects
	 */
	public List<E> find(E target) {
		List<E> results = new ArrayList<E>();

		// If the root is not null, use a recursive helper
		// function to find the matching data.

		if (root != null)
			find(root, target, results);

		return results;
	}

	/**
	 * <p>
	 * Recursively finds data in this subtree that matches the target, and adds
	 * matching data object references to the results list.
	 * </p>
	 * 
	 * @param current
	 *            a root node of some subtree
	 * @param target
	 *            a data value to search for
	 * @param results
	 *            the list to add data values to
	 * @throws NullPointerException
	 *             the target is null or the current node is null
	 */
	private void find(Node<E> current, E target, List<E> results) {
		int compareResults = comparator.compare(current.data, target);
		comparisonCount++;

		if (compareResults == 0)
			results.add(current.data);

		if (compareResults <= 0 && current.right != null)
			find(current.right, target, results);

		if (compareResults >= 0 && current.left != null)
			find(current.left, target, results);
	}

	/**
	 * <p>
	 * Adds the given data object to this search tree. The data object must not
	 * be null.
	 * </p>
	 * 
	 * <p>
	 * This method returns in O(lg(size)) time.
	 * </p>
	 * 
	 * @param data
	 *            any non-null data object.
	 * @throws IllegalArgumentException
	 *             if the data is null
	 */
	public void add(E data) {
		if (data == null)
			throw new IllegalArgumentException();

		// Keep the size up-to-date.

		size++;

		// If the tree is empty, just make a node and bail out.

		if (root == null) {
			root = new Node<E>(data); // Height is 0, OK.
			return;
		}

		// Locate a node with a free child slot for this data.
		// (Non-recursive, no specific reason.)

		Node<E> current = root;

		while (true) {
			int compareResults = comparator.compare(current.data, data);
			comparisonCount++;

			// Does it belong to the left?

			if (compareResults > 0) {
				// Is the left empty?
				if (current.left == null) {
					// Yes, add it there.
					current.left = new Node<E>(data);
					current.left.parent = current;
					updateNode(current);
					return;
				} else
					current = current.left; // No, go deeper.
			} else // It belongs to the right. Is the right empty?
				if (current.right == null) {
					// Yes, add it there.
					current.right = new Node<E>(data);
					current.right.parent = current;
					updateNode(current);
					return;
				} else
					current = current.right; // No, go deeper.
		}
	}

	/**
	 * <p>
	 * Removes the first (in traversal order) data entry that equals the target.
	 * A reference to the removed data entry is returned. The target must not be
	 * null.
	 * </p>
	 * 
	 * <p>
	 * This method returns in O(lg(size)) time.
	 * </p>
	 * 
	 * @param targetValue
	 *            any non-null data object value
	 * @return the removed data entry, or null if none
	 * @throws NullPointerException
	 *             if the tree is nonempty and the target is null
	 */
	public E removeValue(E targetValue) {
		// Locate the node with matching data value.

		Node<E> current = root;
		Node<E> match = null;
		while (current != null) {
			int compareResults = comparator.compare(current.data, targetValue);
			comparisonCount++;

			if (compareResults == 0)
				match = current; // Found one, but look for earlier ones

			if (compareResults <= 0)
				current = current.right;
			else
				current = current.left;
		}

		if (match == null)
			return null; // Not found.

		// Remove it. (Use a helper function to finish the work.)

		return remove(match);
	}

	/**
	 * <p>
	 * Removes the data in specified node from the search tree, returns a
	 * reference to the data in the node. Note that the node reference may
	 * remain in the tree, but the data in the node will be removed. (Other data
	 * may be copied into this node during the removal process.) The tree size
	 * and all changed heights are updated.
	 * </p>
	 * 
	 * @param doomed
	 *            the node containing data to remove
	 * @return the removed data entry, or null if none
	 * @throws NullPointerException
	 *             if the node is null
	 */
	private E remove(Node<E> doomed) {
		// In cases where the node has zero or one children,
		// unlink the node and return the data.

		if (doomed.left == null || doomed.right == null) {
			// Update the size.

			size--;

			// Get a reference to the only child.

			Node<E> soleChild; // Might be null after initialization.

			if (doomed.right == null)
				soleChild = doomed.left;
			else
				soleChild = doomed.right;

			// Point the node's parent to this only child.

			if (doomed.parent != null) {
				if (doomed.parent.left == doomed)
					doomed.parent.left = soleChild;
				else
					doomed.parent.right = soleChild;
			} else
				root = soleChild;

			// Point the child to the parent of the removed node.

			if (soleChild != null)
				soleChild.parent = doomed.parent;

			// The node is now unlinked. Update the heights
			// in the parent and return the data.

			updateNode(doomed.parent);

			return doomed.data;
		}

		// The node has two children. Instead of removing
		// the node, just copy up data from deeper in the
		// tree (and then remove that deeper node).

		// Locate the immediate predecessor data in the tree.

		Node<E> predecessor = doomed.left;
		while (predecessor.right != null)
			predecessor = predecessor.right;

		// Remove it, but store its data in this node.
		// Finally, return the removed data.
		// (Note that this will decrease size for us.)

		return doomed.data = remove(predecessor);
	}

	/**
	 * <p>
	 * Updates (fixes) the height or balance of the subtree rooted at the
	 * specified node (current). It is assumed that the node may be currently
	 * incorrect -- that the height and/or balance of the node is wrong.
	 * </p>
	 * 
	 * <p>
	 * When a child node changes its height or balance, the parent's height or
	 * balance may need to change as well. This method is designed to simplify
	 * that process. After a child has changed, call this method on the parent
	 * to ensure the parent has the correct height or balance.
	 * </p>
	 * 
	 * <p>
	 * Note that this method may initiate recursion. If the height or balance of
	 * the node changes, then the node's parent also needs to be updated.
	 * Calling this method is O(lg(n)).
	 * </p>
	 * 
	 * <p>
	 * It is OK to send a null node as a parameter, it is ignored.
	 * </p>
	 *
	 * @param current
	 *            a node who's height or balance may need to be updated
	 */
	private void updateNode(Node<E> current) {
		// Skip null nodes.

		if (current == null)
			return;

		// Just let a (possibly recursive) helper function do all the work.

		if (keepBalanced)
			updateBalances(current); // Students may change this call.
		else
			updateHeights(current); // Leave this one in place.

		// Skipped in solution

		while (current != null && fixHeight(current))
			current = current.parent;

		// Debugging/testing function
		// I've left it in and you may use it during testing. You
		// should comment it out prior to submitting, but this is not required.

		checkStructure();
	}

	/**
	 * <p>
	 * Updates the height in a node, and possibly in the parents of the node.
	 * (See updateNode.)
	 * </p>
	 * 
	 * <p>
	 * This method is recursive, O(lg(n)).
	 * </p>
	 * 
	 * @param current
	 *            a node that may need updating
	 */
	private void updateHeights(Node<E> current) {
		// Fix the height. If it changes, fix the height of the parent.

		if (fixHeight(current))
			updateHeights(current.parent);
	}

	/**
	 * <p>
	 * Ensures that the height field in the given Node is correct. (The heights
	 * of the children are retrieved but not checked.) If the height changed,
	 * true is returned. If the height was correct before the call, false is
	 * returned.
	 * </p>
	 * 
	 * <p>
	 * This method is -not- recursive, only the given node is fixed. It is O(1).
	 * </p>
	 * 
	 * <p>
	 * If the current node is null, no action is taken.
	 * </p>
	 * 
	 * @param current
	 *            a node reference, or null
	 * @return true iff the height of the node changed
	 */
	private boolean fixHeight(Node<E> current) {
		if (current == null)
			return false;

		int oldHeight = current.height;
		current.height = 0;

		if (current.left != null)
			current.height = current.left.height + 1;
		if (current.right != null)
			current.height = Math.max(current.height, current.right.height + 1);

		return current.height != oldHeight;
	}

	/**
	 * <p>
	 * Removes the first (in in-order traversal order) data entry object that is
	 * the target object (using ==). A reference to the removed data entry is
	 * returned. The target must not be null.
	 * </p>
	 * 
	 * <p>
	 * This method returns in O(lg(size)) time.
	 * </p>
	 * 
	 * @param targetReference
	 *            any non-null data object reference
	 * @return the removed data entry, or null if none
	 * @throws NullPointerException
	 *             if the tree is nonempty and the target is null
	 */
	public E removeReference(E targetReference) 
	{
		if (root != null && targetReference == null) 
		{
			throw new NullPointerException();
		}
		E temp = removeReference(root, targetReference);

		if (temp == null) 
		{
			return null;
		}
		return temp;
	}

	/**
	 * recursive private helper method to remove the wanted reference in a avl
	 * tree
	 * 
	 * @param targetNode
	 *            -- root node of the search tree object
	 * @param targetReference
	 *            -- the reference that need to be remove in avl tree
	 * @return E -- the removed reference
	 */

	private E removeReference(Node<E> targetNode, E targetReference) 
	{

		// Simply return null if we pass in a null reference
		if (targetNode == null) 
		{
			return null;
		}
		// Extract a temporary node
		Node<E> currentNode = targetNode;

		int compareValue = comparator.compare(currentNode.data, targetReference);

		// If value of the targetReference is located in the left subtree, recurse left
		if (compareValue > 0) 
		{
			if (currentNode.left != null) 
			{
				return removeReference(currentNode.left, targetReference);
			}
		}

		// If value of the targetReference is located in the right subtree, recurse right
		if (compareValue < 0) 
		{
			if (currentNode.right != null) 
			{
				return removeReference(currentNode.right, targetReference);
			}
		}

		// If the currentNode data matches the value of the target reference, recurse in-order
		if (compareValue == 0) 
		{
			// Ensure that we remove the references with an in-order traversal
			E temp = null;

			// Try to remove the left subtree
			temp = removeReference(currentNode.left, targetReference);

			if (temp == targetReference) 
			{
				return temp;
			}

			// Try to remove the middle node
			if (currentNode.data == targetReference) 
			{
				return remove(currentNode);
			}

			// Try to remove the right subtree
			temp = removeReference(currentNode.right, targetReference);

			if (temp == targetReference) 
			{
				return temp;
			}
		}

		// If the reference is not found in the tree
		return null;
	}

	/**
	 * <p>
	 * Corrects the height of this node n, then guarantees the AVL condition for
	 * this node. If the height changes, the balance of the parent is also
	 * adjusted. (This may propagate balancing actions all the way to the root
	 * of the tree.)
	 * </p>
	 * 
	 * <p>
	 * This is a private helper method, and students will complete this (or some
	 * similar) method.
	 * </p>
	 * 
	 * @param currentNode
	 *            a node that may need to be balanced
	 */
	private void updateBalances(Node<E> currentNode) {

		// Ensure currentNode is not null before updating balances
		if (currentNode != null) {

			// Keep track of the parent's reference of this currentNode
			Node<E> tempParent = currentNode.parent;

			// fixHeight of currentNode
			fixHeight(currentNode);

			// If left subtree is heavy
			if (heightDiff(currentNode) < -1) 
			{
				// If the current left child height is 1, we know that the left's right child is heavy so we do a left-right rotate
				if (heightDiff(currentNode.left) == 1) 
				{
					leftRotate(currentNode.left);
					rightRotate(currentNode);
				} 
				else 
				{
					// Just do a single right rotation
					rightRotate(currentNode);
				}
			} 
			else if (heightDiff(currentNode) > 1) 
			{
				// If the current right child height is 1, we know that the right's left child is heavy so we do a right-left rotate
				if (heightDiff(currentNode.right) == -1) 
				{
					rightRotate(currentNode.right);

					leftRotate(currentNode);
				} 
				else 
				{
					// Just do a single left rotate
					leftRotate(currentNode);
				}
			}

			// Only recurse updateBalances when the currentNode's parent is not null
			if (tempParent != null) 
			{
				updateBalances(tempParent);
			}
		}
	}

	/**
	 * <p>
	 * Returns (height of the right subtree - height of the left subtree), or 0
	 * if n is null.
	 * </p>
	 * 
	 * @param n
	 *            a Node, or null
	 *            
	 * @return int
	 * 			  the difference between the right subtree and left subtree
	 */
	public int heightDiff(Node<E> n) 
	{

		int leftHeight;
		int rightHeight;

		if (n == null) 
		{
			return 0;
		}

		// By default, the height of a null child is -1
		if (n.left == null) 
		{
			leftHeight = -1;
		} 
		else 
		{
			leftHeight = n.left.height;
		}

		if (n.right == null) 
		{
			rightHeight = -1;
		} 
		else 
		{
			rightHeight = n.right.height;
		}

		return rightHeight - leftHeight;
	}

	/**
	 * <p>
	 * Given a node 'n' with a non-null right child, this method performs a left
	 * rotation around 'n'. The non-null right child will become the root of
	 * this subtree. After rotation, the root of this subtree is returned.
	 * </p>
	 * 
	 * <p>
	 * During the rotation, nodes are detached and reattached and subtree
	 * heights are updated where needed. Upon completion, the parent of 'n' (or
	 * root) will be updated to refer to the root of the left-rotated subtree
	 * (instead of 'n').
	 * </p>
	 * 
	 * <p>
	 * Note that while the references in the original parent of n will be
	 * correct, the height of the parent may become invalid as a result of
	 * rotation. The heights within the rotated subtree will be adjusted to be
	 * correct.
	 * </p>
	 * 
	 * <p>
	 * If the right child of 'n' is null, a null-pointer exception is thrown.
	 * </p>
	 * 
	 * @param n
	 *            the root of a subtree to rotate
	 */

	private void leftRotate(Node<E> n) 
	{
		Node<E> parentNode = n;
		Node<E> newRoot = n.right;
		Node<E> leftChildNewRoot = newRoot.left;

		// If the leftChild is not null then have the parent pointer point to the parent node
		if (leftChildNewRoot != null) 
		{
			leftChildNewRoot.parent = parentNode;
		}

		// The newRoot parent must point to the parentNode parent
		newRoot.parent = parentNode.parent;

		// If root is the same as parent node then set the root to the newRoot
		if (this.root == parentNode) 
		{
			this.root = newRoot;
		} 
		else 
		{
			// Check if the parentNode is a left child of a subtree and deals with left - right rotation
			if (isLeftChild(parentNode)) 
			{
				parentNode.parent.left = newRoot;
			} 
			else 
			{
				parentNode.parent.right = newRoot;
			}
		}

		// The newRoot left pointer should point down to the parentNode
		newRoot.left = parentNode;

		// The parentNode should have its parent pointer to the newRoot
		parentNode.parent = newRoot;

		// The parentNode will adopt the leftChildNewRoot as its new right child
		parentNode.right = leftChildNewRoot;

		// Fix all the heights of every node that have been moved
		fixHeight(parentNode);
		fixHeight(newRoot);
		fixHeight(leftChildNewRoot);
	}

	/**
	 * <p>
	 * Given a node 'n' with a non-null left child, this method performs a right
	 * rotation around 'n'. The non-null left child will become the root of this
	 * subtree. After rotation, the root of this subtree is returned.
	 * </p>
	 * 
	 * <p>
	 * During the rotation, nodes are detached and reattached and subtree
	 * heights are updated where needed. Upon completion, the parent of 'n' (or
	 * root) will be updated to refer to the root of the right-rotated subtree
	 * (instead of 'n').
	 * </p>
	 * 
	 * <p>
	 * If the left child of 'n' is null, a null-pointer exception is thrown.
	 * </p>
	 * 
	 * @param n
	 *            the root of a subtree to rotate
	 */

	private void rightRotate(Node<E> n) 
	{
		Node<E> parentNode = n;
		Node<E> newRoot = n.left;
		Node<E> rightChildNewRoot = newRoot.right;

		// If the rightChild is not null then have the parent pointer point to the parent node
		if (rightChildNewRoot != null) 
		{
			rightChildNewRoot.parent = parentNode;
		}

		// The newRoot parent must point to the parentNode parent
		newRoot.parent = parentNode.parent;

		// If root is the same as parent node then set the root to the newRoot
		if (root == parentNode) 
		{
			root = newRoot;
		} 
		else 
		{
			// Check if the parentNode is a right child of a subtree and deals with right - left rotation
			if (isRightChild(parentNode)) 
			{
				parentNode.parent.right = newRoot;
			} 
			else 
			{
				parentNode.parent.left = newRoot;
			}
		}

		// The newRoot right pointer should point down to the parentNode
		newRoot.right = parentNode;

		// The parentNode should have its parent pointer to the newRoot
		parentNode.parent = newRoot;

		// The parentNode will adopt the rightChildNewRoot as its new right child
		parentNode.left = rightChildNewRoot;

		// Fix all the heights of every node that have been moved
		fixHeight(parentNode);
		fixHeight(newRoot);
		fixHeight(rightChildNewRoot);
	}

	/**
	 * private boolean helper if parentNode is a left child of a sub-root
	 * 
	 * @param parentNode
	 *            - determine if the parent node is a left child of a sub tree
	 * @return true if is left child, false otherwise
	 */
	private boolean isLeftChild(Node<E> parentNode) 
	{
		if (parentNode.parent == null) 
		{
			return false;
		}
		return parentNode.parent.left == parentNode;
	}

	/**
	 * private boolean helper if parentNode is a right child of a sub-root
	 * 
	 * @param parentNode
	 *            - determine if the parent node is a left child of a sub tree
	 * @return true if is right child, false otherwise
	 */

	private boolean isRightChild(Node<E> parentNode) 
	{
		if (parentNode.parent == null) 
		{
			return false;
		}
		return parentNode.parent.right == parentNode;
	}

	/**
	 * <p>
	 * Checks the tree to make sure it has valid structure. (Parent / child
	 * pointers match, heights are correct, etc.) (For debugging only.)
	 * </p>
	 * 
	 * @exception RuntimeException
	 *                if the structure is not valid
	 */
	private void checkStructure() {
		final int[] c = new int[1]; // Not ideal, but works.

		visitInOrder(n -> {
			comparator.toString();
			// Anonymous local inner class specified in a lambda - ouch!
			// Still, it has cool advantages and we'll discuss them later.
			c[0]++;
			int correctHeight = 0;
			if (n.left != null)
				correctHeight = 1 + n.left.height;
			if (n.right != null)
				correctHeight = Math.max(correctHeight, 1 + n.right.height);
			if (n.height != correctHeight)
				throw new RuntimeException("Height invariant not kept");
			if (n.parent == null && root != n)
				throw new RuntimeException("Root not set properly");
			if (n.parent != null && n.parent.left != n && n.parent.right != n)
				throw new RuntimeException("Node does not point to its parent");
			if (n.left != null && n.left == n.right)
				throw new RuntimeException("Left/right children are not distinct");

			// Important: Don't count these comparisons. They're for testing
			// only.

			if (n.left != null && comparator.compare(n.left.data, n.data) > 0)
				throw new RuntimeException("Left child greater than parent");
			if (n.right != null && comparator.compare(n.right.data, n.data) < 0)
				throw new RuntimeException("Right child less than parent");
		});
		if (c[0] != size)
			throw new RuntimeException("Node count " + c[0] + " does not match size " + size);
	}
}
