package Accounts;

import Entity.Valuable;

public class CheckingAccount extends Account implements Valuable {

	public CheckingAccount(double bal, String accNum) {
		type = "C";
		initializeAccount(bal*1.1, accNum);
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
