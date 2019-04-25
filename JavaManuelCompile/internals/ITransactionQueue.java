package internals;

import simulation.Transaction;

public interface ITransactionQueue {
	/**
	 * Insert a new transaction into the queue, analogous to enqueue.
	 * @param T the new transaction
	 */
	public void insert(Transaction T);
	/**
	 * Remove the next transaction from the queue, anologous to dequeue.
	 * @return Transaction which was removed or Null.
	 */
	public Transaction remove();
	/**
	 * Return if the queue is empty.
	 * @return
	 */
	public boolean isEmpty();
	/**
	 * Delete the queue alltogether.
	 * @return
	 */
	public boolean clear();
	/**
	 *
	 * @return a string representation of the queue.
	 */
	public String toString();
	/**
	 * Get the next transaction queue.
	 * @return
	 */
	public ITransactionQueue getNextTransactionQueue();
	/**
	 * Set the transaction queue.
	 */
	public void setNextTransactionQueue(ITransactionQueue next);
	/**
	 * Get the total waiting time up onto this point.
	 * @return
	 */
	public int getTotalWaitingTime();
	/**
	 * Get the number of transaction in the queue.
	 * @return
	 */
	public int getSize();
	/**
	 * 
	 * @return the Date of queue length.
	 */
	public String getDate();
	/**
	 * 
	 * @param Date - Date the transaction queue is created from.
	 */
	public void setDate(String Date);
}
