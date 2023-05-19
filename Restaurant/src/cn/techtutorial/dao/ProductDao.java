package cn.techtutorial.dao;

import java.sql.*;
import java.util.*;

import cn.techtutorial.model.Cart;
import cn.techtutorial.model.Product;
import cn.techtutorial.model.Rest;

public class ProductDao {

	private Connection con;
 	private String query;
     private ResultSet rs;
    private PreparedStatement pst;

        
      public ProductDao(Connection con) {
		super();
		this.con = con;
	}
    
	//get all restaurants
	public List<Product> getAllProducts() {
        List<Product> book = new ArrayList<>(); //create arraylist to store rests
        try {
            query = "select * from products";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	Product row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setLocation(rs.getString("location"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));
                book.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }
	
	//add rests
 	public void insertProduct(Product product) throws SQLException{	
 		
 		try {
 			query ="INSERT INTO products" + "  (name, location, price, image) VALUES "
 					+ " (?, ?, ?, ?);";
 		   pst = this.con.prepareStatement(query);
 		   pst.setString(1, product.getName());
			pst.setString(2, product.getLocation());
			pst.setDouble(3, product.getPrice());
			pst.setString(4, product.getImage());
			System.out.println(pst);
			pst.executeUpdate();
		
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
 	
 	//delete restaurant
	public boolean deleteProduct(int id) throws SQLException {
		boolean rowDeleted = false;	
		try {

				query = "delete from products where id=?";
		 		 pst = this.con.prepareStatement(query);
		 		pst.setInt(1, id);
				rowDeleted = pst.executeUpdate() >0;
		
			}catch(Exception e) {
				e.printStackTrace();
			}
			return rowDeleted;
		}

	public Product selectProduct(int id) {
		Product product = null; 

		try {
			query ="select id,name,location,price,image from products where id =?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String location = rs.getString("location");
				Double price = Double.parseDouble(rs.getString("price"));
				String image = rs.getString("image");
				product = new Product(id, name, location, price, image);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
	
	public boolean updateProduct(Product product) throws SQLException{
		boolean rowUpdated = false;
		try {
			query ="update products set name = ?,location= ?, price =?, image=? where id = ?;";
			pst = this.con.prepareStatement(query);
			pst.setString(1, product.getName());
			pst.setString(2, product.getLocation());
			pst.setDouble(3, product.getPrice());
			pst.setString(4, product.getImage());
			pst.setInt(5, product.getId());
			rowUpdated = pst.executeUpdate() > 0;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}

		
}