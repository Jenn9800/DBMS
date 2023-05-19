package cn.techtutorial.model;

public class LouisaMenu {

	private int id;
 	private String name;
 	private String r_name;
	private String category;
	private Double price;
	private String image;
	private int calorie;
	private int r_id;
	
	public LouisaMenu(){
		
	}
	
	public LouisaMenu(String name, String r_name, Double price, int calorie,String category, String image) {
		super();
 		this.name = name;
 		this.r_name = r_name;
		this.category = category;
		this.price = price;
		this.image = image;
		this.calorie = calorie;
 		}
	public LouisaMenu(int id, String name, String r_name, Double price, int calorie,String category, String image) {
		super();
		this.id = id;
 		this.name = name;
 		this.r_name = r_name;
		this.category = category;
		this.price = price;
		this.image = image;
		this.calorie = calorie;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	@Override
	public String toString() {
		return "LouisaMenu [id=" + id + ", name=" + name + ", r_name=" + r_name + ", category=" + category + ", price="
				+ price + ", image=" + image + ", calorie=" + calorie + ", rest_id=" + r_id + "]";
	}

 

	
	
	
}
