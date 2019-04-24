package simulation;

public class Individual implements ICustomer {
	private static String type = "Individual";
	private static int priority = 2;
	private boolean initilised;
	
	public Individual() {
		initilised = true;
	}
	
	@Override
	public String getType() {
		return type;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return priority;
	}
	
	@Override
	public String toString() {
		return "IND";
	}

}
