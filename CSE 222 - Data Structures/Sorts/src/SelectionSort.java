
public class SelectionSort<T extends Comparable<T>>
{
	public void sort(T[] array)
	{
		if(array.length<2)
			return;
		
		for(int i=0; i<array.length-1; i++)
		{
			int tempMin=i;
			for(int j=i+1; j<array.length; j++)
				if(array[j].compareTo(array[tempMin])<0)
					tempMin=j;
			
			if(i!=tempMin)
			{
				T temp=array[i];
				array[i]=array[tempMin];
				array[tempMin]=temp;
			}	
		}
	}
}
