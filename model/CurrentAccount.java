package model;

public class CurrentAccount extends Account{
	
	private double overdraftLimit;
	
	public CurrentAccount(int accountId, int customerId, Bank bank, String accountType, double balance,double overdraftLimit) {
		super(accountId, customerId, bank, accountType, balance);
		
	}

	public double getOverdraftLimit() {
		return overdraftLimit;
	}

	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}


}
