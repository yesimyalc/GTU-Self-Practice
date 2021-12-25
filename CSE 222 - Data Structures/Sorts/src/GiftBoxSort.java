
public class GiftBoxSort<T extends Comparable<T>> 
{
	public T[][] giftBoxSort(T[][] array, T[][] pivotArray, int count)
	{
		if(count==pivotArray.length)
			return array;
		else
		{
			array=(T[][])sort(array, pivotArray[count][1]);
			count++;
			return giftBoxSort(array, pivotArray, count);
		}
	}
	
	public T[][] sort(T[][] array, T pivot)
	{
		if(array.length<2)
			return array;
		
		partition(array, 0, array.length-1, pivot);
		return array;
	}
	
	private void swap(T[][] table, int pIndex, int cIndex)
	{
		T tempF=table[pIndex][0];
		T tempS=table[pIndex][1];
		table[pIndex][0]=table[cIndex][0];
		table[pIndex][1]=table[cIndex][1];
		table[cIndex][0]=tempF;
		table[cIndex][1]=tempS;
	}

	
	private void partition(T[][] table, int start, int end, T pivot)
	{
		int up=start;
		int down=end;
		
		do
		{
			while(up<down && table[up][1].compareTo(pivot)<0)
				up++;
			while(table[down][1].compareTo(pivot)>0)
				down--;
			
			if(up<down)
				swap(table, up, down);
		}
		while(up<down);
		
	}
}
