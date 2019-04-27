package assignment02;

public class TestRollingList1 {

	public static void main(String[] args)
	{
		RollingList Monkey = new RollingList(1.2f, 0);
//		for(int i = 1; i <= 10_000; i++)
//		{
//			Monkey.prepend(i);
//			if(i % 1000 == 0)
//				System.out.println(i + "\t" + Monkey.getArrayModificationCount() / (1.0 * i));
//		}
//		for(int i = Monkey.size(); i > 1; i--)
//		{
//			Monkey.removeFirst();
//		}
//		System.out.println(Monkey.getElement(0));;
		
		Monkey.append(1);
		Monkey.append(2);
//		Monkey.removeFirst();
//		Monkey.removeFirst();
		System.out.println(Monkey.size());
	}
}
