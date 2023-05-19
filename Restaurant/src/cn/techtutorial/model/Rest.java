package cn.techtutorial.model;

public class Rest {
	
	protected int id;
	protected String name;
	protected String location;
	protected double price;
	protected String image;
	
	public Rest() {
	
	}
	
	public Rest(String name, String location, double price, String image) {
		super();
 		this.name = name;
		this.location = location;
		this.price = price;
		this.image = image;
	}
	
	public Rest(int id, String name, String location, double price, String image) {
		super();
		this.id = id;
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

	public void setLocation(String location) {
		this.location = location;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	

}
