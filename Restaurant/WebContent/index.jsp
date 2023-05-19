<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.ProductDao"%>
<%@page import="cn.techtutorial.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("person", auth);
}
ProductDao pd = new ProductDao(DbCon.getConnection());
List<Product> products = pd.getAllProducts();

%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<style type="text/css">
.card-img-top{
    width: 100%;
    height: 15vw;
    object-fit: cover;
}
</style>
<title>Fitness & Budget Tracking</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">List of Restaurants</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<p><% System.out.println(p.getImage());%></p>
				<div class="card w-100">
					<%-- <img class="card-img-top" src="product-images/<%=p.getImage()%>"
						alt="Card image cap"> --%>
					<img class="card-img-top" src="product-images/<%=p.getImage()%>"
						alt="Card image cap">
						
					<div class="card-body">
						<h5 class="card-title"><%=p.getName() %></h5>
						<h6 class="price">Price Range: $<%=p.getPrice() %></h6>
						<h6 class="location">Location: <%=p.getLocation() %></h6>
						<div class="col-md-12 text-center">
 							<a href="louisa_menu.jsp?restaurantName=<%= p.getName()%>" class="btn btn-primary">Select</a>
							
						</div>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			out.println("There is no proucts");
			}
			%>

		</div>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>