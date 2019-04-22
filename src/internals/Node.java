package internals;

/**
 * Node class to be used with other data types.
 * @param <T> Any reference type.
 */
class Node<T> {
	private T element;
	private Node<T> nextNode;

	/**
	 * Create a new Node element.
	 * @param newElement - New element to set
	 * @param newNode - New node coming after this node.
	 */
	public Node(T newElement, Node<T> newNode) {
		this.element = newElement;
		this.nextNode = newNode;
	}
	
	/**
	 * Create a terminal node
	 * @param newElement - Value of the element.
	 */
	public Node(T newElement) {
		this(newElement, null);
	}
	
	/**
	 * Create an empty node.
	 */
	public Node() {
		this(null, null);
	}

	/**
	 * @return the element
	 */
	T getElement() {
		return element;
	}

	/**
	 * @param element the element to set
	 */
	void setElement(T element) {
		this.element = element;
	}

	/**
	 * @return the nextNode
	 */
	Node<T> getNextNode() {
		return nextNode;
	}

	/**
	 * @param nextNode the nextNode to set
	 */
	void setNextNode(Node<T> nextNode) {
		this.nextNode = nextNode;
	}
}
