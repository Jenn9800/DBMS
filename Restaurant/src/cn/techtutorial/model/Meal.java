package cn.techtutorial.model;

public class Meal {

	private int id;
	private String name;
 	private String r_name;

	private double price;
	private int calorie;
	private String category;
	private String image;
	
	public Meal() {
		
	}
	
	public Meal(int id, String name, String r_name,double price, int calorie, String category, String image) {
		super();
		this.id = id;
		this.name = name;
 		this.r_name = r_name;

		this.price = price;
		this.calorie = calorie;
		this.category = category;
		this.image = image;
	}
	public Meal(String name,String r_name, double price, int calorie, String category, String image) {
		super();
		this.name = name;
 		this.r_name = r_name;

		this.price = price;
		this.calorie = calorie;
		this.category = category;
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCalorie() {
		return calorie;
	}
	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
}
