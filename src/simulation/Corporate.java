package simulation;

public class Corporate implements ICustomer {
	private static String type;
	private static int priority;
	
	public Corporate() {
		type = "Corporate";
		priority = 1;
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
		return "COR";
	}

}
