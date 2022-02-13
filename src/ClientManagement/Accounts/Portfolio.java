package Accounts;

import java.io.Serializable;
import java.util.ArrayList;

import Entity.Valuable;

public class Portfolio implements Valuable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private ArrayList<Account> accounts = new ArrayList<>();
	private String name;
	
	public Portfolio(String name) {
		this.name = name;
	}
	
	public Portfolio(String name, Account...accounts ) {
		
	}
	
	public Account addAccount(Account a) {
		this.accounts.add(a);
		return a;
		
	}
	
	public String getAccountByNumber(String s) {
		// fix
		return s;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Account> getPortfolioAccounts() {
		return this.accounts;
	}
	
	public ArrayList<String> getPortfolioMemberDescriptions() {
		
		ArrayList<String> memberDesc = new ArrayList<>();
		for (Account a : accounts) {
			if (a instanceof CheckingAccount) {
				memberDesc.add(((CheckingAccount) a).getDescription());
			} 
			else if (a instanceof SavingsAccount) {
				memberDesc.add(((SavingsAccount) a).getDescription());
			}
		}
		return memberDesc;
	}
	
	public ArrayList<String> getPortfolioMemberNamesWithTypes() {
		ArrayList<String> nameList = new ArrayList<String>();
		for (Account a : accounts) {
			nameList.add(a.getAccountNumber());
		}
		return nameList;
	}
	
	public ArrayList<String> getPortfolioMemberNamesWithTypesAndBal() {
		ArrayList<String> nameList = new ArrayList<String>();
		for (Account a : accounts) {
			nameList.add(a.getAccountNumber() + " ($" + a.getBalance() + ")");
		}
		return nameList;
	}
	
	public ArrayList<String> getPortfolioMemberTypes() {
		ArrayList<String> typeList = new ArrayList<String>();
		for (Account a : accounts) {
			typeList.add(a.type);
		}
		return typeList;
	}
	
	public ArrayList<Double> getPortfolioMemberValues() {
		// Fix
		ArrayList<Double> MemberValues = new ArrayList<>();
		for(Account a : accounts) {
			MemberValues.add(a.getBalance());
		}
		return MemberValues;
	}
	
	public double getPortfolioTotalValue() {
		// Fix
		ArrayList<Double> MemberValues = getPortfolioMemberValues();
		int sum = 0;
		for (Double d : MemberValues) {
			sum+= d;
		}
		return sum;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return getPortfolioTotalValue();
	}
	
	
}
