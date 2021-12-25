import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class DriverCode {

	public static void main(String[] args) 
	{
		BinaryTree<Integer> avl1=new AVLTree<Integer>();
		BinaryTree<Integer> tree=new BinarySearchTree<Integer>();
		int i=0;

		Integer array[]={15,23,78,43,67,21,46,79,90,34,25,65,33,22,42,87,56,678,421,122,132,156
				,234,345,245,213,27,28,96,93,95,145,6,24,152,178,71
				,321,456,345,333,313,365,378,389,421,411,413,521,115,118,789,743,921,563,559,116,955,210,335,440,755
				,15, 234, 78, 15, 15, 90, 67, 23, 23, 234, 43, 78};
		System.out.println("Generating 37 numbers and inserting them into both the array and the map.");
		for(int j=0; j<array.length; ++j)
		{
			((AVLTree)avl1).add(array[j]);
			((BinarySearchTree)tree).add(array[j]);
		}
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		System.out.println("\n15: "+((AVLTree)avl1).getAmount(15));
		System.out.println("23: "+((AVLTree)avl1).getAmount(23));
		System.out.println("78: "+((AVLTree)avl1).getAmount(78));
		System.out.println("234: "+((AVLTree)avl1).getAmount(234));
		System.out.println("90: "+((AVLTree)avl1).getAmount(90));
		System.out.println("67: "+((AVLTree)avl1).getAmount(67));
		System.out.println("43: "+((AVLTree)avl1).getAmount(43));
		System.out.println("345: "+((AVLTree)avl1).getAmount(345));
		Integer array2[]= {43,67,21,46,79,145,6,24,152,178,22,42,78,87,56,678,421,234,345
				,333 ,789, 365, 345, 743, 921, 559, 115, 90, 313};
		//43,67,21,46, 6, 24
		//43,67,21,46,79,145,6,24,152,178,22,42,78,87,56,678,421,234,345
		for(int j=0; j<array2.length; ++j)
		{
			((AVLTree)avl1).delete(array2[j]);
			((BinarySearchTree)tree).delete(array2[j]);
		}
		/*System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());*/
		System.out.println("\n15: "+((AVLTree)avl1).getAmount(15));
		System.out.println("23: "+((AVLTree)avl1).getAmount(23));
		System.out.println("78: "+((AVLTree)avl1).getAmount(78));
		System.out.println("234: "+((AVLTree)avl1).getAmount(234));
		System.out.println("90: "+((AVLTree)avl1).getAmount(90));
		System.out.println("67: "+((AVLTree)avl1).getAmount(67));
		System.out.println("43: "+((AVLTree)avl1).getAmount(43));
		System.out.println("345: "+((AVLTree)avl1).getAmount(345));
		
		/*Random rand=new Random();
		int upperbound=5001;
		System.out.println("Generating 10000 numbers and inserting them into both the array and the map.");
		BinaryTree<Integer> avl2=new AVLTree<Integer>();
		for(int j=0; j<10000; ++j)
		{
			Integer number=rand.nextInt(upperbound);
			int old=((AVLTree)avl2).getAmount(number);
			boolean check=((AVLTree<Integer>)avl2).add(number);
			int newc=((AVLTree)avl2).getAmount(number);
		}
		int count=0;
		System.out.println("Current size: "+avl2.size());
		for(int j=0; j<500; ++j)
		{
			Integer number=rand.nextInt(upperbound);
			int old=((AVLTree)avl2).getAmount(number);
			((AVLTree<Integer>)avl2).delete(number);
			int newc=((AVLTree)avl2).getAmount(number);
			//System.out.println("Old: "+old+" New: "+newc);
			if(old!=newc)
				count++;
		}
		System.out.println("Deleted: "+count+" New size: "+avl2.size()+" Old size: "+(count+avl2.size()));
		Iterator<Integer> iter=((AVLTree)avl2).preOrderIterator();
		int count2=0;
		while(iter.hasNext())
		{
			Integer temp=iter.next();
			count2+=((AVLTree)avl2).getAmount(temp);
		}
		System.out.println(count2);*/
		
		/*((AVLTree)avl1).add(40);
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).add(25);
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).add(50);
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).add(30);
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).add(20);
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).add(15);
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).add(60);
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).add(45);
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).add(72);
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		
		((AVLTree)avl1).delete(50);
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).delete(20);
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).delete(25);
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());*/
		
		
		
		
		
		/*((AVLTree)avl1).add("The");
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).add("quick");
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).add("brown");
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).add("fox");
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).add("jumps");
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).add("over");
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).add("the");
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).add("lazy");
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).add("dog");
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		
		
		((AVLTree)avl1).delete("fox");
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).delete("quick");
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).delete("jumps");
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).delete("brown");
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).delete("The");
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());
		((AVLTree)avl1).delete("the");
		System.out.println("\n->"+ ++i);
		System.out.println(avl1.toString());*/
		
		

	}

}
