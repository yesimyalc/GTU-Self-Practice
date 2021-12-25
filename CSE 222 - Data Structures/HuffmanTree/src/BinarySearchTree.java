
public class BinarySearchTree<E> extends BinaryTree<E> implements SearchTree<E>
{
	private Node<E> add(Node<E> start, E target, Boolean isAdded)
	{
		if(start==null)
		{
			start=new Node<E>(target);
			isAdded=true;
			return start;
		}
		else if(((Comparable) start.data).compareTo(target)<0)
		{
			start.left=add(start.left, target, isAdded);
			return start;
		}
		else if(((Comparable) start.data).compareTo(target)>0)
		{
			start.right=add(start.right, target, isAdded);
			return start;
		}
		else
			return start;
	}
	
	private E find(Node<E> start, E target)
	{
		if(start==null)
			return null;
		else if(((Comparable) start.data).compareTo(target)<0)
			return find(start.left, target);
		else if(((Comparable) start.data).compareTo(target)>0)
			return find(start.right, target);
		else
			return start.data;
	}
	
	private Node<E> delete(Node<E> start, E target, E removed)
	{
		if(start==null)
			return start;
		else if(((Comparable) start.data).compareTo(target)<0)
		{
			start.left=delete(start.left, target, removed);
			return start;
		}
		else if(((Comparable) start.data).compareTo(target)>0)
		{
			start.right=delete(start.right, target, removed);
			return start;
		}
		else
		{
			removed=start.data;
			if(start.left==null)
				return start.right;
			else if(start.right==null)
				return start.left;
			else
			{
				start.data=findLargestChild(start.left);
				return start;
			}
		}
	}
	
	private E findLargestChild(Node<E> start)
	{
		if(start.right.right==null)
		{
			E returnValue=start.right.data;
			start.right=start.right.left;
			return returnValue;
		}
		else
			return findLargestChild(start.right);
	}
	
	public boolean add(E item) 
	{
		Boolean check=new Boolean(false);
		root=add(root, item, check);
				
		return check;
	}

	public boolean contains(E target) 
	{
		if(find(target)==null)
			return false;
		else
			return true;
	}

	public E find(E target) 
	{
		if(isEmpty())
			return null;
		else
			return find(target);
	}

	public E delete(E target) 
	{
		E temp=null;
		root=delete(root, target, temp);
		
		return temp;
	}
	
}
