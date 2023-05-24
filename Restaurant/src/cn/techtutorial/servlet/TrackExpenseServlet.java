package cn.techtutorial.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.techtutorial.connection.DbCon;

 
@WebServlet("/TrackExpenseServlet")
public class TrackExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public TrackExpenseServlet() {
		super();
 	}
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		Connection con;
		String query;
		PreparedStatement pst;
		ResultSet rs;
		
		// update old data or insert new data into 'er'
		int userID = (int) request.getSession().getAttribute("userID");
		ArrayList<String> dates = new ArrayList<>();
		String date = "";
		int budget = 0;
		int moneySpent = 0;
		int remainingMoney = 0;
		
		try {
			con = (Connection) DbCon.getConnection();
			
			query = "select distinct cDate as date from record where userID=?";
			pst = con.prepareStatement(query);
			pst.setInt(1, userID);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				dates.add(rs.getString("date"));
			}
			int countDates = dates.size();
			
			if(countDates != 0) {
				for (int i=0; i<countDates; i++) {
					// set date
					date = dates.get(i);
					
					// set budget
					query = "select budget from users where id=?";
					pst = con.prepareStatement(query);
					pst.setInt(1, userID);
					rs = pst.executeQuery();
					
					if (rs.next()) {
						budget = rs.getInt("budget");
					}
					
					// set moneySpent
					query = "select sum(price) as totalPrice from record where userID=? and cDate=?";
					pst = con.prepareStatement(query);
					pst.setInt(1, userID);
					pst.setString(2, date);
					rs = pst.executeQuery();
					
					if (rs.next()) {
						moneySpent = rs.getInt("totalPrice");
					}
					
					// set remainingMoney
					if (i==0) {
						remainingMoney = budget - moneySpent;
					}else {
						query = "select remainingMoney from er where userID=? and cDate=?";
						pst = con.prepareStatement(query);
						pst.setInt(1, userID);
						pst.setString(2, dates.get(i-1));
						rs = pst.executeQuery();
					
						if (rs.next()) {
							remainingMoney = rs.getInt("remainingMoney") - moneySpent;
						}
					}
					
					// check whether there is a row with the same user and date
					query = "select * from er where userID=? and cDate=?";
					pst = con.prepareStatement(query);
					pst.setInt(1, userID);
					pst.setString(2, date);
					rs = pst.executeQuery();
					
					if (rs.next()) {
						// update the old data
						// update the budget of users (it might have been changed by users)
						query = "update er set budget=? where userID=? and cDate=?;";
						pst = con.prepareStatement(query);
						pst.setInt(1, budget);
						pst.setInt(2, userID);
						pst.setString(3, date);
						pst.executeUpdate();
	
						// update moneySpent
						query = "update er set moneySpent=? where userID=? and cDate=?;";
						pst = con.prepareStatement(query);
						pst.setInt(1, moneySpent);
						pst.setInt(2, userID);
						pst.setString(3, date);
						pst.executeUpdate();
	
						// update remainingMoney
						query = "update er set remainingMoney=? where userID=? and cDate=?;";
						pst = con.prepareStatement(query);
						pst.setInt(1, remainingMoney);
						pst.setInt(2, userID);
						pst.setString(3, date);
						pst.executeUpdate();
					}else {
						// insert new data into table 'er'
						query = "insert into er (userID, cDate, budget, moneySpent, remainingMoney) values (?, ?, ?, ?, ?);";
						pst = con.prepareStatement(query);
						pst.setInt(1, userID);
						pst.setString(2, date);
						pst.setInt(3, budget);
						pst.setInt(4, moneySpent);
						pst.setInt(5, remainingMoney);
						pst.executeUpdate();
					}
				}
			}else {
				query = "TRUNCATE TABLE er";
				pst = con.prepareStatement(query);
				pst.executeUpdate();
			}
			
			response.sendRedirect("btrack.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
