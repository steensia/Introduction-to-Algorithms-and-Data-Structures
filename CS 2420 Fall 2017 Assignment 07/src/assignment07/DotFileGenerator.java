package assignment07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * This "Visitor" class can be used to create a valid 
 * digraph GraphViz file, rendered with the Dotty / Dot program
 * from GraphViz. 
 * @author ryans
 *
 * @param <T> - Same type as Tree
 */
public class DotFileGenerator {
	/**
	 * Creates a valid digraph .dot file at specified location.
	 * 
	 * @param filename - path to saved file location.
	 * @param tree - Tree to build the file from.
	 */
	public static void writeDot(String filename, SearchTree<?> tree) {
		try(FileWriter fw = new FileWriter(new File(filename));
				PrintWriter output = new PrintWriter(fw)) {
			output.println("digraph BST {");
            output.println("node [shape=record]");
            // First print out all the nodes and label them.
            tree.visitPreOrder((node) -> output.println(getNodeId(node) + "[label=\"<L> |<D> " + node.data + "|<R> \"]"));
            
            //Then print out all the edges 
            tree.visitPostOrder(node -> {
            	if(node.left != null) {
            		output.println(getNodeId(node) + ":L -> " + getNodeId(node.left) + ":D");
            	}
            	if(node.right != null) {
            		output.println(getNodeId(node) + ":R -> " + getNodeId(node.right) + ":D");
            	}
            });
			output.println("}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getNodeId(Node<?> node) {
		return Integer.toString(node.hashCode());
	}
}
