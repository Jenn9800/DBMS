package cn.techtutorial.model;

public class Record {
	
	private int recordID;
	private int userID;
	private int foodID;
	private String date;
	private String name;
	private int price;
	private double calories;
	private String category;
	
	public Record(){
		
	}
	
	public Record(int userID, String date, int foodID, String name, int price, double calories, String category) {
		super();
		this.userID = userID;
		this.date = date;
		this.foodID = foodID;
		this.name = name;
		this.price = price;
		this.calories = calories;
		this.category = category;
	}
	
	public int getRecordID() {
		return this.recordID;
	}
	
	public void setRecordID(int recordID) {
		this.recordID = recordID;
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
	
	public int getFoodID() {
		return this.foodID;
	}
	
	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public double getCalories() {
		return this.calories;
	}
	
	public void setCalories(double calories) {
		this.calories = calories;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
}
