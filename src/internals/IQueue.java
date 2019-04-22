package internals;

public interface IQueue<T> {
	public boolean enqueue(T newElement);
	public T dequeue();
	public T peek();
	public boolean clear();
	public T[] toArray();
	public int getSize();
}
