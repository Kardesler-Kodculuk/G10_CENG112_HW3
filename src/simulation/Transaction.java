package simulation;

public class Transaction {
	private String date; // date of the transaction
	private int id; // unique id in [1,1000]
	private ICustomer customer; // the request owner
	private int occupation; // needed time for transaction
	private int waiting; // needed time for previous transactions
	private Transaction next;

	/**
	 * Creates a transaction node with next transaction
	 * 
	 * @param date
	 * @param id
	 * @param customer
	 * @param occupation
	 * @param waiting
	 * @param next
	 */
	public Transaction(String date, int id, ICustomer customer, int occupation, int waiting, Transaction next) {
//		super();
		this.date = date;
		this.id = id;
		this.customer = customer;
		this.occupation = occupation;
		this.waiting = waiting;
		this.next = next;
	}

	/**
	 * Creates a terminal transaction node
	 * 
	 * @param id
	 * @param customer
	 * @param occupation
	 * @param waiting
	 */
	public Transaction(String date, int id, ICustomer customer, int occupation, int waiting) {
		this(date, id, customer, occupation, waiting, null);
	}

	/**
	 * Creates an empty Transaction node
	 */
	public Transaction() {
		this(null, -1, null, -1, -1, null);
	}

	@Override
	public String toString() {
		return String.valueOf(id) + "|" + customer.toString() + "|" + String.valueOf(occupation) + "|"
				+ String.valueOf(waiting);
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
