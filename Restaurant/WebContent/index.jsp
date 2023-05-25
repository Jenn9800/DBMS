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

String priceValue = (String) request.getSession().getAttribute("priceValue");
@SuppressWarnings("unchecked")
ArrayList<Product> newProducts = (ArrayList<Product>) request.getSession().getAttribute("newProducts");

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
		<div align="left">
		The range of prices: 
			<%
				if (priceValue == null || priceValue.equals("All prices")){
			%>
					<select id="mySelect"  onchange="getValue()">
		    		<option value="All prices" selected>All prices</option>
		    		<option value="Below $100">Below $100</option>
		    		<option value="$100-$200">$100-$200</option>
		    		<option value="$200-$300">$200-$300</option>
		    		<option value="$300-$400">$300-$400</option>
					</select>
			<%	
				}else if (priceValue.equals("Below $100")){
			%>
					<select id="mySelect"  onchange="getValue()">
		    		<option value="All prices">All prices</option>
		    		<option value="Below $100" selected>Below $100</option>
		    		<option value="$100-$200">$100-$200</option>
		    		<option value="$200-$300">$200-$300</option>
		    		<option value="$300-$400">$300-$400</option>
					</select>
			<%
				}else if (priceValue.equals("$100-$200")){
			%>
					<select id="mySelect"  onchange="getValue()">
		    		<option value="All prices">All prices</option>
		    		<option value="Below $100">Below $100</option>
		    		<option value="$100-$200" selected>$100-$200</option>
		    		<option value="$200-$300">$200-$300</option>
		    		<option value="$300-$400">$300-$400</option>
					</select>
			<%
				}else if (priceValue.equals("$200-$300")){
			%>
					<select id="mySelect"  onchange="getValue()">
		    		<option value="All prices">All prices</option>
		    		<option value="Below $100">Below $100</option>
		    		<option value="$100-$200">$100-$200</option>
		    		<option value="$200-$300" selected>$200-$300</option>
		    		<option value="$300-$400">$300-$400</option>
					</select>
			<%
				}else if (priceValue.equals("$300-$400")){
			%>
					<select id="mySelect"  onchange="getValue()">
		    		<option value="All prices">All prices</option>
		    		<option value="Below $100">Below $100</option>
		    		<option value="$100-$200">$100-$200</option>
		    		<option value="$200-$300" >$200-$300</option>
		    		<option value="$300-$400" selected>$300-$400</option>
					</select>
			<%
				}
		
				request.getSession().removeAttribute("priceValue");
			%>
		</div>
		<script>
		function getValue() {
			var selectElement = document.getElementById("mySelect");
			var selectedValue = selectElement.value;
			
			var url = "filter-rest?value=" + encodeURIComponent(selectedValue);
			window.location.href = url;
			}
		</script>
		
		<div class="row">
			<%
			if (newProducts == null){

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
		}else{
			if (!newProducts.isEmpty()) {
				for (Product p : newProducts) {
			%>
							<div class="col-md-3 my-3">
								<div class="card w-100">
									<img class="card-img-top" src="product-images/<%=p.getImage() %>"
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
				}
			
				request.getSession().removeAttribute("newProducts");
			%>
		</div>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>