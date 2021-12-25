import java.util.Arrays;

public class Stack<E>
{
	private E data[];
	private int topIndex;
	private int capacity;
	
	public Stack()
	{
		this(10);
	}
	
	public Stack(int capVal)
	{
		setCap(capVal);
		topIndex=-1;
		data=(E[]) new Object[capacity];
	}
	
	public Stack(int capVal, E obj)
	{
		topIndex=-1;
		setCap(capVal);
		data=(E[]) new Object[capacity];
		push(obj);
	}
	
	private void setCap(int newCapVal)
	{
		if(newCapVal<0)
			throw new ArrayIndexOutOfBoundsException();
		
		capacity=newCapVal;
	}
	
	private void reallocate()
	{
		capacity*=2;
		data=Arrays.copyOf(data, capacity);
	}
	
	public E push(E obj)
	{
		if(topIndex==capacity-1)
			reallocate();
		
		topIndex++;
		data[topIndex]=obj;
		
		return obj;
	}
	
	public E pop(E obj)
	{
		if(topIndex==-1)
			throw new ArrayIndexOutOfBoundsException();
		
		E temp=data[topIndex];
		data[topIndex]=null;
		topIndex--;
		
		return temp;
	}
	
	public E peek()
	{
		if(topIndex==-1)
			throw new ArrayIndexOutOfBoundsException();
		
		return data[topIndex];
	}
	
	public boolean empty()
	{
		if(topIndex==-1)
			return true;
		
		return false;
	}
}
