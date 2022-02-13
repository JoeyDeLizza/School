import java.util.Iterator;

public class CircularList<E> implements Iterable<E> {
	
	private Node<E> CurrentNode;
	private int size = 0;
	
	
	/**
	 * Empty Constructor
	 */
	CircularList() {
		CurrentNode = null;
	}
	
	/**
	 * Constructor that adds an item to the list
	 * @param item Item to be added
	 */
	CircularList(E item) {
		add(item);
	}
	
	/**
	 * Constructor that adds an item to the list
	 * @param item Item to be added
	 */
	CircularList(CircularList<E> list) {
		this.CurrentNode = list.CurrentNode;
		this.size = list.size;
	}
	/**
	 * Insert the specified item at index relative to the current node
	 * @param index The position where the item is inserted
	 * @param item The item to be inserted
	 */
	public void add(int index, E item) {
		if (index < 0) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if (index == 0 && size == 0) {
			addFirst(item);
		} else if (index == 0) {
			addAfter(CurrentNode, item);
		} else {
			Node<E> node = getNode(index-1);
			addAfter(node, item);
		}
	}
	
	/**
	 * Adds an item to the end of the list
	 * @param item Item that gets added
	 * @return Returns true
	 */
	public boolean add(E item) {
		if (size > 0)
			addAfter(CurrentNode, item);
		else
			addFirst(item);
			return true;
	}
	
	/**
	 * Add node after a given node and make CurrentNode point to the new node
	 * @param node Node before new item
	 * @param item New item to be inserted
	 */
	private void addAfter(Node<E> node, E item) {
		Node<E> newNode = new Node<E>(item, node.getNext());
		node.setNext(newNode);
		this.setCurrentNode(newNode);;
		this.size++;
	}
	
	/**
	 * Add first item to list and set next to itself
	 * @param item The item to be added
	 */
	private void addFirst(E item) {
		setCurrentNode(new Node<E>(item));
		CurrentNode.setNext(CurrentNode);
		this.size++;
	}
	
	/**
	 * Get the data at the index
	 * @param index The position of the data to return 
	 * @return The data at index
	 */
	public E get(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<E> node = getNode(index);
		return node.getData();
	}
	
	/**
	 * Find the node at specified position relative to CurrentNode
	 * @param index The position of the node sought
	 * @return The node at index or null if it does not exist
	 */
	private Node<E> getNode(int index) {
		Node<E> node = getCurrentNode();
		for (int i = 0; i < index && node != null; i++) {
			node = node.getNext();
		}
		return node;
	}
	
	@Override
	public Iterator<E> iterator() {

            return new ListIterator<E>(this);
	}
	
	/**
	 * Store a reference to anEntry in the element at position index
	 * @param index The position of the data to return
	 * @param newValue The data at index
	 * @return The data at index
	 */
	public E set(int index, E newValue) {
		if (index < 0) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<E> node = getNode(index);
		E result = node.getData();
		node.setData(newValue);
		return result;
	}
	
	/**
	 * Gets the size of list
	 * @return size of list
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Removes object at given index relative to CurrentNode from list
	 * @param index Index of node in list
	 * @return returns data of removed node or null
	 */
	public E remove() {
		
		return this.removeCurrentNode();
	}
	
	/**
	 * Removes object at given index relative to CurrentNode from list
	 * @param index Index of node in list
	 * @return returns data of removed node or null
	 */
	public E remove(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if (index == 0) {
			return this.removeCurrentNode();
		} else {
			Node<E> node = getNode(index-1);
			return removeAfter(node);
		}
	}
	
	/**
	 * Remove node after given node
	 * @param node Node before the one to be removed
	 * @return The data of the removed node or null
	 */
	private E removeAfter(Node<E> node) {
		Node<E> temp = node.getNext();
		if (temp != null) {
			node.setNext(temp.getNext());
			//this.setCurrentNode(temp.getNext());
			this.size--;
			if (size == 0)
				CurrentNode = null;
			return temp.getData();
		} else {
			return null;
		}
	}
	
	/**
	 * Remove the first node from the list
	 * @return The removed node's data or null
	 */
	private E removeCurrentNode() {
		Node<E> temp = getNode(size-1);
		if (temp != null) {
			setCurrentNode(getNode(1));
			temp.setNext(CurrentNode);
			this.size--;
			if (size == 0)
				CurrentNode = null;
			return temp.getData();
		} else {
			return null;
		}
	}
	
	public Node<E> getCurrentNode() {
		return CurrentNode;
	}

	public void setCurrentNode(Node<E> c) {
		this.CurrentNode = c;
	}

	class ListIterator<E> implements Iterator<E> {
		Node<E> current;
		int count = 0;
		
		public ListIterator (CircularList<E> list) {
			current = list.getCurrentNode();
		}
		
		@Override
		public boolean hasNext() {
			return count < size;
		}

		
		@Override
		public E next() {
			E data = current.getData();
			current = current.getNext();
			count++;
			return data;
		}
		
		@Override
		public void remove() {
			CircularList.this.remove();		
		}
		
	}

}


