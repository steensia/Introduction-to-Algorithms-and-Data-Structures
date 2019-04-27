package assignment07;

public class SearchTreeTest2 {

	public static void main(String[] args) 
	{
		SearchTree<String> tree = new SearchTree(String.CASE_INSENSITIVE_ORDER, true);

		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		String e = "E";
		String k = "K";
		String z = "Z";

		//LL Case - just right rotate
		//		tree.add(c);
		//		tree.add(b);
		//		tree.add(a);

		//RR Case - just left rotate
		//		tree.add(a);
		//		tree.add(b);
		//		tree.add(c);

		//LR Case - rotate left then right
		//		tree.add(c);
		//		tree.add(a);
		//		tree.add(b);

		//RL Case - rotate right then left
		//		tree.add(c);
		//		tree.add(e);
		//		tree.add(d);

		//Special Case - rotate left then right
		//		tree.add(k);
		//		tree.add(b);
		//		tree.add(z);
		//		tree.add(a);
		//		tree.add(d);
		//		tree.add(c);

		DotFileGenerator.writeDot("tree.dot", tree);
	}
}
