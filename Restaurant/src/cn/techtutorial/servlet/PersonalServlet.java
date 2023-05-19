package cn.techtutorial.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.techtutorial.connection.DbCon;
import cn.techtutorial.dao.UserDao;
import cn.techtutorial.model.User;

/**
 * Servlet implementation class PersonalServlet
 */
@WebServlet("/PersonalServlet")
public class PersonalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao uDao;
    
   public PersonalServlet() throws ClassNotFoundException, SQLException {
	   uDao = new UserDao(DbCon.getConnection());
   }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. retrieve parametere from jsp page 
 		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		int height = Integer.parseInt(request.getParameter("height"));
		int weight = Integer.parseInt(request.getParameter("weight"));
		int budget = Integer.parseInt(request.getParameter("budget"));
		int cal_limit = Integer.parseInt(request.getParameter("cal_limit"));

		
		//2. set all values in user class object 
		HttpSession session = request.getSession();
		User user = new User(name, email, password, gender, height, weight, budget, cal_limit);

		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setGender(gender);
		user.setHeight(height);
		user.setWeight(weight);
		user.setBudget(budget);
		user.setCal_limit(cal_limit);
 
		//3. call method in UserDao to update data in table

 		
 		try {
			boolean update=uDao.updateUser(user);
			if(update!= false) {
				session.setAttribute("name", name);
				session.setAttribute("email", email);
				session.setAttribute("password", email);
				session.setAttribute("gender", gender);
				session.setAttribute("weight", weight);
				session.setAttribute("height", height);
				session.setAttribute("budget", budget);
				session.setAttribute("cal_limit", cal_limit);

				System.out.println("updated sucessful!");
				request.setAttribute("msg", "Update Sucessful!");
				getServletContext().getRequestDispatcher("/p_info.jsp").forward(request, response);
			}else {
				System.out.println("fail to update :(");

				request.setAttribute("msg", "Update NOT Sucessful!");
				getServletContext().getRequestDispatcher("/user-form.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

}
