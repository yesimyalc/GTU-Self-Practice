
public class MergeSort<T extends Comparable<T>>
{
	public void sort(T[] array)
	{		
		if(array.length>1)
		{
			int halfSize=array.length/2;
			T[] left=(T[])new Comparable[halfSize];
			T[] right=(T[])new Comparable[array.length-halfSize];
			System.arraycopy(array, 0, left, 0, halfSize);
			System.arraycopy(array, halfSize, right, 0, array.length-halfSize);
			
			sort(left);
			sort(right);
			
			merge(right, left, array);
		}
	}
	
	private void merge(T[] rightSub, T[] leftSub, T[] output)
	{
		int ri=0;
		int li=0;
		int oi=0;
		
		while(li<leftSub.length && ri<rightSub.length)
		{
			if(leftSub[li].compareTo(rightSub[ri])<0)
			{
				output[oi]=leftSub[li];
				li++;
			}
			else
			{
				output[oi]=rightSub[ri];
				ri++;
			}
			oi++;
		}
		
		while(ri<rightSub.length)
			output[oi++]=rightSub[ri++];
		while(li<leftSub.length)
			output[oi++]=leftSub[li++];
	}
}
