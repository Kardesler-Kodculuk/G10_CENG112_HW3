package internals;

import simulation.ICustomer;
import simulation.Transaction;

public class TransactionQueue implements ITransactionQueue {

	private Transaction head;
	private int queueLength;
	private int totalWaitingTime;
	private String date;
	private TransactionQueue next;

	public TransactionQueue(String date) {
		head = null;
		queueLength = 0;
		totalWaitingTime = 0;
		this.date = date;
		next = null;
	}

	/**
	 * Update the waiting times when an insertion occurs.
	 */
	private void shiftWaitingTimes() {
		if (head != null && head.getNext() != null) {
			Transaction traversing_transaction = head.getNext();
			Transaction prior_transaction = head;
			for (int i = 1; i < queueLength; i++) {
				traversing_transaction.setWaiting(prior_transaction.getWaiting() + prior_transaction.getOccupation());
				prior_transaction = traversing_transaction;
				traversing_transaction = traversing_transaction.getNext();
			}
		}
	}
	
	/**
	 * Return the transaction object at a certain index.
	 * @param index
	 * @return Transaction if it exists, null, otherwise.
	 */
	private Transaction getElementAt(int index) {
		if (index >= this.queueLength) {
			return null;
		} else {
			Transaction traversingTransaction = head;
			for (int i = 0; i < index; i++) {
				traversingTransaction = traversingTransaction.getNext();
			}
			return traversingTransaction;
		}
	}

	/**
	 * Insert a new element into a given index
	 * @param T Element to be inserted
	 * @param index Index to insert at.
	 */
	private void insert(Transaction T, int index) {
		if (index == 0) {
			T.setNext(head);
			this.head = T;
		} else {
			Transaction priorElement = getElementAt(index - 1);
			Transaction postElement = getElementAt(index);
			T.setNext(postElement);
			priorElement.setNext(T);
		}
		this.queueLength++;
		this.totalWaitingTime += T.getWaiting();
		shiftWaitingTimes();
	}

	@Override
	public void insert(Transaction T) {
		int insertAt;
		ICustomer ThisCustomer = T.getCustomer();
		int ThisPriority = ThisCustomer.getPriority();
		if (head == null) {
			insertAt = 0;
		} else if (ThisPriority < head.getCustomer().getPriority()) {
			insertAt = 0;
		} else {
			insertAt = 0;
			for (int i = 0; i <= this.queueLength; i++) {
				Transaction nextTransaction = getElementAt(i + 1);
				if (nextTransaction == null) {
					insertAt = i + 1;
					break;
				} else {
					ICustomer nextCustomer = nextTransaction.getCustomer();
					if (nextCustomer.getPriority() > ThisPriority) {
						insertAt = i + 1;
						break;
					}
				}
			}
		}
		insert(T, insertAt);
	}

	@Override
	public Transaction remove() {
		if (head != null) {
			Transaction removedTransaction = this.head;
			this.head = getElementAt(1);
			this.queueLength--;
			removedTransaction.setNext(null);
			return removedTransaction;
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return (this.queueLength == 0);
	}

	@Override
	public boolean clear() {
		this.head = null;
		this.queueLength = 0;
		return true;
	}

	@Override
	public ITransactionQueue getNextTransactionQueue() {
		return this.next;
	}

	@Override
	public void setNextTransactionQueue(ITransactionQueue next) {
		this.next = (TransactionQueue) next;
	}

	@Override
	public int getTotalWaitingTime() {
		return this.totalWaitingTime;
	}

	@Override
	public int getSize() {
		return this.queueLength;
	}

	@Override
	public String getDate() {
		return date;
	}

	@Override
	public void setDate(String Date) {
		this.date = Date;
	}
	
	@Override
	public String toString() {
		String str_repr = this.date + " COUNTER";
		String seperator_char = "←";
		String traversing_transaction_string;
		Transaction traversing_transaction = head;
		for (int i = 0; i < queueLength; i++) {
			if (traversing_transaction == null) {
				break;
			} 
			traversing_transaction_string = traversing_transaction.toString();
			str_repr += " " + seperator_char + " " + traversing_transaction_string;
			traversing_transaction = traversing_transaction.getNext();
		}
		return str_repr;
		
	}

}
