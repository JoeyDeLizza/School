
public class Node<E> {
	private E data;
	private Node<E> next;
	
	public Node(E dataItem) {
		setData(dataItem);
		this.setNext(null);
	}

	public Node(E dataItem, Node<E> nodeRef) {
		setData(dataItem);
		this.setNext(nodeRef);
	}

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}
	
	public String toString() {
		return data.toString();
	}
	
}
