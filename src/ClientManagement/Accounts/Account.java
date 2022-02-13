package Accounts;

import java.io.Serializable;

public abstract class Account implements Serializable{
	private static final long serialVersionUID = 3L;

	private String accountNumber;
	private double balance;
	protected String type;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public double getBalance() {
		return balance;
	}
	
	protected void initializeAccount(double bal, String accNum) {
		this.balance = bal;
		this.accountNumber = type + accNum;
	}
	
	public String toString() {
		return accountNumber +  " " + "($" + balance + ")";
	}
}
