import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LList<E>
{
	private static class Node<E>
	{
		E data;
		Node<E> next;
		Node<E> previous;
		
		private Node(E item)
		{
			data=item;
			next=null;
			previous=null;
		}
		
		private Node(E item, Node<E> nextItem)
		{
			data=item;
			next=nextItem;
			previous=null;
			nextItem.previous=this;
		}
		
		private Node(E item, Node<E> nextItem, Node<E> pastItem)
		{
			data=item;
			next=nextItem;
			previous=pastItem;
			nextItem.previous=this;
			pastItem.next=this;
		}
	}
	
	private Node<E> head;
	private Node<E> tail;
	private int size=0;
	
	public LList()
	{
		head=null;
		tail=null;
	}
	
	public LList(E item)
	{
		Node<E> newNode=new Node(item);
		head=newNode;
		tail=newNode;
		size++;
	}
	
	public Object clone()
	{
		LList<E> temp=new LList<E>();
		for(int i=0; i<size; ++i)
			temp.addLast(get(i));
		
		return temp;
	}
	
	public int size() {return size;}
	
	public E getFirst() {return head.data;}
	
	public E getLast() {return tail.data;}
	
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
		ListIter iter=iterator();
		
		while(iter.hasNext())
			if(iter.nextItem.data.equals(obj))
			{
				iter.remove();
				break;
			}
		
		return true;
	}
	public ListIter iterator() {return new ListIter();}
	
	public ListIter iterator(int i) 
	{
		ListIter iter=null;
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
	
	private class ListIter implements ListIterator<E>
	{
		private Node<E> nextItem;
		private Node<E> lastReturned;
		private int index;
		
		public ListIter()
		{
			nextItem=head;
			lastReturned=null;
			index=0;
		}
		
		public ListIter(int indexNo)throws InvalidIndex
		{
			if(indexNo<0 || indexNo>size())
				throw new InvalidIndex("There is no elements with this index no.");
			
			index=indexNo;
			lastReturned=null;
			
			if(index==size())
				nextItem=null;
			else
			{
				nextItem=head;
				for(int i=0; i<index; ++i)
					nextItem=nextItem.next;
			}
		}
		
		public void add(E obj)
		{
			Node<E> newNode=new Node<E>(obj);
			
			if(head==null)
			{
				head=newNode;
				tail=newNode;
			}
			else if(index==0)
			{
				head.previous=newNode;
				head=newNode;
			}
			else if(index==size())
			{
				tail.next=newNode;
				newNode.previous=tail;
				tail=newNode;
			}
			else
			{
				nextItem.previous.next=newNode;
				newNode.previous=nextItem.previous;
				nextItem.previous=newNode;
			}
			
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
		
		public boolean hasPrevious()
		{
			if(nextItem.previous!=null)
				return true;
			
			return false;
		}
		
		public E next()
		{
			if(hasNext()==false)
				throw new NoSuchElementException();
			
			lastReturned=nextItem;
			nextItem=nextItem.next;
			index++;
			
			return lastReturned.data;
		}
		
		public int nextIndex() {return index;}
		
		public E previous()
		{
			if(hasPrevious()==false)
				throw new NoSuchElementException();
			
			if(nextItem==null)
				lastReturned=tail;
			else
				lastReturned=nextItem.previous;
			nextItem=nextItem.previous;
			index--;
			
			return lastReturned.data;
		}
		
		public int previousIndex() {return index-1;}
		
		public void remove()
		{
			if(index==size())
				throw new NoSuchElementException();
			else if(index==0)
			{
				head=head.next;
				head.previous=null;
			}
			else if(index==size()-1)
			{
				tail=tail.previous;
				tail.next=null;
			}
			else
			{
				nextItem.previous.next=nextItem.next;
				nextItem.next.previous=nextItem.previous;
			}
			
			nextItem=nextItem.next;
			size--;
		}
		
		public void set(E obj) {nextItem.data=obj;}
	}
}
