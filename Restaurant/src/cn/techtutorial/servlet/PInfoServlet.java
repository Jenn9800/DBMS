package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

 
/**
 * Servlet implementation class PInfo
 */
@WebServlet("/p-info")
public class PInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		Connection con = null;
		HttpSession session = request.getSession();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String login_email = request.getParameter("login-email");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_cart?useSSL=false", "root", "jenn980047");
			PreparedStatement pst = con.prepareStatement("select * from users where email=?");
			
			pst.setString(1, login_email);
			
			out.print("<table width=75% border=1>");
			out.print("<caption> Result: </caption>");
			ResultSet rs = pst.executeQuery();
			
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int totalColumn = rsmd.getColumnCount();
			out.print("<tr>");
			for (int i=1; i<=totalColumn; i++) {
				out.print("<th>"+rsmd.getColumnName(i)+"</th>");
			}
			
			out.print("<tr>");
			
			while(rs.next()) {
				out.print("<tr><td>"+rs.getInt(1)
						+"</td><td>" +rs.getString(2)
						+"</td><td>" + rs.getString(3)
						+"</td><td>" + rs.getInt(4)
						+"</td><td>" + rs.getString(5)
						+"</td><td>" + rs.getInt(6)
						+"</td><td>" + rs.getInt(7)
						+"</td><td>" + rs.getInt(8)
						+"</td><td>" + rs.getInt(9)
						+"</td><tr>");
			}
			out.print("</table>");

		}catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("p_info.jsp");

}
}
