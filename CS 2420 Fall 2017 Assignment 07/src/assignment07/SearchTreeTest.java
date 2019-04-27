package assignment07;

import java.util.Comparator;

public class SearchTreeTest {

	static Comparator<Integer> integerCompare = new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1.intValue() - o2.intValue();
		}
	};

	public static void main(String[] args) {

		// SearchTree tree = new SearchTree((Comparator)
		// String.CASE_INSENSITIVE_ORDER, true);
		SearchTree<Integer> tree = new SearchTree<Integer>((Comparator) integerCompare, true);
		// ... fill the tree up!

		Integer test = (int) Math.random() + 10 * 5;

		for (int i = 0; i < 100; i++)

			tree.add(test);

		DotFileGenerator.writeDot("tree.dot", tree);
	}
}
