import java.util.EmptyStackException;
import java.util.LinkedList;

public class Stack<E> 
{
	private LinkedList<E> stackList;
	
	public Stack()
	{
		stackList=new LinkedList<E>();
	}
	
	public Stack(LinkedList<E> data)
	{
		stackList=data;
	}
	
	public E push(E obj)
	{
		stackList.addLast(obj);
		
		return obj;
	}
	
	public E pop()
	{
		if(empty())
			throw new EmptyStackException(); 
		
		E temp=stackList.getLast();
		stackList.removeLast();
		
		return temp;
	}
	
	public E peek()
	{
		if(empty())
			throw new EmptyStackException(); 
		
		return stackList.getLast();
	}
	
	public boolean empty()
	{
		if(stackList.isEmpty())
			return true;
		
		return false;
	}
}
