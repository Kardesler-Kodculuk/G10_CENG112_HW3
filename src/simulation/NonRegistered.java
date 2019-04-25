package simulation;

public class NonRegistered implements ICustomer {
	private static String type;
	private static int priority;
	
	public NonRegistered() {
		type = "Non-Registered";
		priority = 3;
		
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
		return "NON";
	}

}
