
/**
 * A class to represent a binary search tree.
 * @author Koffman and Wolfgang
 */
public class BinarySearchTree<E extends Comparable<E>>
         extends BinaryTree<E> {
 
    /** Return value from the public add method. */
    protected boolean isAdded;
    /** Return value from the public delete method. */
    protected E removed;

    public BinarySearchTree()
    {
    	super();
    }
    
    public BinarySearchTree(E item)
    {
    	super();
    	add(item);
    }
    
    /**
     * find method.
     * @param target 
     * @return The object, if found, otherwise null
     */
    public E find(E target) {
        return find(root, target);
    }

    /**
     * Recursive find method.
     * @param localRoot The local subtree's root
     * @param target The object being sought
     * @return The object, if found, otherwise null
     */
    private E find(Node<E> localRoot, E target) 
    {
        if (localRoot == null) 
            return null;
        int compResult = target.compareTo(localRoot.data);
        if (compResult == 0) 
            return localRoot.data;
        else if (compResult < 0)
            return find(localRoot.left, target);
        else
            return find(localRoot.right, target);
    }
    /**
     * add method.
     * @param item The object being inserted
     * @return true if the object is inserted, false
     *         if the object already exists in the tree
     */
    public boolean add(E item) 
    {
    	isAdded=false;
		root=add(root, item);
				
		return isAdded;
    }

    /**
     * Recursive add method.
     * @param localRoot The local root of the subtree
     * @param item The object to be inserted
     * @return The new local root that now contains the
     *         inserted item
     */
    private Node<E> add(Node<E> localRoot, E item) 
    {
        if (localRoot == null) 
        {
            isAdded = true;
            size++;
            return new Node<E>(item);
        } 
        else if (item.compareTo(localRoot.data) == 0) 
        {
            isAdded = false;
            return localRoot;
        }
        else if (item.compareTo(localRoot.data) < 0) 
        {
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        } 
        else 
        {
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    /**
     * Starter method delete.
     * @param target The object to be deleted
     * @return The object deleted from the tree
     *         or null if the object was not in the tree
     * @throws ClassCastException if target does not implement
     *         Comparable
     */
    public E delete(E target) 
    {
    	removed=null;
		root=delete(root, target);
		
		return removed;
    }

    /**
     * Recursive delete method.
     * @param localRoot The root of the current subtree
     * @param item The item to be deleted
     * @return The modified local root that does not contain
     *         the item
     */
    private Node<E> delete(Node<E> localRoot, E item) 
    {
    	if (localRoot == null) 
		 {
			 // item is not in the tree.
			 removed = null;
			 return localRoot;
		 }
		 // Search for item to delete.
		 int compResult = ((Comparable<E>)item).compareTo(localRoot.data);
		 if (compResult < 0) 
		 {
			 // item is smaller than localRoot.data.
			 localRoot.left = delete(localRoot.left, item);
			 return localRoot;
		 } 
		 else if (compResult > 0) 
		 {
			 // item is larger than localRoot.data.
			 localRoot.right = delete(localRoot.right, item);
			 return localRoot;
		 } 
		 else 
		 {
			 // item is at local root.
			 removed = localRoot.data;
			 size--;
			 if (localRoot.left == null) 
			 {
				 // If there is no left child, return right child
				 // which can also be null.
				 return localRoot.right;
			 } 
			else if (localRoot.right == null) 
			{
				 // If there is no right child, return left child.
				 return localRoot.left;
			} 
			else 
			{
				 // Node being deleted has 2 children, replace the data
				 // with inorder predecessor. 
				 if (localRoot.left.right == null) 
				 {
					 // The left child has no right child.
					 // Replace the data with the data in the
					 // left child.
					 localRoot.data = localRoot.left.data;
					 // Replace the left child with its left child.
					 localRoot.left = localRoot.left.left;
					 return localRoot;
				 } 
				 else 
				 {
					 // Search for the inorder predecessor (ip) and
					 // replace deleted node's data with ip.
					 localRoot.data = findLargestChild(localRoot.left);
					 return localRoot;
				 }
			 }
		 }
    }

    /**
     * Find the node that is the
     * inorder predecessor and replace it
     * with its left child (if any).
     * @param parent 
     * @return The data in the ip
     */
    private E findLargestChild(Node<E> parent) 
    {
        // If the right child has no right child, it is
        // the inorder predecessor.
        if (parent.right.right == null) 
        {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } 
        else 
        {
            return findLargestChild(parent.right);
        }
    }
    
    /**
	 * @param target is the element that will be searched inside the tree
	 * @return true if the element is found, false if not
	 */
	public boolean contains(E target) 
	{
		if(find(target)!=null)
			return true;
		
		return false;
	}
}