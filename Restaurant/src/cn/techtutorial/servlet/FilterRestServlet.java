package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;

import cn.techtutorial.connection.DbCon;
import cn.techtutorial.dao.ProductDao;
import cn.techtutorial.model.*;

@WebServlet(name = "FilterRestServlet", urlPatterns = "/filter-rest")
public class FilterRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		
		ProductDao pd = null;
		try {
			pd = new ProductDao(DbCon.getConnection());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Product> products = pd.getAllProducts();
		
		String priceValue = request.getParameter("value").toString();
		List<Product> newProducts = new ArrayList<>();
		
		if (priceValue.equals("All prices")) {
			for (Product p : products) {
				newProducts.add(p);
			}
		}else if (priceValue.equals("Below $100")) {
			for (Product p : products) {
				if (p.getPrice() < 100) {
					newProducts.add(p);
				}
			}
		}else if (priceValue.equals("$100-$200")) {
			for (Product p : products) {
				if (100 <= p.getPrice() && p.getPrice() < 200) {
					newProducts.add(p);
				}
			}
		}else if (priceValue.equals("$200-$300")) {
			for (Product p : products) {
				if (200 <= p.getPrice() && p.getPrice() < 300) {
					newProducts.add(p);
				}
			}
		}else {
			for (Product p : products) {
				if (300 <= p.getPrice() && p.getPrice() < 400) {
					newProducts.add(p);
				}
			}
		}
		
		session.setAttribute("priceValue", priceValue);
		session.setAttribute("newProducts", newProducts);
		response.sendRedirect("index.jsp");
	}

}