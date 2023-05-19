
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>Identity Selection Page</title>
<style type="text/css">
.card-img-top {
	width: 100%;
	height: 25vw;
	object-fit: cover;
}
</style>
</head>
<body>
 	<h2 class="text-center my-5">Select Registration Identity</h2>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<div  class="card w-100">
					<img class="card-img-top" src="product-images/student.jpg">
										<div class="card-body">
					
 						<div class="col-md-12 text-center">
							<h5 class="card-title">Student</h5>
							<a href="login.jsp" class="btn btn-primary">Select</a>
						</div>
				
						</div>
					</div>
				</div>

			<div class="col-6">
				<div  class="card w-100">
					<img class="card-img-top" src="product-images/owner.jpg">
 						
					<div class="card-body">
						<div class="col-md-12 text-center">
							<h5 class="card-title">Administrator</h5>
							<a href="a_login.jsp" class="btn btn-primary">Select</a>

						</div>
						
					</div>
					
				</div>
 				
			</div>

			</div>
		</div>

 


	<%@include file="/includes/footer.jsp"%>
</body>
</html>