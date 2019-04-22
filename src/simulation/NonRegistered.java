package simulation;

public class NonRegistered implements ICustomer {
	private static String type = "Customer";
	private static int priority = 1;
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
}
