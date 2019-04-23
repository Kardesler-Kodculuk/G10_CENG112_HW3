package simulation;

public class NonRegistered implements ICustomer {
	private static String type = "Non-Registered";
	private static int priority = 3;
	private boolean initilised;
	
	public NonRegistered() {
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
		return (getType() + " | " + ((Integer) getPriority()).toString());
	}

}
