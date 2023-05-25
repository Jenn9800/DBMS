<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Personal Information</title>
</head>
<%@include file="/includes/head.jsp"%>


<body>
	<%@include file="/includes/navbar.jsp"%>
<br>
<h2><center>Update Personal Information</center></h2>
<br>

<form action="PersonalServlet" method="post">
<table align="center">
<tr>
<td>Name</td>
<td><input type="text" name="name" value="${name}"></td>
</tr>

<tr>
<td>Email</td>
<td><input type="text" name="email" value="${email}"></td>
</tr>

<tr>
<td>Password</td>
<td><input type="text" name="password" value="${password}"></td>
</tr>

<tr>
<td>Gender</td>
<td><input type="text" name="gender" value="${gender}"></td>
</tr>

<tr>
<td>Height</td>
<td><input type="text" name="height" value="${height}"></td>
</tr>

<tr>
<td>Weight</td>
<td><input type="text" name="weight" value="${weight}"></td>
</tr>

<tr>
<td>Budget</td>
<td><input type="text" name="budget" value="${budget}"></td>
</tr>

<tr>
<td>Calorie Goal</td>
<td><input type="text" name="cal_limit" value="${cal_limit}"></td>
</tr>

<tr>
<td></td>
<td><input type="submit" value="Update"></td>
</tr>

</table>

</form>
	<%@include file="/includes/footer.jsp"%>

</body>
</html>