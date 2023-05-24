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

/**
 * Servlet implementation class TrackExpenseServlet
 */
@WebServlet("/TrackCalServlet")
public class TrackCalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrackCalServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		Connection con;
		String query;
		PreparedStatement pst;
		ResultSet rs;
		
		// update old data or insert new data into 'er'
		int userID = (int) request.getSession().getAttribute("userID");
		ArrayList<String> dates = new ArrayList<>();
		String date = "";
		int calLimit = 0;
		int calIngested = 0;
		int remainingCal = 0;
		
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
					
					// set calLimit
					query = "select cal_limit from users where id=?";
					pst = con.prepareStatement(query);
					pst.setInt(1, userID);
					rs = pst.executeQuery();
					
					if (rs.next()) {
						calLimit = rs.getInt("cal_limit");
					}
					
					// set calIngested
					query = "select sum(calories) as totalCal from record where userID=? and cDate=?";
					pst = con.prepareStatement(query);
					pst.setInt(1, userID);
					pst.setString(2, date);
					rs = pst.executeQuery();
					
					if (rs.next()) {
						calIngested = rs.getInt("totalCal");
					}
					
					// set remainingCal
					if (i==0) {
						remainingCal = calLimit - calIngested;
					}else {
						query = "select remainingCal from dr where userID=? and cDate=?";
						pst = con.prepareStatement(query);
						pst.setInt(1, userID);
						pst.setString(2, dates.get(i-1));
						rs = pst.executeQuery();
					
						if (rs.next()) {
							remainingCal = rs.getInt("remainingCal") - calIngested;
						}
					}
					
					// check whether there is a row with the same user and date
					query = "select * from dr where userID=? and cDate=?";
					pst = con.prepareStatement(query);
					pst.setInt(1, userID);
					pst.setString(2, date);
					rs = pst.executeQuery();
					
					if (rs.next()) {
						// update the old data
						// update the calLimit of users (it might have been changed by users)
						query = "update dr set calLimit=? where userID=? and cDate=?;";
						pst = con.prepareStatement(query);
						pst.setInt(1, calLimit);
						pst.setInt(2, userID);
						pst.setString(3, date);
						pst.executeUpdate();
		
						// update calIngested
						query = "update dr set calIngested=? where userID=? and cDate=?;";
						pst = con.prepareStatement(query);
						pst.setInt(1, calIngested);
						pst.setInt(2, userID);
						pst.setString(3, date);
						pst.executeUpdate();
		
						// update remainingCal
						query = "update dr set remainingCal=? where userID=? and cDate=?;";
						pst = con.prepareStatement(query);
						pst.setInt(1, remainingCal);
						pst.setInt(2, userID);
						pst.setString(3, date);
						pst.executeUpdate();
				
						}else {
							// insert new data into table 'dr'
							query = "insert into dr (userID, cDate, calLimit, calIngested, remainingCal) values (?, ?, ?, ?, ?);";
							pst = con.prepareStatement(query);
							pst.setInt(1, userID);
							pst.setString(2, date);
							pst.setInt(3, calLimit);
							pst.setInt(4, calIngested);
							pst.setInt(5, remainingCal);
							pst.executeUpdate();
						}
				}
			}else {
				query = "TRUNCATE TABLE dr";
				pst = con.prepareStatement(query);
				pst.executeUpdate();
			}
			
			response.sendRedirect("ctrack.jsp");
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
