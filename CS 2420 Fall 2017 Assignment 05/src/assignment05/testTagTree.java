package assignment05;

import java.util.Scanner;

public class testTagTree {

	public static void main(String[] args) {

		Scanner a = new Scanner("<a 1> </a>");
		Scanner b = new Scanner("<a color> <b green> </b> <c blue> </c> <d yellow> </d> </a>");
		Scanner c = new Scanner("<a color> <b green> </b> <c blue> </c> </a>");
		Scanner d = new Scanner("<a color> <b green> </b> <c blue> </c> <d yellow> <e 1> </e> <f 2> </f> <g 3> </g> <e 4> </e> </d> </a>");
		Scanner e = new Scanner("<a A> <b B> <c D> </c> <d E> <e H> </e> <f I> </b> </f> </d> <g C> <h F> </h> <i G> </i> </g> </a>");
		Scanner f = new Scanner("<1 E> <2 N> <4 A> <7 L> <12 K> </12> </7> </4> <5 J> <8 H> </8> <9 I> </9> </5> </2> <3 F> <6 G> <10 0> <13 B> </13> </10> <11 C> <14 D> </14> <15 M> </15> </11> </6> </3> </1>");
		Scanner g = new Scanner("<a color> <b green> <g 3> </g> <e 4> </e> </b> <c blue> <e 1> </e> <f 2> </f> </c> </a>");

		TagTree tags = new TagTree(e);
		System.out.println("Height: " + tags.getHeight());
		System.out.println("Maximum Degree: " + tags.getMaxDegree());
		//System.out.println("Number of children: " + tags.getChildCount());
		System.out.println("Pre-order: " + tags.outputPrefix());
		System.out.println("Post-order: " + tags.outputPostfix());
		System.out.println("Two-Tree? " + tags.isTwoTree());
		System.out.println("Binary-Tree? " + tags.isBinaryTree());
		System.out.println("Full Binary-Tree? " + tags.isFullBinaryTree());
		System.out.println("Depth: " + tags.findDepth("E"));
	}

}
