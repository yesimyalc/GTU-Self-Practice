
public class BinaryTree<E>
{
	Node<E> root;
	
	protected static class Node<E>
	{
		Node<E> left;
		Node<E> right;
		E data;
		
		public Node()
		{
			data=null;
			left=null;
			right=null;
		}
		
		public Node(E item)
		{
			data=item;
			left=null;
			right=null;
		}
		
		public Node(E item, E rightItem, E leftItem)
		{
			data=item;
			right=new Node<E>(rightItem);
			left=new Node<E>(leftItem);
		}
		
		public Node(E item, Node<E> rightItem, Node<E> leftItem)
		{
			data=item;
			right=rightItem;
			left=leftItem;
		}
		
		public String toString()
		{
			return data.toString();
		}
		
		public E getData() {return data;}
		public Node<E> getRight() {return right;}
		public Node<E >getLeft() {return left;}
	}
	
	public BinaryTree()
	{
		root=new Node<E>();
	}
	
	public BinaryTree(E data, BinaryTree<E> ldata, BinaryTree<E> rdata)
	{
		root=new Node<E>(data);
		if(ldata!=null)
			root.left=ldata.root;
		if(rdata!=null)
			root.right=rdata.root;
	}
	
	protected BinaryTree(Node<E> item)
	{
		root=item;
	}
	
	public E getRoot() {return root.data;}
	
	public boolean isEmpty()
	{
		if(root==null || root.getData()==null)
			return true;
		else
			return false;
	}
	
	public boolean isLeaf()
	{
		if(isEmpty() || root.getLeft()!=null || root.getRight()!=null)
			return false;
		else
			return true;
	}
	
	public BinaryTree<E> getLeftSubtree()
	{
		if(isEmpty() || root.getLeft()==null)
			return null;
		else
			return new BinaryTree<E>(new Node<E>(root.getLeft().data));
	}
	
	public BinaryTree<E> getRightSubtree()
	{
		if(isEmpty() || root.getRight()==null)
			return null;
		else
			return new BinaryTree<E>(new Node<E>(root.getRight().data));
	}
	
	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb)
	{
		for(int i=0; i<depth; i++)
			sb.append(" ");
		if(node==null)
			sb.append("null\n");
		else
		{
			sb.append(node.toString());
			sb.append("\n");
			preOrderTraverse(node.left, depth+1, sb);
			preOrderTraverse(node.right, depth+1, sb);
		}
	}
	
	public String toString()
	{
		StringBuilder sb=new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	}
	
	private void inOrderPrint(Node<E> node)
	{
		if(node==null)
			System.out.println("null\n");
		else
		{
			inOrderPrint(node.left);
			System.out.println(node.toString());
			inOrderPrint(node.right);
		}
	}
	
	public void inOrderPrint()
	{
		inOrderPrint(root);
	}
	
	private void postOrderPrint(Node<E> node)
	{
		if(node==null)
			System.out.println("null\n");
		else
		{
			postOrderPrint(root.left);
			postOrderPrint(root.right);
			System.out.println(root.toString());
		}
	}
	
	public void postOrderPrint()
	{
		postOrderPrint(root);
	}
	
}
