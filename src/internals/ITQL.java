package internals;

public interface ITQL<T> {

	/**
	 * adds new TQ to the end of list
	 * 
	 * @param newTQ
	 */
	public void add(T newTQ);

	/**
	 * adds new TQ to the given position
	 * 
	 * @param givenPosition
	 * @param newTQ
	 * @throws IndexOutOfBounds exception
	 */
	public void add(int givenPosition, T newTQ);

	/**
	 * removes an TQ by the given position
	 * 
	 * @param givenPosition
	 * @return removed TQ
	 * @throws IndexOutOfBounds exception
	 */
	public T remove(int givenPosition);

	/**
	 * clears the list
	 */
	public void clear();

	/**
	 * replace an TQ by its position with new TQ
	 * 
	 * @param givenPosition
	 * @param newTQ
	 * @return replaced TQ
	 * @throws IndexOutOfBounds exception
	 */
	public T replace(int givenPosition, T newTQ);

	/**
	 * retrieves an TQ by its position
	 * 
	 * @param givenPosition
	 * @return an TQ w.r.t. given position
	 * @throws IndexOutOfBounds exception
	 */
	public T getTQ(int givenPosition);

	/**
	 * converts list to an array
	 * 
	 * @return an array type of T
	 */
	public T[] toArray();

	/**
	 * checks list contains the TQ or not
	 * 
	 * @param anTQ
	 * @return true if contains, false if not
	 */
	public boolean contains(T anTQ);

	/**
	 * retrieves the length of the list
	 * 
	 * @return length of the list type of T
	 */
	public int getLength();

	/**
	 * checks the list is empty or not
	 * 
	 * @return true if empty, false if not
	 */
	public boolean isEmpty();

}
