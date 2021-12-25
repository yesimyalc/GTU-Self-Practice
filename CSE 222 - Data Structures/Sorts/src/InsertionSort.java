
public class InsertionSort<T extends Comparable<T>>
{
	public void sort(T[] array)
	{
		if(array.length<2)
			return;
		
		for(int nextPos=1; nextPos<array.length; ++nextPos)
		{
			T temp=array[nextPos];
			while(nextPos>0 && array[nextPos-1].compareTo(temp)>0)
			{
				array[nextPos]=array[nextPos-1];	
				nextPos--;
			}
			array[nextPos]=temp;
		}
	}
}
