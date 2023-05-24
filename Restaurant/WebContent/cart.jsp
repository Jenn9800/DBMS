<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.LouisaMenuDao"%>
<%@page import="cn.techtutorial.dao.UserDao"%>

<%@page import="cn.techtutorial.model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
UserDao uDao = new UserDao(DbCon.getConnection());

session = request.getSession();
String email = (String) session.getAttribute("loginemail");
System.out.println("auth.getEmail(): " + email);
int cal_limit = 1000;

User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("person", auth);
	System.out.println("auth is not null");
 } else {
    System.out.println("auth is null");
}
 

 

 
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if (cart_list != null) {
	LouisaMenuDao lDao = new LouisaMenuDao(DbCon.getConnection());
	cartProduct = lDao.getCartProducts(cart_list);
	int c_total = lDao.getTotalCartCalorie(cart_list);
	int p_total = lDao.getTotalCartPrice(cart_list);
	
	int remainingCalories = cal_limit - c_total;

	request.setAttribute("cart-list", cart_list);
	request.setAttribute("c_total", c_total);
	request.setAttribute("p_total", p_total);
	request.setAttribute("remainingCalories", remainingCalories); // Add this line

}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>Today's Record Cart</title>
<style type="text/css">
.table tbody td {
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
		<div class="d-flex py-3">
			<h3>
				Today's Calorie Consumption: ${(c_total>0)?c_total:0} Cal <br>
				Today's Spent: $${(p_total>0)?p_total:0}<br>
				Remaining Calories: ${(remainingCalories>0)?remainingCalories:0} Cal <!-- Add this line -->
			
			</h3>


		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Price</th>
					<th scope="col">Calorie</th>
					<th scope="col">Quantity</th>
					<th scope="col">Category</th>
					<th scope="col">Remove</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cart_list != null) {
					for (Cart c : cartProduct) {
				%>
				<tr>
					<td><%=new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())%></td>
					<td><%=c.getName()%></td>
					<td>$<%=c.getPrice()%></td>
					<td><%=c.getCalorie()%></td>
					<td>
						<form action="" method="post" class="form-inline">
						<input type="hidden" name="id" value="<%= c.getId()%>" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn bnt-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%=c.getId()%>"><i class="fas fa-minus-square"></i></a> 
								<input type="text" name="quantity" class="form-control"  value="<%=c.getQuantity()%>" readonly> 
								<a class="btn btn-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"><i class="fas fa-plus-square"></i></a>
							</div>
 						</form>
					</td>					
					<td><%=c.getCategory()%></td>

					<td>
						<form action="order-now" method="post" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId()%>"
								class="form-input">
						</form> <a href="remove-from-cart?id=<%=c.getId()%>" <%-- get /remove-from-cart from servlet--%>
						class="btn btn-sm btn-danger" onclick="alert('Removed Successfully!')">Remove</a>
					</td>
				</tr>

				<%
				}
				}
				%>
			</tbody>
		</table>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>