import java.util.Arrays;

public class KWArrayList<E>
{
	private E data[]=null;
	private int size=0;
	private int capacity=0;
	
	public KWArrayList()
	{
		this(10);
	}
	public KWArrayList(int capVal)
	{
		try
		{
			setCap(capVal);
			data=(E[])new Object[capacity];
		}
		catch(InvalidCapacity exc)
		{
			System.err.print(exc);
			System.exit(-1);
		}
	}
	public Object clone()
	{
		KWArrayList<E> temp=new KWArrayList<E>(capacity);
		temp.data=Arrays.copyOf(data, capacity);
		
		return temp;
	}
	
	private void setCap(int newCapVal)throws InvalidCapacity
	{
		if(newCapVal>=0)
			capacity=newCapVal;
		else
			throw new InvalidCapacity("There cannot be negative amount of capacity.");
	}
	
	private void reallocate()
	{
		capacity*=2;
		data=Arrays.copyOf(data, capacity);
	}
	
	public E get(int index)throws InvalidIndex
	{
		if(index<0 || index>=size)
			throw new InvalidIndex("There is no elements in this index.");
		
		return data[index];
	}
	
	public int size() {return size;}
	
	public E set(int index, E element)throws InvalidIndex
	{
		if(index<0 || index>=size)
			throw new InvalidIndex("There is no elements in this index.");
		
		E temp=data[index];
		data[index]=element;
		
		return temp;
	}
	
	public boolean add(E element)
	{
		if(size>=capacity)
			reallocate();
		
		data[size()]=element;
		size++;
		
		return true;
	}
	
	public void add(int index, E element)throws InvalidIndex
	{
		if(index<0 || index>size)
			throw new InvalidIndex("There is no elements in this index.");
		
		if(size>=capacity)
			reallocate();
		
		size++;
		
		for(int i=index+1; i<size; ++i)
			data[i]=data[i-1];
		
		data[index]=element;
	}
	
	public int indexOf(E target)
	{
		for(int i=0; i<size; ++i)
			if(data[i].equals(target))
				return i;
		
		return -1;
	}
	
	public E remove(int index)throws InvalidIndex
	{
		if(index<0 || index>=size)
			throw new InvalidIndex("There is no elements in this index.");
		
		E temp=data[index];
		size--;
		
		for(int i=index; i<size; ++i)
			data[i]=data[i+1];
		
		return temp;
	}
}
