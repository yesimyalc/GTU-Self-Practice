
public class SLList<E>
{
	private static class Node<E>
	{
		E data;
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
		SLList temp=new SLList();
		for(int i=0; i<size; ++i)
			temp.add(get(i));
		
		return temp;
	}
	
	private void addFirst(E element)
	{
		Node<E> newNode=new Node<E>(element);
		newNode.next=head;
		head=newNode;
		size++;
	}
	
	private void addAfter(Node<E> node, E item)
	{
		node.next=new Node<E>(item, node.next);
		size++;
	}
	
	private E removeAfter(Node<E> node)throws NoElement
	{
		if(node.next==null)
			throw new NoElement("There is no element to remove.");
		
		Node<E> temp=node.next;
		
		node.next=temp.next;
		size--;
		
		return temp.data;	
	}
	
	private E removeFirst() throws NoElement
	{
		if(head==null)
			throw new NoElement("There is no element to remove.");
		
		E temp=head.data;
		
		head=head.next;
		size--;
		
		return temp;
	}
	
	private Node<E> getNode(int index)throws InvalidIndex
	{
		if(index<0 || index>=size)
			throw new InvalidIndex("There is no element in this index.");
		
		Node<E> temp=head;
		for(int i=0; i<index; ++i)
			temp=temp.next;
		
		return temp;
	}
	
	public E get(int index)
	{
		Node<E> temp=null;
		try
		{
			temp=getNode(index);
		}
		catch(InvalidIndex exc)
		{
			System.err.print(exc);
			System.exit(-1);
		}
		return temp.data;
	}
	
	public E set(int index, E element)
	{
		E temp=null;
		try
		{
			temp=getNode(index).data;
			getNode(index).data=element;	
		}
		catch(InvalidIndex exc)
		{
			System.err.print(exc);
			System.exit(-1);
		}
		return temp;
	}
	
	public int size() {return size;}
	
	public boolean add(E element)
	{
		try
		{
			if(head==null)
				addFirst(element);
			else
				addAfter(getNode(size()-1), element);
		}
		catch(InvalidIndex exc)
		{
			System.err.print(exc);
			System.exit(-1);
		}
		
		return true;
	}
	
	public void add(int index, E element)throws InvalidIndex
	{
		if(index<0 || index>size)
			throw new InvalidIndex("There is no element in this index.");
		
		try
		{
			if(index==0)
				addFirst(element);
			else if(index==size())
				add(element);
			else
				addAfter(getNode(index-1), element);
		}
		catch(InvalidIndex exc)
		{
			System.err.print(exc);
			System.exit(-1);
		}
	}
	
	public int indexOf(E target)
	{
		Node<E> temp=head;
		for(int i=0; i<size(); ++i)
		{
			if(temp.data.equals(target))
				return i;
			
			temp=temp.next;
		}
		return -1;
	}
	
	public boolean remove(int index)throws InvalidIndex
	{
		try
		{
			if(index<0 || index>=size)
				throw new InvalidIndex("There is no element in this index.");
			
			if(index==0)
				removeFirst();
			else 
				removeAfter(getNode(index-1));
		}
		catch(InvalidIndex exc)
		{
			System.err.print(exc);
			System.exit(-1);
		}
		catch(NoElement exc)
		{
			System.err.print(exc);
			System.exit(-1);
		}
		
		return true;
	}
	
	public boolean remove(E element) 
	{
		try
		{
			int i=indexOf(element);
			if(i!=-1)
				remove(i);
		}
		catch(InvalidIndex exc)
		{
			System.err.print(exc);
			System.exit(-1);
		}
		return true;
	}
}
