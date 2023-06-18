package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

 
/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
 		RequestDispatcher dispatcher = null;
		Connection con = null;
		HttpSession session = request.getSession();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_cart?useSSL=false", "root", "jenn980047");
			PreparedStatement pst = con.prepareStatement("select * from users where email=?");
			pst.setString(1, email);
 			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
			

				session.setAttribute("loginemail", rs.getString("email"));
				dispatcher = request.getRequestDispatcher("index.jsp");
				session.setAttribute("id", rs.getInt(1));
				session.setAttribute("name", rs.getString(2));
				session.setAttribute("email", rs.getString(3));
				session.setAttribute("password", rs.getString(4));
				session.setAttribute("gender", rs.getString(5));
				session.setAttribute("height", rs.getInt(6));
				session.setAttribute("weight", rs.getInt(7));
				session.setAttribute("budget", rs.getInt(8));
				session.setAttribute("cal_limit", rs.getInt(9));
			}else {
				dispatcher = request.getRequestDispatcher("login.jsp");
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	
		
	}

}