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
			<h3 class="text-center">List of Restaurants</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Restaurant</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Location</th>
						<th>Price</th>
						<th>Image</th>
						<th>Edit</th>
						<th>Delete</th>
 
					</tr>
				</thead>
				<tbody>
 					<c:forEach var="product" items="${listProduct}">

						<tr>
							<td><c:out value="${product.id}" /></td>
							<td><c:out value="${product.name}" /></td>
							<td><c:out value="${product.location}" /></td>
							<td><c:out value="${product.price}"/></td>
							
							<td><img src="<c:url value='productImages/${product.image}'/>" style="max-height:50%; max-width:50%"/></td>
  							<td><a href="edit?id=<c:out value='${product.id}' />">Edit</a>
							<td><a href="delete?id=<c:out value='${product.id}' />">Delete</a></td>
 							
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>