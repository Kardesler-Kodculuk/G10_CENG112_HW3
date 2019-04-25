package simulation;

public class Individual implements ICustomer {
	private static String type;
	private static int priority;
	
	public Individual() {
		type = "Individual";
		priority = 2;
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
