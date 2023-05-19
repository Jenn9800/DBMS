package cn.techtutorial.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.techtutorial.connection.DbCon;
import cn.techtutorial.dao.LouisaMenuDao;
import cn.techtutorial.dao.MealDao;
import cn.techtutorial.dao.ProductDao;
import cn.techtutorial.model.LouisaMenu;
import cn.techtutorial.model.Meal;
import cn.techtutorial.model.Product;
 
/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductDao pDao;
    private LouisaMenuDao lmDao;


    public ProductServlet() throws ClassNotFoundException, SQLException {
    	pDao = new ProductDao(DbCon.getConnection());
    	lmDao = new LouisaMenuDao(DbCon.getConnection());

    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertRest(request, response);
				break;
			case "/delete":
				deleteRest(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateRest(request, response);
				break;
			case "/new-meal":
				showMealNewForm(request, response);
				break;
			case "/insert-meal":
				insertMeal(request, response);
				break;
			case "/delete-meal":
				deleteMeal(request, response);
				break;
			case "/edit-meal":
				showMealEditForm(request, response);
				break;
			case "/update-meal":
				updateMeal(request, response);
				break;
			case "/list-meal":
				listMeal(request, response);
				break;
			default:
				listRest(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	
	private void listRest(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Product> listProduct = pDao.getAllProducts();
		request.setAttribute("listProduct", listProduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("rest-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("rest-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Product existingProduct = pDao.selectProduct(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("rest-form.jsp");
		request.setAttribute("product", existingProduct);
		dispatcher.forward(request, response);

	}

	private void insertRest(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String location = request.getParameter("location");
		Double price = Double.parseDouble(request.getParameter("price"));
		String image = request.getParameter("image");
		Product newProduct= new Product(name, location, price, image);
		pDao.insertProduct(newProduct);
		response.sendRedirect("list");
	}

	private void updateRest(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String location = request.getParameter("location");
		Double price = Double.parseDouble(request.getParameter("price"));
		String image = request.getParameter("image");
		Product book = new Product(id, name, location, price, image);
		pDao.updateProduct(book);
		response.sendRedirect("list");
	}

	private void deleteRest(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		pDao.deleteProduct(id);
		response.sendRedirect("list");

	}
	
	//add meals 
			
			private void listMeal(HttpServletRequest request, HttpServletResponse response)
					throws SQLException, IOException, ServletException {
				List<LouisaMenu> listMeal = lmDao.getAllProducts();
				request.setAttribute("listMeal", listMeal);
				RequestDispatcher dispatcher = request.getRequestDispatcher("meal-list.jsp");
				dispatcher.forward(request, response);
			}
			
			private void showMealNewForm(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
				RequestDispatcher dispatcher = request.getRequestDispatcher("meal-form.jsp");
				dispatcher.forward(request, response);
			}
			
			private void showMealEditForm(HttpServletRequest request, HttpServletResponse response)
					throws SQLException, ServletException, IOException {
				int id = Integer.parseInt(request.getParameter("id"));
				LouisaMenu existingMeal = lmDao.selectMeal(id);
				RequestDispatcher dispatcher = request.getRequestDispatcher("meal-form.jsp");
				request.setAttribute("meal", existingMeal);
				dispatcher.forward(request, response);

			}
			private void insertMeal(HttpServletRequest request, HttpServletResponse response) 
					throws SQLException, IOException {
				String name = request.getParameter("name");
				String r_name = request.getParameter("r_name");
				Double price = Double.parseDouble(request.getParameter("price"));
				Integer calorie = Integer.parseInt(request.getParameter("calorie"));
				String category = request.getParameter("category");
				String image = request.getParameter("image");
				LouisaMenu newMeal= new LouisaMenu(name, r_name, price, calorie, category, image);
				lmDao.insertMeal(newMeal);
				response.sendRedirect("list-meal");
			}
			
			private void updateMeal(HttpServletRequest request, HttpServletResponse response) 
					throws SQLException, IOException {
				int id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				String r_name = request.getParameter("r_name");

				Double price = Double.parseDouble(request.getParameter("price"));
				Integer calorie = Integer.parseInt(request.getParameter("calorie"));
				String category = request.getParameter("category");
				String image = request.getParameter("image");
				LouisaMenu book = new LouisaMenu(id, name, r_name, price, calorie, category, image);
				lmDao.updateMeal(book);
				response.sendRedirect("list-meal");
			}
			
			private void deleteMeal(HttpServletRequest request, HttpServletResponse response) 
					throws SQLException, IOException {
				int id = Integer.parseInt(request.getParameter("id"));
				lmDao.deleteMeal(id);
 				response.sendRedirect("list-meal");

			}

}