package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.techtutorial.model.Cart;

/**
 * Servlet implementation class RemoveFromLM
 */
@WebServlet("/RemoveFromLM")
public class RemoveFromLM extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try(PrintWriter out = response.getWriter()){
			String id = request.getParameter("id");
			out.println("product id " +id);
			
			if(id!= null) {
				ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
				if(cart_list != null) {
					for(Cart c:cart_list) {
						if(c.getId()== Integer.parseInt(id)) {
							cart_list.remove(cart_list.indexOf(c));
							break;
						}
					}
					response.sendRedirect("louisa_menu.jsp");
				}
			}else {
				response.sendRedirect("louisa_menu.jsp");
			}
		}
	}

}
