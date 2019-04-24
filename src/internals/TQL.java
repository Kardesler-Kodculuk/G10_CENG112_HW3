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
		if(getHead() == null) {
			this.head = newTQ;
		}
		else {
			TransactionQueue last = head;
			while(last.getNextTransactionQueue() != null) {
				last = (TransactionQueue) last.getNextTransactionQueue();
			}
			last.setNextTransactionQueue(newTQ);
		}
		listLength++;
		
	}
	
//	private void createRoom(int givenPosition) {
//		int iterationTime = givenPosition;
//		TransactionQueue last = this.getHead();
//		for(int i = 0; i < iterationTime; i++) {
//			if (i + 1 == givenPosition) {
//				last.setNextTransactionQueue(null);
//				last = (TransactionQueue) last.getNextTransactionQueue();
//				this.listLength++;
//			}
//			else {
//				last = (TransactionQueue) last.getNextTransactionQueue();
//			}
//		}
//	}
	@Override
	public void add(int givenPosition, TransactionQueue newTQ) {
		int iterationTime = this.getListLength();
		TransactionQueue last = this.getHead();
		for(int i = 0; i < iterationTime; i++) {
			if (i + 1 == givenPosition) {
				TransactionQueue tempNext = (TransactionQueue) last.getNextTransactionQueue();
				last.setNextTransactionQueue(newTQ);
				last = (TransactionQueue) last.getNextTransactionQueue();
				last.setNextTransactionQueue(tempNext);
				this.listLength++;
			}
			else {
				last = (TransactionQueue) last.getNextTransactionQueue();
			}
		}
	}
	@Override
	public TransactionQueue remove(int givenPosition) {
		if (givenPosition == 1) {
			TransactionQueue removedTQ = this.getHead();
			this.setHead((TransactionQueue) this.head.getNextTransactionQueue());
			this.listLength--;
			return removedTQ;
		}
		else if (givenPosition == this.getListLength()) {
			TransactionQueue beforeLast = head;
			TransactionQueue last = null;
			while(last.getNextTransactionQueue() != null) {
				beforeLast = (TransactionQueue) beforeLast.getNextTransactionQueue();
				last = (TransactionQueue) beforeLast.getNextTransactionQueue();
			}
			beforeLast.setNextTransactionQueue(null);
			this.listLength--;
			return last;
		}
		else {
			int iterationTime = givenPosition - 2;
			TransactionQueue beforeRemoved = this.getHead();
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
		for (int i = 0; i < iterationTime; i++) {
			wantedArray[i] = anyTQ;
			anyTQ = (TransactionQueue) anyTQ.getNextTransactionQueue();
		}
		return wantedArray;
	}
	@Override
	public boolean contains(TransactionQueue anTQ) {
		TransactionQueue anyTQ = this.getHead();
		while(!anTQ.equals(anyTQ)) {
			if (anyTQ == null) {
				return false;
			}
			else {
				anyTQ = (TransactionQueue) anyTQ.getNextTransactionQueue();
			}

		}
		return true;
	}
	@Override
	public int getLength() {
		return this.getListLength();
	}
	@Override
	public boolean isEmpty() {
		if (this.getHead() == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	

	
}
