package internals;

class Node<T> {
	private T element;
	private Node<T> nextNode;

	public Node(T newElement, Node<T> newNode) {
		this.element = newElement;
		this.nextNode = newNode;
	}
}
