package assignment05;

import java.util.ArrayList;
import java.util.Scanner;

/**TagTree is a data structure that utilizes nodes and pairs it with
 * ArrayLists to store the objects.  A TagTree contains a root node
 * that can have children and expand further.  This class operates
 * by reading a file through a scanner and stores each string
 * into its appropriate ArrayList.
 * 
 * @authors Steen Sia, Tom Nguyen
 * @version 10/27/17
 *
 */
public class TagTree {

	private Node root;
	private static int childCount = 0;

	private static class Node
	{
		String data;
		ArrayList<Node> children = new ArrayList<Node>();

		public Node(String data, ArrayList<Node> children) {
			this.data = data;
			this.children = children;
		}
	}

	public TagTree(Scanner s)
	{
		s.next();
		root = parse(s);
	}	
	
	/**Private helper method that scans in data
	 * and distinguishes opening tags from closing
	 * tags.  
	 * 
	 * @param s - scanner object
	 * @return node 
	 */
	private static Node parse(Scanner s)
	{
		String file = s.next();

		Node temp = new Node(file.substring(0, file.length() - 1), new ArrayList<Node>());

		temp.data = file.substring(0, file.length() - 1);

		while(s.hasNext())
		{
			file = s.next();

			if(file.charAt(0) == '<' && file.charAt(1) == '/')
			{
				return temp;
			}
			temp.children.add(parse(s));
		}
		return temp;
	}
	
	/**The method returns the height of this tag tree.
	 * 
	 * @return int - height of tag tree
	 */
	public int getHeight()
	{
		return getHeight(root);
	}

	private static int getHeight(Node r)
	{
		if(r.children.size() == 0)
		{
			return 0;
		}

		int startHeight = 0;

		for(Node n: r.children)
		{
			startHeight = Math.max(startHeight, getHeight(n));
		}
		return startHeight+1;
	}

	/**The method returns the maximum degree (maximum child count) of any node in this tag tree.
	 * 
	 * @return int - maximum child count
	 */
	public int getMaxDegree()
	{
		return getMaxDegree(root);
	}

	private static int getMaxDegree(Node r)
	{
		if(r.children.size() == 0)
		{
			return 0;
		}

		int degreeCount = r.children.size();

		for(Node n: r.children)
		{
			degreeCount = Math.max(degreeCount, n.children.size());
			getMaxDegree(n);
		}
		return degreeCount;
	}

	/**The returned string should contain the node names in prefix order (pre-order traversal order), separated by whitespace.  
	 * For the tree above (with the root 'color'), the output would be "color green blue yellow".  
	 * 
	 * @return String - the nodes in pre-order traversal order
	 */
	public String outputPrefix()
	{
		return outputPrefix(root);
	}

	private static String outputPrefix(Node r)
	{
		String preOrder = r.data;
		if(r.children.size() == 0)
		{
			return r.data;
		}
		for(Node n: r.children)
		{
			preOrder += " " + outputPrefix(n);
		}
		return preOrder;
	}

	/**The returned string should contain the node names in postfix order (post-order traversal order), separated by whitespace.  
	 * For the tree above (with the root 'color'), the output would be "green blue yellow color".
	 * 
	 * @return String - the nodes in post-order traversal order
	 */
	public String outputPostfix()
	{
		return outputPostfix(root);
	}

	private static String outputPostfix(Node r)
	{
		String temp = r.data;
		String postOrder = "";

		if(r.children.size() == 0)
		{
			return r.data;
		}
		
		for(Node n: r.children)
		{
			postOrder += outputPostfix(n) + " "; 
		}
		return postOrder + temp;
	}

	/**The method returns true if this tag tree is a binary tree, and false otherwise.
	 * 
	 * @return boolean - true if binary tree
	 */
	public boolean isBinaryTree()
	{
		if(getMaxDegree() <= 2)
		{
			return true;	
		}
		return false;	
	}

	/**The method returns true if this tag tree is a two-tree, and false otherwise.
	 * 
	 * @return boolean - true if two-tree
	 */
	public boolean isTwoTree()
	{		
		return isTwoTree(root);
	}

	private static boolean isTwoTree(Node r)
	{
		if(r.children.size() == 0)
		{
			return true;
		}
		for(Node n: r.children)
		{	
			if(n.children.size() == 1 || n.children.size() > 2 || r.children.size() == 1 || r.children.size() > 2)
			{
				return false;
			}
			isTwoTree(n);
		}
		return true;
	}

	/**The method returns true if this tag tree is a full binary tree, and false otherwise.
	 * 
	 * @return boolean - true if full binary tree
	 */
	public boolean isFullBinaryTree()
	{	
		return isTwoTree();
	}
	
	/**This method searches for the node who's name equals the String and returns the depth of that node.  
	 * If the String is not the name of any node, -1 is returned.  
	 * If the String is the name of more than one node, the depth of the node that occurred first in the tag data is returned.
	 * 
	 * @param s - string to find
	 * @return int - the depth of the node that was searched
	 */
	public int findDepth(String s)
	{
		if(findDepth(root, s, 1) == 0)
		{
			return -1;
		}
		return findDepth(root, s, 0);
	}

	private static int findDepth(Node r, String s, int i)
	{
		if(r.data.equals(s))
		{
			return i;
		}
		
		int temp = 0;

		for(Node n : r.children)
		{
			temp += findDepth(n, s, i+1);
		}
		return temp;
	}
}
