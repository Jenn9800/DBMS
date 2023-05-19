<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.LouisaMenuDao"%>
<%@page import="cn.techtutorial.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("person", auth);
}
LouisaMenuDao pd = new LouisaMenuDao(DbCon.getConnection());
String restaurantName = request.getParameter("restaurantName");
List<LouisaMenu> lms = pd.getSpecificProducts(restaurantName);
%>
<!DOCTYPE html>
<html>
<head>
<title>Fitness & Expense Tracking</title>
<%@include file="includes/head.jsp"%>
<style type="text/css">
.card-img-top {
	width: 100%;
	height: 15vw;
	object-fit: cover;
}

.popup {
	width: 400px;
	background: #fff;
	border-radius: 6px;
	position: absolute;
	top: 0%;
	left: 50%;
	transform: translate(-50%, -50%) scale(0.1));
	text-align: center;
	padding: 0 30px 30px;
	color: #333;
	visibility: hidden;
}

.open-popup {
	visibility: visible;
	top: 50%;
	transform: translate(-50%, -50%) scale(1);
}

.popup img {
	width: 100px;
	margin-top: -50px;
	border-radius: 50%;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

.popup h2 {
	font-size: 38px;
	font-weoght: 500;
	margin: 30px 0 10px;
}

.popup button {
	width: 100%;
	margin-top: 50px;
	padding: 10px 0;
	background: #6fd649;
	color: #fff;
	border: 0;
	outline: none;
	font-size: 18px;
	border-radius: 4px;
	cursor: pointer;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
}
</style>
</head>

<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">Menu</div>

		<div class="row">
			<%
			if (!lms.isEmpty()) {
				for (LouisaMenu lm : lms) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top" src="product-images/<%=lm.getImage()%>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=lm.getName()%></h5>
						<h6 class="price">
							Price: $<%=lm.getPrice()%></h6>
						<h6 class="calorie">
							Calorie:
							<%=lm.getCalorie()%></h6>
						<h6 class="category">
							Category:
							<%=lm.getCategory()%></h6>
						<div class="col-md-12 text-center">
							<a href="add-to-cart?id=<%=lm.getId()%>" class="btn btn-primary"
								onclick="alert('Added Successfully!')">Add</a>
						
							<a href="RemoveFromLM?id=<%=lm.getId()%>" class="btn btn-danger"
								onclick="alet('Removed Successfully!')">Remove</a>
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
	 
	<%@include file="includes/footer.jsp"%>

</body>
</html>