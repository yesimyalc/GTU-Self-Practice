
public class CLList<E>
{
	private static class Node<E>
	{
		E data;
		Node<E> next;
		Node<E> previous;
		
		public Node(E item)
		{
			data=item;
			next=null;
			previous=null;
		}
		
		public Node(E item, Node<E> nextItem)
		{
			data=item;
			next=nextItem;
			previous=null;
			nextItem.previous=this;
		}
		
		public Node(E item, Node<E> nextItem, Node<E> pastItem)
		{
			data=item;
			next=nextItem;
			previous=pastItem;
			pastItem.next=this;
			nextItem.previous=this;
		}
	}
	
	public Node<E> head;
	public Node<E> tail;
	public int size=0;
	
	public CLList()
	{
		head=null;
		tail=null;
	}
	
	public CLList(E item)
	{
		addFirst(item);
	}
	
	public Object clone()
	{
		CLList<E> temp=new CLList<E>();
		for(int i=0; i<size; ++i)
			temp.add(get(i));
		
		return temp;
	}
	
	private void addFirst(E element)
	{
		Node<E> newNode=new Node<E>(element);
		if(head==null)
		{
			head=newNode;
			tail=newNode;
			newNode.next=newNode;
			newNode.previous=newNode;	
		}
		else
		{
			newNode.next=head;
			newNode.previous=tail;
			tail.next=newNode;
			head.previous=newNode;
			head=newNode;
		}
		size++;
	}
	
	private void addAfter(Node<E> node, E item)
	{
		Node<E> newNode=new Node<E>(item, node.next, node);
		node.next=newNode;
		if(newNode.next==head)
		{
			tail=newNode;
			head.previous=newNode;
		}
		else
			newNode.next.previous=newNode;
		
		size++;
	}
	
	private E removeAfter(Node<E> node)throws NoElement
	{
		if(node.next==null)
			throw new NoElement("There is no element to remove.");
		
		E temp=node.next.data;
		
		if(node.next==tail)
			tail=node;
		
		node.next=node.next.next;
		node.next.next.previous=node;
		
		size--;
		
		return temp;
	}
	
	private E removeFirst() throws NoElement
	{
		if(head==null)
			throw new NoElement("There is no element to remove.");
		
		E temp=head.data;
		
		head=head.next;
		head.previous=tail;
		tail.next=head;
		
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
		Node<E> temp=null;
		try
		{
			temp=getNode(index);
			temp.data=element;
		}
		catch(InvalidIndex exc)
		{
			System.err.print(exc);
			System.exit(-1);
		}
		return temp.data;
	}
	
	public int size() {return size;}
	
	public boolean add(E element)
	{
		try
		{
			if(head==null)
				addFirst(element);
			else
			{
				addAfter(getNode(size()-1), element);
			}
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
		try
		{
			if(index==0)
				addFirst(element);
			else if(index==size)
				add(element);
			else
				addAfter(getNode(index-1),element);
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
		for(int i=0; i<size; ++i)
		{
			if(temp.equals(target))
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
