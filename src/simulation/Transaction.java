package simulation;

//TODO 

public class Transaction {
	private int id; // unique id in [1,1000]
	private ICustomer customer; // the request owner
	private int occupation; // needed time for transaction
	private int waiting; // needed time for previous transactions
	private Transaction next;
	
	/**
	 * Creates a transaction node with next transaction
	 * @param id
	 * @param customer
	 * @param occupation
	 * @param waiting
	 * @param next
	 */
	public Transaction(int id, ICustomer customer, int occupation, int waiting, Transaction next) {
		super();
		this.id = id;
		this.customer = customer;
		this.occupation = occupation;
		this.waiting = waiting;
		this.next = next;
	}
	
	/**
	 * Creates a terminal transaction node
	 * @param id
	 * @param customer
	 * @param occupation
	 * @param waiting
	 */
	public Transaction(int id, ICustomer customer, int occupation, int waiting){
		this(id, customer, occupation, waiting, null);
	}
	
	/**
	 * Creates an empty Transaction node
	 */
	public Transaction(){
		this(-1, null, -1, -1, null);
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", customer=" + customer + ", occupation=" + occupation + ", waiting="
				+ waiting + ", next=" + next + "]";
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the customer
	 */
	public ICustomer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(ICustomer customer) {
		this.customer = customer;
	}

	/**
	 * @return the occupation
	 */
	public int getOccupation() {
		return occupation;
	}

	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(int occupation) {
		this.occupation = occupation;
	}

	/**
	 * @return the waiting
	 */
	public int getWaiting() {
		return waiting;
	}

	/**
	 * @param waiting the waiting to set
	 */
	public void setWaiting(int waiting) {
		this.waiting = waiting;
	}

	/**
	 * @return the next
	 */
	public Transaction getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(Transaction next) {
		this.next = next;
	}

}
