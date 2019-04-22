package internals;

public class TransactionQueue<T> implements IQueue<T> {

	private Node<T> backNode;
	private Node<T> frontNode;
	private int size;

	
	public TransactionQueue() {
		this.frontNode = new Node<T>();
		this.backNode = this.frontNode;
		this.size = 0;
	}

	@Override
	public boolean enqueue(T newElement) {
		if (this.size == 0) {
			this.frontNode.setElement(newElement);
			this.size++;
		}
		Node<T> newNode = new Node(newElement);
		backNode.setNextNode(newNode);
		this.backNode = newNode;
		this.size++;
		return true;
	}

	@Override
	public T dequeue() {
		Node<T> nextNode = frontNode.getNextNode();
		T element = frontNode.getElement();
		this.frontNode = nextNode;
		this.size--;
		return element;
	}

	@Override
	public T peek() {
		T element = frontNode.getElement();
		return element;
	}

	@Override
	public boolean clear() {
		this.backNode = null;
		this.frontNode = null;
		this.size = 0;
		return true;
	}

	@Override
	public T[] toArray() {
		T[] transferArray = (T[]) new Object[this.size]; //INCOMPLETE
		return null;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
