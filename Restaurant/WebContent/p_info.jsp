<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.UserDao"%>
<%@page import="cn.techtutorial.model.*"%>
<%@page import="java.util.*"%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*,java.io.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<%
session = request.getSession();
String email = (String) session.getAttribute("loginemail");

%>
<!DOCTYPE html>

<html>
<%@include file="/includes/head.jsp"%>
<title>Personal Information Page</title>
<body>
    <%@include file="/includes/navbar.jsp"%>
<br>

<h2 align="center"><font>Personal Information</font></h2>

<table align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>
<tr style="background-color: DodgerBlue">
<td><b>ID</b></td>
<td><b>Name</b></td>
<td><b>Email</b></td>
<td><b>Password</b></td>
<td><b>Gender</b></td>
<td><b>Height</b></td>
<td><b>Weight</b></td>
<td><b>Budget</b></td>
<td><b>Calorie Goal</b></td>

</tr>

    <%
    System.out.println("login email:"+email);
    
try{ 
	
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_cart", "root", "jenn980047");
PreparedStatement pst = con.prepareStatement("select * from users where email=?");
pst.setString(1,email);
ResultSet rs = pst.executeQuery();
while(rs.next()){

%>
<tr bgcolor=white>

<td><%=rs.getInt("id") %></td>
<td><%=rs.getString("name") %></td>
<td><%=rs.getString("email") %></td>
<td><%=rs.getString("password") %></td>
<td><%=rs.getString("gender") %></td>
<td><%=rs.getInt("height") %></td>
<td><%=rs.getInt("weight") %></td>
<td><%=rs.getInt("budget") %></td>
<td><%=rs.getInt("cal_limit") %></td>

</tr>

<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>
</table>
<br>
<div style="text-align:center;">
<a href="user-form.jsp">Update</a>
</div>

        <%@include file="/includes/footer.jsp"%>
    
</body>
</html>
