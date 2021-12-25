
public class ShellSort<T extends Comparable<T>>
{
	public void sort(T[] array)
	{
		if(array.length<2)
			return;
		
		int gapValue=(int)(array.length/2.2);
		while(gapValue>0)
		{
			for(int nextPos=gapValue; nextPos<array.length; ++nextPos)
			{
				T temp=array[nextPos];
				while(nextPos>gapValue-1 && array[nextPos-gapValue].compareTo(temp)>0)
				{
					array[nextPos]=array[nextPos-gapValue];
					nextPos-=gapValue;
				}
				array[nextPos]=temp;
			}
			if(gapValue==2)
				gapValue=1;
			else
				gapValue=(int)(gapValue/2.2);
		}
	}
}
