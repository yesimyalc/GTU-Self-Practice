
public class HeapSort<T extends Comparable<T>>
{
	public void sort(T[] array)
	{
		if(array.length<2)
			return;
		
		buildHeap(array);
		shrinkHeap(array);
	}
	
	private void swap(T[] table, int pIndex, int cIndex)
	{
		T temp=table[pIndex];
		table[pIndex]=table[cIndex];
		table[cIndex]=temp;
	}
	
	private void buildHeap(T[] table)
	{
		int n=1;
		while(n<table.length)
		{
			n++;
			int child=n-1;
			int parent=(child-1)/2;
			while(parent>=0 && table[parent].compareTo(table[child])<0)
			{
				swap(table, parent, child);
				child=parent;
				parent=(child-1)/2;
			}
		}
	}
	
	private void shrinkHeap(T[] table)
	{
		int n=table.length;
		while(n>0)
		{
			n--;
			int parent=0;
			swap(table, 0, n);
			
			while(true)
			{
				int leftC=(parent*2)+1;
				int rightC=leftC+1;
				
				if(leftC>=n)
					break;
				int maxC=leftC;
				if(rightC<n && table[leftC].compareTo(table[rightC])<0)
					maxC=rightC;
				
				if(table[parent].compareTo(table[maxC])<0)
				{
					swap(table, parent, maxC);
					parent=maxC;
				}
				else
					break;
			}
		}
	}
}
