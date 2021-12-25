import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PriorityQueue<E>
{
	ArrayList<E> theData;
	
	public PriorityQueue()
	{
		theData=new ArrayList<E>();
	}
	
	public PriorityQueue(E item)
	{
		theData=new ArrayList<E>();
		theData.add(item);
	}
	
	private void swap(int i, int j)
	{
		E temp=theData.get(j);
		theData.set(j, theData.get(i));
		theData.set(i, temp);
	}
	
	private int compare(E left, E right)
	{
		return ((Comparable) left).compareTo(right);
	}
	
	public int size()
	{
		return theData.size();
	}
	
	public boolean isEmpty()
	{
		return theData.isEmpty();
	}
	
	public boolean contains(E target)
	{
		return theData.contains(target);
	}
	
	public E peek()
	{
		if(isEmpty())
			return null;
		else
			return theData.get(0);
	}
	
	public E element()
	{
		E temp=peek();
		if(temp==null)
			throw new NoSuchElementException();
		else
			return temp;
	}
	
	public E poll()
	{
		if(isEmpty())
			return null;
		else if(size()==1)
			return theData.remove(0);
		
		E removed=theData.get(0);
		theData.set(0, theData.get(size()-1));
		theData.remove(size()-1);
		
		int parent=0;
		
		while(true)
		{	
			int leftC=(parent*2)+1;
			int rightC=leftC+1;
			
			if(leftC>=size())
				break;
			int maxC=leftC;
			if(rightC<size() && compare(theData.get(rightC), theData.get(leftC))>0)
				maxC=rightC;
			
			if(compare(theData.get(parent), theData.get(maxC))>0)
			{
				swap(parent, maxC);
				parent=maxC;
			}
			else
				break;
		}
		
		return removed;
	}
	
	public E remove()
	{
		E temp=poll();
		if(temp==null)
			throw new NoSuchElementException();
		else
			return temp;
	}
	
	public boolean offer(E target)
	{
		boolean returnVal=false;
		theData.add(target);
		int child=size()-1;
		int parent=(child-1)/2;
		while(parent>=0 && compare(theData.get(parent), theData.get(child))>0)
		{
			swap(parent, child);
			child=parent;
			parent=(child-1)/2;
			returnVal=true;
		}
		
		return returnVal;
	}
	
}
