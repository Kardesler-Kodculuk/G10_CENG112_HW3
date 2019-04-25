package internals;

public class TQL implements ITQL<TransactionQueue> {

	private TransactionQueue head;
	private int listLength;

	/**
	 * @param listLength
	 */
	public TQL() {
		this.head = null;
		this.listLength = 0;
	}

	/**
	 * @return the head
	 */
	public TransactionQueue getHead() {
		return head;
	}

	/**
	 * @param head the head to set
	 */
	public void setHead(TransactionQueue head) {
		this.head = head;
	}

	/**
	 * @return the listLength
	 */
	public int getListLength() {
		return listLength;
	}

	/**
	 * @param listLength the listLength to set
	 */
	public void setListLength(int listLength) {
		this.listLength = listLength;
	}

	@Override
	public void add(TransactionQueue newTQ) {
		if (getHead() == null) {
			this.head = newTQ;
		} else {
			TransactionQueue last = head;
			// loop searchs for last node
			while (last.getNextTransactionQueue() != null) {
				last = (TransactionQueue) last.getNextTransactionQueue();
			}
			last.setNextTransactionQueue(newTQ);
		}
		listLength++;

	}

	@Override
	public void add(int givenPosition, TransactionQueue newTQ) {
		int iterationTime = this.getListLength();
		TransactionQueue last = this.getHead();
		// loop searchs for given position
		for (int i = 0; i < iterationTime; i++) {
			if (i + 1 == givenPosition) { // when we are on the given position
				TransactionQueue tempNext = (TransactionQueue) last.getNextTransactionQueue();
				last.setNextTransactionQueue(newTQ);
				last = (TransactionQueue) last.getNextTransactionQueue();
				last.setNextTransactionQueue(tempNext);
				this.listLength++;
			} else {
				last = (TransactionQueue) last.getNextTransactionQueue();
			}
		}
	}

	@Override
	public TransactionQueue remove(int givenPosition) {
		// if given position is points the head
		if (givenPosition == 1) {
			TransactionQueue removedTQ = this.getHead();
			this.setHead((TransactionQueue) this.head.getNextTransactionQueue());
			this.listLength--;
			return removedTQ;
		}
		// if given position is points the last
		else if (givenPosition == this.getListLength()) {
			TransactionQueue beforeLast = head;
			TransactionQueue last = null;
			// loop searches for last position
			while (last.getNextTransactionQueue() != null) {
				beforeLast = (TransactionQueue) beforeLast.getNextTransactionQueue();
				last = (TransactionQueue) beforeLast.getNextTransactionQueue();
			}
			beforeLast.setNextTransactionQueue(null);
			this.listLength--;
			return last;
		}
		// if given position is between head and last
		else {
			int iterationTime = givenPosition - 2;
			TransactionQueue beforeRemoved = this.getHead();
			// loop searches by given position
			for (int i = 0; i < iterationTime; i++) {
				beforeRemoved = (TransactionQueue) beforeRemoved.getNextTransactionQueue();
			}
			TransactionQueue removedTQ = (TransactionQueue) beforeRemoved.getNextTransactionQueue();
			beforeRemoved.setNextTransactionQueue(removedTQ.getNextTransactionQueue());
			this.listLength--;
			return removedTQ;
		}

	}

	@Override
	public void clear() {
		this.setHead(null);
	}

	@Override
	public TransactionQueue replace(int givenPosition, TransactionQueue newTQ) {
		TransactionQueue removedTQ = this.remove(givenPosition);
		this.add(givenPosition, newTQ);
		return removedTQ;
	}

	@Override
	public TransactionQueue getTQ(int givenPosition) {
		int iterationTime = givenPosition - 1;
		TransactionQueue wantedTQ = this.getHead();
		// loop searches for given position
		for (int i = 0; i < iterationTime; i++) {
			wantedTQ = (TransactionQueue) wantedTQ.getNextTransactionQueue();
		}
		return wantedTQ;
	}

	@Override
	public TransactionQueue[] toArray() {
		TransactionQueue[] wantedArray = (TransactionQueue[]) new Object[this.getListLength()];
		int iterationTime = this.getListLength();
		TransactionQueue anyTQ = this.getHead();
		// loop for each TransactionQueue in the list
		for (int i = 0; i < iterationTime; i++) {
			wantedArray[i] = anyTQ;
			anyTQ = (TransactionQueue) anyTQ.getNextTransactionQueue();
		}
		return wantedArray;
	}

	@Override
	public boolean contains(TransactionQueue anTQ) {
		TransactionQueue anyTQ = this.getHead();
		// loop for searching the list to find if it is contains the TQ
		// loop iterates until found the TQ
		while (!anTQ.equals(anyTQ)) {
			// if searching completed and not found
			if (anyTQ == null) {
				return false;
			}
			// not found but there is more TQ to check
			else {
				anyTQ = (TransactionQueue) anyTQ.getNextTransactionQueue();
			}

		}
		return true; // searching not completed but TQ found
	}

	@Override
	public int getLength() {
		return this.getListLength();
	}

	@Override
	public boolean isEmpty() {
		if (this.getHead() == null) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		TransactionQueue traversingNodeSubtype = head;
		String outputString = "";
		for (int i = 0; i < listLength; i++) {
			outputString += traversingNodeSubtype.toString() + "\n";
			traversingNodeSubtype = (TransactionQueue) traversingNodeSubtype.getNextTransactionQueue();
		}
		return outputString;
	}

}
