import java.util.Iterator;

public class SLList<E> 
{
	private static class Node<E>
	{
		private E data;
		private Node<E> next;
		
		private Node(E item)
		{
			data=item;
			next=null;
		}
		
		private Node(E item, Node<E> nextItem)
		{
			data=item;
			next=nextItem;
		}
	}
	
	private Node<E> head;
	private int size=0;
	
	public SLList()
	{
		head=null;
	}
	
	public SLList(Node<E> item)
	{
		head=item;
		size++;
	}
	
	public Object clone()
	{
		SLList<E> temp=new SLList();
		for(int i=0; i<size; ++i)
			temp.addLast(get(i));
		
		return temp;
	}
	
	public int size() {return size;}
	
	public E getFirst() {return head.data;}
	
	public E getLast() {return get(size()-1);}
	
	public E get(int index)
	{
		return iterator(index).nextItem.data;	
	}
	
	public void add(int index, E obj)
	{
		iterator(index).add(obj);
	}
	
	public void addFirst(E obj)
	{
		iterator().add(obj);
	}
	
	public void addLast(E obj)
	{
		iterator(size()).add(obj);
	}
	
	public boolean remove(E obj)
	{
		try
		{
		ListIter<E> iter=iterator();
		while(iter.hasNext())
			if(iter.nextItem.data.equals(obj))
			{
				iter.remove();
				break;
			}
		}
		catch(NoElement exc)
		{
			System.err.print(exc);
			System.exit(-1);
		}
		
		return true;
	}
	public ListIter<E> iterator() {return new ListIter<E>();}
	
	public ListIter<E> iterator(int i) 
	{
		ListIter<E> iter=null;
		try
		{
			iter=new ListIter(i);
		}
		catch(InvalidIndex exc)
		{
			System.err.print(exc);
			System.exit(-1);
		}
		return iter;
	}
	
	private class ListIter<E>
	{
		private Node<E> nextItem;
		private Node<E> lastReturned;
		private int index;
		
		public ListIter()
		{
			index=0;
			lastReturned=null;
			nextItem=(SLList.Node<E>) head;
		}
		
		public ListIter(int indexNo) throws InvalidIndex
		{
			if(indexNo<0 || indexNo>size)
				throw new InvalidIndex("There is no elements in this index.");
			
			index=indexNo;
			nextItem=(SLList.Node<E>) head;
			for(int i=0; i<indexNo; ++i)
			{
				lastReturned=nextItem;
				nextItem=nextItem.next;
			}
		}
		
		public void add(E obj)
		{
			Node<E> newNode=new Node<E>(obj);

			if(index==0)
				head=(SLList.Node<E>)newNode;
			else
				lastReturned=newNode;
			
			newNode.next=nextItem;
			index++;
			size++;
		}
		
		public boolean hasNext()
		{
			if(nextItem!=null)
				return true;
			
			return false;
		}
		
		public E next() throws NoElement
		{
			if(hasNext()==true)
				throw new NoElement("There is no elements to return.");
			
			lastReturned=nextItem;
			nextItem=nextItem.next;
			index++;
			
			return lastReturned.data;
		}
		
		public int nextIndex() {return index;}
		
		public int previousIndex() {return index-1;}
		
		public void remove() throws NoElement
		{
			if(index==size())
				throw new NoElement("There is no elements to remove.");
			else if(index==0)
			{
				head=head.next;
				nextItem=(SLList.Node<E>) head;
			}
			else
			{
				lastReturned.next=nextItem.next;
				nextItem=lastReturned.next;
			}
			
			size--;	
		}
		
		public void set(E obj) {nextItem.data=obj;}
	}

}
