package cn.techtutorial.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.techtutorial.dao.UserDao;
import cn.techtutorial.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user-servlet")
public class UserServlet extends HttpServlet {
 
	private static final long serialVersionUID = 1L;
    private UserDao ud;
    
    public UserServlet() {
       this.ud = new UserDao();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	this.doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		String email = request.getParameter("s-email");
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
				session.setAttribute("name", rs.getString("s-email"));
				System.out.println(email);
				dispatcher = request.getRequestDispatcher("index.jsp");
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
