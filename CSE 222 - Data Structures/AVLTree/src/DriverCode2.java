import java.util.Random;

public class DriverCode2 {

	public static void main(String[] args) 
	{
		BinarySearchTree<Integer> avl1=new AVLTree<Integer>();
		int i=0;
		Random rand=new Random();
		int upperbound=500001;
		System.out.println("Generating 10000 numbers and inserting them into both the array and the map.");
		for(int j=0; j<100000; ++j)
		{
			Integer number=rand.nextInt(upperbound);
			boolean check=((AVLTree<Integer>)avl1).add(number);
			if(check==false)
				j--;
		}
		for(int j=0; j<5000; ++j)
		{
			Integer number=rand.nextInt(upperbound);
			((AVLTree<Integer>)avl1).delete(number);
		}
		

	}

}
