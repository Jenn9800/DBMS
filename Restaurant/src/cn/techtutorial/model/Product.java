package cn.techtutorial.model;

public class Product {

	private int id;
	private String name;
	private String location;
	private Double price;
	private String image;
	
	public Product(){
		
	}

	public Product(int id, String name, String location, Double price, String image) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.price = price;
		this.image = image;
	}

	public Product( String name, String location, Double price, String image) {
		super();
 		this.name = name;
		this.location = location;
		this.price = price;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String category) {
		this.location = category;
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", location=" + location + ", price=" + price + ", image="
				+ image + "]";
	}
	
	
	
}
