package cn.techtutorial.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import cn.techtutorial.connection.DbCon;
import cn.techtutorial.model.*;

public class UserDao {
	private Connection con;
	private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public UserDao() {
    	
    }
	public UserDao(Connection con) {
		this.con = con;
	}
	
	public int getCalorieLimit(String email) {
	    int cal_limit = 0;
	    try {
	        query = "SELECT cal_limit FROM users WHERE email = ?";
	        pst = this.con.prepareStatement(query);
	        pst.setString(1, email);
	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            cal_limit = rs.getInt("cal_limit");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cal_limit;
	}

	
	public User userLogin(String email) {
		User user = null;
        try {
            query = "select * from users";
            pst = this.con.prepareStatement(query);
              rs = pst.executeQuery();
            if(rs.next()){
            	user = new User();
            	user.setId(rs.getInt("id"));
            	user.setName(rs.getString("name"));
            	user.setEmail(rs.getString("email"));
            	user.setPassword(rs.getString("password"));
            	user.setHeight(rs.getInt("height"));
            	user.setWeight(rs.getInt("weight"));
            	user.setBudget(rs.getInt("budget"));
            	user.setCal_limit(rs.getInt("cal_limit"));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return user;
    }
	
	public User getUserByEmail(String email) {
		User user = null;
		try {
            query = "select * from users where email=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, email);
 			System.out.println(pst);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()){
				
				user = new User();
		            user.setId(rs.getInt("id"));
		            user.setName(rs.getString("name"));
		            user.setEmail(rs.getString("email"));
		            user.setPassword(rs.getString("password"));
		            user.setGender(rs.getString("gender"));
		            user.setHeight(rs.getInt("height"));
		            user.setWeight(rs.getInt("weight"));
		            user.setBudget(rs.getInt("budget"));
		            user.setCal_limit(rs.getInt("cal_limit"));

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
 
	public User selectUser(int id) {
		User user = null;
		try {
            query = "select name, email, password, gender, height, weight, budget, cal_limit from users where id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			System.out.println(pst);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()){
 				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String gender = rs.getString("gender");

				int height = rs.getInt("height");
				int weight = rs.getInt("weight");
				int budget = rs.getInt("budget");
				int cal_limit = rs.getInt("cal_limit");
				user = new User(name, email, password,gender, height, weight, budget, cal_limit);

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public List<User> selectAllUsers(){
		List<User> users = new ArrayList<>();
		
		try {
            query = "select id, name, email, password, gender, height, weight, budget, cal_limit from users where id=?";
			pst = this.con.prepareStatement(query);
 			System.out.println(pst);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String gender = rs.getString("gender");
				int height = rs.getInt("height");
				int weight = rs.getInt("weight");
				int budget = rs.getInt("budget");
				int cal_limit = rs.getInt("cal_limit");
				users.add(new User(name, email, password, gender, height, weight, budget, cal_limit));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated = false;

		try {
			query ="update users set name=?, email=?, password=?, gender=?, height=?, weight=?, budget=?, cal_limit=? where email=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getPassword());
			pst.setString(4, user.getGender());
			pst.setInt(5, user.getHeight());
			pst.setInt(6, user.getWeight());
			pst.setInt(7, user.getBudget());
			pst.setInt(8, user.getCal_limit());
			pst.setString(9, user.getEmail());

 			rowUpdated = pst.executeUpdate() > 0;

 		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return rowUpdated;
	}
	
}
