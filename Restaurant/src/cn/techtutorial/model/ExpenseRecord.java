package cn.techtutorial.model;

public class ExpenseRecord {
	private int userID;
	private String date;
	private int budget;
	private int moneySpent;
	private int remainingMoney;
	
	public ExpenseRecord() {
		
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	
	public int getBudget() {
		return this.budget;
	}
	
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	public int getMoneySpent() {
		return this.moneySpent;
	}
	
	public void setMoneySpent(int moneySpent) {
		this.moneySpent = moneySpent;
	}
	
	public int getRemainingMoney() {
		return this.remainingMoney;
	}
	
	public void setRemainingMoney() {
		this.remainingMoney = this.budget - this.moneySpent;
	}
	
	public void setRemainingMoney(int remainingMoney) {
		this.remainingMoney = remainingMoney;
	}
}
