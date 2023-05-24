package cn.techtutorial.model;

public class DietRecord {
	private int userID;
	private String date;
	private double calLimit;
	private double calIngested;
	private double remainingCal;
	
	public DietRecord() {
		
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
	
	public double getCalLimit() {
		return this.calLimit;
	}
	
	public void setCalLimit(double calLimit) {
		this.calLimit = calLimit;
	}
	
	public double getCalIngested() {
		return this.calIngested;
	}
	
	public void setCalIngested(double calIngested) {
		this.calIngested = calIngested;
	}
	
	public double getRemainingCal() {
		return this.remainingCal;
	}
	
	public void setRemainingMoney() {
		this.remainingCal = this.calLimit - this.calIngested;
	}
	
	public void setRemainingCal(double remainingCal) {
		this.remainingCal = remainingCal;
	}
}
