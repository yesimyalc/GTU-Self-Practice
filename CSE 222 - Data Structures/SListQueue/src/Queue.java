import java.util.EmptyStackException;

public class Queue<E> 
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
	
	private Node<E> front;
	private Node<E> rear;
	private int size;
	
	public Queue()
	{
		size=0;
		front=null;
		rear=null;
	}
	
	public Queue(E element)
	{
		offer(element);
	}
	
	public boolean offer(E item)
	{
		Node<E> newNode=new Node<E>(item);
		if(size==0)
		{
			front=newNode;
			rear=newNode;
		}
		else
		{
			rear.next=newNode;
			rear=newNode;
		}
		size++;
		
		return true;
	}
	
	public E remove()
	{
		E temp=poll();
		if(temp==null)
			throw new EmptyStackException();
		
		return temp;
	}
	
	public E peek()
	{
		if(isEmpty())
			return null;
		
		return front.data;
	}
	
	public E poll()
	{
		if(isEmpty())
			return null;
		
		E temp=front.data;
		front=front.next;
		size--;
		
		return temp;
	}
	
	public E element()
	{
		E temp=peek();
		if(temp==null)
			throw new EmptyStackException();
		
		return temp;
	}
	
	public boolean isEmpty()
	{
		if(size==0)
			return true;
		
		return false;
	}

}
