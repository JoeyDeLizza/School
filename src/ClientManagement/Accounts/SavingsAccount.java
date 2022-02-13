package Accounts;

import Entity.Valuable;

public class SavingsAccount extends Account implements Valuable {

	public SavingsAccount(double bal, String accNum) {
		type = "S";
		initializeAccount(bal*1.05, accNum);
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return toString();
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return getBalance();
	}

}
