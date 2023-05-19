

package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.techtutorial.model.Cart;
import cn.techtutorial.model.LouisaMenu;
import cn.techtutorial.model.Product;

public class LouisaMenuDao {
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    

	public LouisaMenuDao(Connection con) {
		super();
		this.con = con;
	}
	
	public List<LouisaMenu> getSpecificProducts (String restaurantName) {
        List<LouisaMenu> book = new ArrayList<>();
        try {
            query = "select lm.id, lm.name, lm.price, lm.calorie, lm.category, lm.image from products p JOIN louisa_menu lm on p.name=lm.r_name where p.name=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, restaurantName);  // Set the value for the parameter
             rs = pst.executeQuery();

            while (rs.next()) {
            	LouisaMenu row = new LouisaMenu();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                 row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setCalorie(rs.getInt("calorie"));
                row.setImage(rs.getString("image"));
 
                book.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }

	
	public List<LouisaMenu> getAllProducts () {
        List<LouisaMenu> book = new ArrayList<>();
        try {
            query = "select * from louisa_menu";
            pst = this.con.prepareStatement(query);
              rs = pst.executeQuery();

            while (rs.next()) {
            	LouisaMenu row = new LouisaMenu();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setR_name(rs.getString("r_name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setCalorie(rs.getInt("calorie"));
                row.setImage(rs.getString("image"));

                book.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartlist){
		List<Cart> products = new ArrayList<Cart>();
		try {
			if(cartlist.size()>0) {
				for(Cart item: cartlist) {
					query = "select * from louisa_menu where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while(rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setR_name(rs.getString("r_name"));
						row.setPrice(rs.getDouble("price")*item.getQuantity());
						row.setCalorie(rs.getInt("calorie")*item.getQuantity());
						row.setCategory(rs.getString("category"));
						row.setImage(rs.getString("image"));

						row.setQuantity(item.getQuantity());
						products.add(row);
					}
				}
			}
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
		
		return products;
	}
	
	public int getTotalCartCalorie(ArrayList<Cart> cartList) {
		int sum=0;
		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					query= "select calorie from louisa_menu where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs= pst.executeQuery();
					
					while(rs.next()) {
						sum+= rs.getInt("calorie")*item.getQuantity(); //need to add quantity
					}
				}
			} 
		}catch(Exception e) {
			
		}
		
		return sum;
	}
	
	public int getTotalCartPrice(ArrayList<Cart> cartList) {
		int sum=0;
		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					query= "select price from louisa_menu where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs= pst.executeQuery();
					
					while(rs.next()) {
						sum+= rs.getInt("price")*item.getQuantity();  //need to add quantity
					}
				}
			} 
		}catch(Exception e) {
			
		}
		
		return sum;
	}
	
	//add meals
	 	public void insertMeal(LouisaMenu lm) throws SQLException{	
	 		
	 		try {
	 			query ="INSERT INTO louisa_menu" + "  (name, r_name, price, calorie, category, image) VALUES "
	 					+ " (?, ?, ?, ?, ?, ?);";
	 		   pst = this.con.prepareStatement(query);
	 		   pst.setString(1, lm.getName());
	 		   pst.setString(2, lm.getR_name());
 				pst.setDouble(3, lm.getPrice());
 				pst.setInt(4, lm.getCalorie());
 				pst.setString(5, lm.getCategory());
				pst.setString(6, lm.getImage());
				System.out.println(pst);
				pst.executeUpdate();
			
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
	 	//delete meal
		public boolean deleteMeal(int id) throws SQLException {
			boolean rowDeleted = false;	
			try {

					query = "delete from louisa_menu where id=?";
			 		 pst = this.con.prepareStatement(query);
			 		pst.setInt(1, id);
					rowDeleted = pst.executeUpdate() >0;
			
				}catch(Exception e) {
					e.printStackTrace();
				}
				return rowDeleted;
			}
		//select meal
		public LouisaMenu selectMeal(int id) {
			LouisaMenu lm = null; 

			try {
				query ="select id,name,r_name,price,calorie, category, image from louisa_menu where id =?";
				pst = this.con.prepareStatement(query);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				while (rs.next()) {
					String name = rs.getString("name");
					String r_name = rs.getString("r_name");
					Double price = Double.parseDouble(rs.getString("price"));
					int calorie = Integer.parseInt(rs.getString("calorie"));
					String category = rs.getString("category");
					String image = rs.getString("image");
					lm = new LouisaMenu(id, name, r_name, price, calorie, category, image);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return lm;
		}
		//update meal
		public boolean updateMeal(LouisaMenu lm) throws SQLException{
			boolean rowUpdated = false;
			try {
				query ="update louisa_menu set name = ?,r_name= ?, price =?, calorie=?, category=?, image=? where id = ?;";
				pst = this.con.prepareStatement(query);
				pst.setString(1, lm.getName());
				pst.setString(2, lm.getR_name());
				pst.setDouble(3, lm.getPrice());
				pst.setInt(4,  lm.getCalorie());
				pst.setString(5, lm.getCategory());
				pst.setString(6, lm.getImage());
				pst.setInt(7, lm.getId());
				rowUpdated = pst.executeUpdate() > 0;

			}catch(SQLException e) {
				e.printStackTrace();
			}
			return rowUpdated;
		}
}
