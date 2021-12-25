import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<E>
{
	private E data[];
	private int size;
	private int capacity;
	private int front;
	private int rear;
	
	public Queue()
	{
		this(10);
	}
	
	public Queue(int capVal)
	{
		setCap(capVal);
		size=0;
		data=(E[])new Object[capacity];
		front=-1;
		rear=capacity-1;	
	}
	
	private void setCap(int newCapVal)
	{
		if(newCapVal<0)
			throw new ArrayIndexOutOfBoundsException();
		
		capacity=newCapVal;
	}
	
	private void reallocate()
	{
		int newCapacity=capacity*2;
		E temp[]=(E[])new Object[capacity];
		
		int j=front;
		for(int i=0; i<size; ++i)
		{
			temp[i]=data[j];
			j=(j+1)%capacity;
		}
		
		front=0;
		rear=size-1;
		capacity=newCapacity;
		
		data=temp;
	}
	
	public boolean offer(E item)
	{
		if(size==capacity)
			reallocate();
		
		rear=(rear+1)%capacity;
		data[rear]=item;
		size++;
		
		return true;
	}
	
	public E poll()
	{
		if(isEmpty())
			return null;
		
		E temp=data[front];
		front=(front+1)%capacity;
		size--;
		
		return temp;
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
		
		return data[front];	
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
	
	private class iter implements Iterator<E>
	{
		int index;
		int count=0;
		
		public iter()
		{
			index=front;
		}
		
		public boolean hasNext()
		{
			if(size>count)
				return true;
			
			return false;
		}
		
		public E next()
		{
			if(!hasNext())
				throw new NoSuchElementException();
			
			E temp=data[index];
			index=(index+1)% capacity;
			count++;
			
			return temp;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}
