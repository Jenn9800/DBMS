<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<c:if test="${product != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${product == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${product != null}">
            			Edit Restaurant
            		</c:if>
						<c:if test="${product == null}">
            			Add New Restaurant
            		</c:if>
					</h2>
				</caption>

				<c:if test="${product != null}">
					<input type="hidden" name="id"
						value="<c:out value='${product.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Restaurant Name</label> <input type="text"
						value="<c:out value='${product.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Restaurant Location</label> <br> <select
						name="location">
						<option>West Entrance</option>
						<option>East Entrance</option>
						<option>Main Entrance</option>
						<option>Others</option>

					</select>


				</fieldset>

				<fieldset class="form-group">
					<label>Restaurant Price</label> <input type="text"
						value="<c:out value='${product.price}' />" class="form-control"
						name="price">
				</fieldset>

				<fieldset class="form-group">
					<label>Restaurant Image</label> <input type="file"
						value="<c:out value='${product.image}' />"
						class="form-control-file" name="image">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>