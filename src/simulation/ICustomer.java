package simulation;

public interface ICustomer {
	/**
	 * The type of the customer can be: Individual, Corporate or Non-Registered.
	 * @return the type of the customer.
	 */
	public String getType();
	/**
	 * Priority is 1 for Individual, 2 for corporate, and 3 for non registered.
	 * @return
	 */
	public int getPriority();
	/**
	 * String consists of type and priority.
	 * @return
	 */
	public String toString();
}
