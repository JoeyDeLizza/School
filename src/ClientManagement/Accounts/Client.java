package Accounts;

import java.io.Serializable;
import java.util.ArrayList;

public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private ArrayList<Portfolio> portfolios = new ArrayList<>();
	
	public Client(Client c) {
		this.firstName = c.firstName;
		this.lastName = c.lastName;
		this.phoneNumber = c.phoneNumber;
		this.portfolios = c.portfolios;
	}
	
	public Client(String fn, String ln, String pn) {
		this.firstName = fn;
		this.lastName = ln;
		this.phoneNumber = pn;
	}
	
	public Client(String fn, String ln, String pn, Portfolio... portfolios) {
		this.firstName = fn;
		this.lastName = ln;
		this.phoneNumber = pn;
		for (int i = 0; i < portfolios.length; i++) {
			this.portfolios.add(portfolios[i]);
		}
	}
	
	public void addPortfolio(Portfolio p) {
		portfolios.add(p);
	}
	
	public Portfolio getClientPortfolioByName(String s) {
		for(Portfolio p : portfolios) {
			if (p.getName() == s) {
				return p;
			}
		}
		return null;
		
	}
	
	public ArrayList<String> getClientPortfolioNames() {
		ArrayList<String> names = new ArrayList<String>();
		for(Portfolio p : portfolios) {
			names.add(p.getName());
		}
		return names;
		
	}
	
	public double getClientPortfolioValue() {
		double sum = 0;
		for (Portfolio p : portfolios) {
			sum += p.getPortfolioTotalValue();
		}
		return sum;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getID() {
		return this.firstName + this.lastName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public int getPortfoliocount() {
		return portfolios.size();
	}
	
	public ArrayList<Portfolio> getPortfolios() {
		return portfolios;
	}
	
	public boolean removePortfolio(Portfolio p) {
		return portfolios.remove(p);
	}
	
	public void setFirstName(String fn) {
		this.firstName = fn;
	}
	
	public void setLastName(String ln) {
		this.lastName = ln;
	}
	
	public void setPhoneNumber(String pn) {
		this.phoneNumber = pn;
	}
	
	public void setPortfolios(ArrayList<Portfolio> p) {
		this.portfolios = new ArrayList<Portfolio>(p);
	}
}
