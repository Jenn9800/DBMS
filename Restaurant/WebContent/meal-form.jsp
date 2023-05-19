<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>

<html>
<head>
<title>Admin Management Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md " style="background-color: tomato">
			<div>
				<a class="navbar-brand">Admin Management Page </a>
			</div>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">

				<ul class="navbar-nav ml-auto">
					<li><a href="<%=request.getContextPath()%>/list"
						class="nav-link" style="color: white">Restaurants</a></li>
				<li><a href="<%=request.getContextPath()%>/list-meal"
						class="nav-link" style="color: white">Meals</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${meal != null}">
					<form action="update-meal" method="post">
				</c:if>
				<c:if test="${meal == null}">
					<form action="insert-meal" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${meal != null}">
            			Edit Meal
            		</c:if>
						<c:if test="${meal == null}">
            			Add New Meal
            		</c:if>
					</h2>
				</caption>

				<c:if test="${meal != null}">
					<input type="hidden" name="id"
						value="<c:out value='${meal.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Meal Name</label> <input type="text"
						value="<c:out value='${meal.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Restaurant Name</label> 
					<br>
					<select name="r_name">
					<option>Select</option>
								
					<%
					try{
						 Class.forName("com.mysql.cj.jdbc.Driver");
				          Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_cart","root","jenn980047");
				          Statement statement = connection.createStatement();
				          String query= "select name from products";
				          ResultSet rs = statement.executeQuery(query);
				          
				          while (rs.next()){
				        	  %>
				        	  
				        	  <option ><%=rs.getString("name") %></option>
				        	  <% 
				          }
					}catch(Exception e){
						e.printStackTrace();
					}
					%>
					</select>
					 
				</fieldset>

				<fieldset class="form-group">
					<label>Meal Price</label> <input type="text"
						value="<c:out value='${meal.price}' />" class="form-control"
						name="price">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Meal Calorie</label> <input type="text"
						value="<c:out value='${meal.calorie}' />" class="form-control"
						name="calorie">
				</fieldset>
				<fieldset class="form-group">
					<label>Meal Category</label> <input type="text"
						value="<c:out value='${meal.category}' />" class="form-control"
						name="category">
				</fieldset>

				<fieldset class="form-group">
					<label>Meal Image</label> <input type="file"
						value="<c:out value='${meal.image}' />"
						class="form-control-file" name="image">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>