package internals;

public interface IQueue<T> {
	/**
	 *  Add a new element to the back of the queue
	 * @param newElement
	 * @return true if sucessful, false otherwise.
	 */
	public boolean enqueue(T newElement);
	/**
	 * Remove an item from the front of the queue.
	 * @return the item dequeued
	 */
	public T dequeue();
	/**
	 * Return the item at the front of the queue without changing it.
	 * @return Item at the front.
	 */
	public T peek();
	/**
	 * Delete every item at the queue.
	 * @return true if sucessfull, false otherwise.
	 */
	public boolean clear();
	/**
	 * Return an array consisting of the elements inside the queue
	 * @return the array.
	 */
	public T[] toArray();
	/**
	 * Return the number of elements inside the queue.
	 * @return number of elements inside the queue.
	 */
	public int getSize();
}
