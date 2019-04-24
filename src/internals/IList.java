package internals;

//TODO didnt start yet

public interface IList<T> {
	
	/**
	 * adds new entry to the end of list
	 * 
	 * @param newEntry
	 */
	public void add(T newEntry);
	/**
	 * adds new entry to the given position
	 * 
	 * @param givenPosition
	 * @param newEntry
	 * @throws IndexOutOfBounds exception
	 */
	public void add(int givenPosition, T newEntry);
	/**
	 * removes an entry by the given position
	 * 
	 * @param givenPosition
	 * @return removed entry
	 * @throws IndexOutOfBounds exception
	 */
	public T remove(int givenPosition);
	/**
	 * clears the list
	 */
	public void clear();
	/**
	 * replace an entry by its position with new entry
	 * 
	 * @param givenPosition
	 * @param newEntry
	 * @return replaced entry
	 */
	public T replace(int givenPosition, T newEntry);
	/**
	 * retrieves an entry by its position
	 * 
	 * @param givenPosition
	 * @return an entry w.r.t. given position
	 */
	public T getEntry(int givenPosition);
	/**
	 * converts list to an array
	 * 
	 * @return an array type of T
	 */
	public T[] toArray();
	/**
	 * checks list contains the entry or not
	 * 
	 * @param anEntry
	 * @return true if contains, false if not
	 */
	public boolean contains(T anEntry);
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
