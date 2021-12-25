
public class RLList<E>
{
	private static class Node<E>
	{
		E data;
		Node<E> next;
		Node<E> previous;
		
		private Node()
		{
			data=null;
			next=null;
			previous=null;
		}
		
		private Node(E obj)
		{
			data=obj;
			next=null;
			previous=null;
		}
		
		private Node(E obj, Node<E> nextObj)
		{
			data=obj;
			next=nextObj;
			previous=null;
			nextObj.previous=this;
		}
		
		private Node(E obj, Node<E> nextObj, Node<E> pObj)
		{
			data=obj;
			next=nextObj;
			previous=pObj;
			nextObj.previous=this;
			pObj.next=this;
		}
	}
	
	private Node<E> head;
	private Node<E> tail;
	
	public RLList()
	{
		head=null;
		tail=null;
	}
	
	public RLList(E obj)
	{
		head=new Node<E>(obj);
		tail=new Node<E>(obj);
	}
	
	private int size(Node<E> start)
	{
		if(start==null)
			return 0;
		
		return 1 + size(start.next);
	}
	
	private String toString(Node<E> start)
	{
		if(start==null)
			return "";
		
		return start.data +"\n" + toString(start.next);
	}
	
	private void replace(E oldObj, E newObj, Node<E> start)
	{
		if(start==null)
			return;
		else if(start.data.equals(oldObj))
		{
			start.data=newObj;
			return;
		}
		
		replace(oldObj, newObj, start.next);
	}
	
	private void add(E obj, Node<E> start)
	{
		if(start==null)
		{
			Node<E> newNode=new Node<E>(obj);
			start=newNode;
		}
		
		add(obj, start.next);
	}
	
	private boolean remove(E obj, Node<E> start)
	{
		if(start==null)
			return false;
		else if(start.data.equals(obj))
		{
			start.previous=start.next;
			start.next.previous=start.previous;
		}
		else
			remove(obj, start.next);
		
		return true;
	}
	
	public int size()
	{
		return size(head);	
	}
	
	public String toString()
	{
		return toString(head);
	}
	
	public void replace(E oldObj, E newObj)
	{	
		replace(oldObj, newObj, head);
	}
	
	public void add(E obj)
	{
		add(obj, head);
	}
	
	public boolean remove(E obj)
	{
		if(head==null)
			return false;
		if(head.equals(obj) && tail==head)
		{
			head=null;
			tail=null;
		}
		else if(head.equals(obj))
		{
			head=head.next;
			head.previous=null;
		}
		else if(tail.equals(obj))
		{
			tail=tail.previous;
			tail.next=null;
		}
		
		return remove(obj, head);
	}
}
