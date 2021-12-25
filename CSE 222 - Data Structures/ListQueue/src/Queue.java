import java.util.EmptyStackException;
import java.util.LinkedList;

public class Queue<E> 
{
	LinkedList<E> queueList;
	
	public Queue()
	{
		queueList=new LinkedList<E>();
	}
	
	public Queue(LinkedList<E> data)
	{
		queueList=data;
	}
	
	public boolean offer(E item)
	{
		queueList.addLast(item);
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
		if(queueList.isEmpty())
			return null;
		
		return queueList.getFirst();
	}
	
	public E poll()
	{
		if(queueList.isEmpty())
			return null;
		
		E temp=queueList.getFirst();
		queueList.removeFirst();
		
		return temp;
	}
	
	public E element()
	{
		E temp=peek();
		
		if(temp==null)
			throw new EmptyStackException();
		
		return temp;
	}
}
