<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.LouisaMenuDao"%>
<%@page import="cn.techtutorial.dao.UserDao"%>

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.PrintWriter"%>
<%@ page import="java.sql.*,java.io.*"%>

 <%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
UserDao uDao = new UserDao(DbCon.getConnection());
session = request.getSession();
String email = (String) session.getAttribute("loginemail");

 
// Retrieve cal_limit from the database using UserDao
System.out.println("auth.getEmail(): " + email);


ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if (cart_list != null) {
	LouisaMenuDao lDao = new LouisaMenuDao(DbCon.getConnection());
	cartProduct = lDao.getCartProducts(cart_list);
	int p_total = lDao.getTotalCartPrice(cart_list);
 
	request.setAttribute("cart-list", cart_list);
	request.setAttribute("p_total", p_total);
 
	

}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>Budget Tracking Page</title>
<style type="text/css">
.table {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
}
</style>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
	<div class="container my-3">
 		<table class="table">
			<thead>
				<tr>
				<%
				try{ 
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_cart", "root", "jenn980047");
					PreparedStatement pst = con.prepareStatement("select budget from users where email=?");
					pst.setString(1,email);
					ResultSet rs = pst.executeQuery();
					if(rs.next()){
				      int budget = rs.getInt("budget");

				%>
					<th scope="col">Date</th>
					<th scope="col">Monthly Budget</th>
					<th scope="col">Total Money Spent</th>
					<th scope="col">Remaining Money</th>
				</tr>
			</thead>
			<tbody>
					 
					<tr>

						<td><%=new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())%></td>
					<td><%=rs.getInt("budget")%></td>
					<td>$${(p_total>0)?p_total:0}</td>
					<td>$${(p_total > 0) ? Math.max(0, budget - p_total) : budget}</td>
					
					</tr>

				<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>
			</tbody>
		</table>
	</div>
	<center><h5>There are no more expense-records.</h5></center>
	
	<%@include file="/includes/footer.jsp"%>
</body>
</html>