
public class QuickSort<T extends Comparable<T>> 
{
	public static int count=0;
	public T[] sort(T[] array)
	{
		if(array.length<2)
			return array;
		
		quickSort(array, 0, array.length-1);
		return array;
	}
	
	private void swap(T[] table, int pIndex, int cIndex)
	{
		System.out.println("\n->"+table[pIndex]+" ,"+table[cIndex]);
		T temp=table[pIndex];
		table[pIndex]=table[cIndex];
		table[cIndex]=temp;
		for(int i=0; i<table.length; ++i)
			System.out.print(table[i]+" ");
		System.out.println();
		count++;
	}
	
	private void quickSort(T[] table, int first, int last)
	{
		if(first<last)
		{
			int pivIndex=partition(table, first, last);
			quickSort(table, first, pivIndex-1);
			quickSort(table, pivIndex+1, last);
		}
	}
	
	private int partition(T[] table, int start, int end)
	{
		T pivot=table[start];
		int up=start;
		int down=end;
		
		/*do
		{
			while(up<down && table[up].compareTo(pivot)<=0)
				up++;
			while(table[down].compareTo(pivot)>0)
				down--;
			
			if(up<down)
				swap(table, up, down);
		}
		while(up<down);*/
		
		down++;
		while(up<down)
		{
			do {
				up++;
			}
			while(up<table.length && table[up].compareTo(pivot)<0);
			do {
				down--;
			}
			while(down>=-1 && table[down].compareTo(pivot)>0);
			
			if(up<down)
				swap(table, down, up);
		}
		
		//if(start!=down)
			swap(table, start, down);
		
		return down;
	}
}
