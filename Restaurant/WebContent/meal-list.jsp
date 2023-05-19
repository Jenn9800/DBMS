<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.ProductDao"%>
<%@page import="cn.techtutorial.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table {
  table-layout: fixed ;
  width: 100% ;
}
td {
  width: 25% ;
}
</style>
<title>Admin All Rest Page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a class="navbar-brand" style="color: white">Admin Management Page </a>
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

	<div class="row">
 
		<div class="container">
			<h3 class="text-center">List of Meals</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new-meal" class="btn btn-success">Add
					New Meal</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Restaurant Name</th>
						<th>Price</th>
						<th>Calorie</th>
						<th>Category</th>
						
						<th>Image</th>
						
						<th>Edit</th>
						<th>Delete</th>
 
					</tr>
				</thead>
				<tbody>
 					<c:forEach var="meal" items="${listMeal}">

						<tr>
							<td><c:out value="${meal.id}" /></td>
							<td><c:out value="${meal.name}" /></td>
							<td><c:out value="${meal.r_name}" /></td>
							<td><c:out value="${meal.price}"/></td>
							<td><c:out value="${meal.calorie}"/></td>
							<td><c:out value="${meal.category}"/></td>
 							
							<td><img src="<c:url value='/Restaurant/WebContent/productImages/louisa.jpg'/>" style="max-height:50%; max-width:50%"/></td>
  							<td><a href="edit-meal?id=<c:out value='${meal.id}' />">Edit</a>
							<td><a href="delete-meal?id=<c:out value='${meal.id}' />">Delete</a></td>
 							
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>