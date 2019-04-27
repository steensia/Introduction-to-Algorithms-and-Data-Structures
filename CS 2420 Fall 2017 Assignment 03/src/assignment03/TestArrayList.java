package assignment03;

public class TestArrayList {

	public static void main(String[] args) 
	{
		MyArrayList<Integer> list = new MyArrayList<Integer>();
//		list.insert(0, 1);
//		list.insert(1, 2);
//		list.insert(0, 3);
//		list.insert(2, 5);
	//	list.insert(1, 7);
		list.insert(0, 1000);
		list.insert(0, 2000);
		list.insert(0, 3000);
		list.insert(2, 4000);
		
		System.out.println(list.getElement(0));
		System.out.println(list.getElement(1));
		System.out.println(list.getElement(2));
		System.out.println(list.getElement(3));
		System.out.println(list.remove(2));
//		System.out.println(list.getElement(4));
		System.out.println(list.getModificationCount());
		System.out.println(list.size());
	}
}
