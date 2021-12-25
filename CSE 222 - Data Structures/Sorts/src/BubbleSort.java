
public class BubbleSort<T extends Comparable<T>>
{
	public void sort(T[] array)
	{
		if(array.length<2)
			return;
		
		boolean exchanged=false;
		int pass=1;
		do
		{
			exchanged=false;
			for(int i=0; i<array.length-pass; i++)
			{
				if(array[i].compareTo(array[i+1])>0)
				{
					T temp=array[i];
					array[i]=array[i+1];
					array[i+1]=temp;
					exchanged=true;
				}
			}
			pass++;
		}
		while(exchanged);
	}
}
