package Lab3;
/*
Lab #3: Create a driver program to test your code. In your driver:
1) Add 10 integers
2) Print the list of unsorted integers
3) Sort the list
4) Print the sorted list 
*/

//test some Node capabilities
public class NodeDriver
{
	public static void main(String[] args)
	{
		IntNode head = new IntNode(10, null);

		head.addNodeAfter(4);
		head.addNodeAfter(3);
		head.addNodeAfter(27);
		head.addNodeAfter(1);
		head.addNodeAfter(8);
		head.addNodeAfter(4);
		head.addNodeAfter(11);
		head.addNodeAfter(9);
		head.addNodeAfter(4);
		head.addNodeAfter(88);
		head.addNodeAfter(0);
		head.addNodeAfter(-1);
		head.addNodeAfter(2);
		
		System.out.println("Unsorted List:");
		for(IntNode current = head; current != null; current = current.getLink())
		{
			System.out.println(current.getData());
		}
		
		System.out.println("\nSorted List:");
		head = head.listSort(head);
		int i = 0;
		for(IntNode current = head; current != null; current = current.getLink())
		{
			System.out.println(current.getData());
			i++;
			if(i == 20) break;
		}

	}
}