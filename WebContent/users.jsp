<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Management System</title>
<link rel="stylesheet" href="css/style.css"/>
<script type="text/javascript" scr="js/script.js"></script>
</head>
<body>
	<div class="social">
		<a href="number1"><img src="img/number1.png" height="21px"></a>
		<a href="number2"><img src="img/number2.png" height="21px"></a>
	</div>
	<div class="navigation" align="center">
		<ul class="nav">
			<li><a href="#">Home</a>
			<li><a href="#">About Us</a>
			<li><a href="#">Contact Us</a>
		</ul>
	</div>
	<header>
		<a href="#"><img src="img/number3.png"></a>
	</header>
	
	<h2 align="center">User Management System</h2>
	
	<c:url value="/addUser" var="addUserURL"></c:url>
	
	<div class="container">
		<div class="main" align="center">
			<c:if test="${requestScope.error ne null}">
				<strong style="color:red">
					<c:out value="${requestScope.error}"></c:out>
				</strong>
			</c:if>
			
			<c:if test="${requestScope.success ne null}">
				<strong style="color:green">
					<c:out value="${requestScope.success}"></c:out>
				</strong>
			</c:if>
			
			<form action='<c:out value="${addUserURL}"></c:out>' method="post">
				Name: <input type="text" name="name"><br>
				Email: <input type="text" name="email"><br>
				Password: <input type="password" name="password"><br>
				<input type="submit" value="Add User">
			</form>
			
			<%--Userlist logic --%>
			<c:if test="${not empty requestScope.users}">
				<table>
					<caption>Registered Users Details</caption>
					<tbody>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Email ID</th>
							<th>Password</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
						
						<c:forEach items="${requestScope.users}" var = "user">
							<c:url value="/editUser" var="editURL">
								<c:param name="id" value="${user.id}"></c:param>
							</c:url>
							
							<c:url value="/deleteUser" var="deleteURL">
								<c:param name="id" value="${user.id}"></c:param>
							</c:url>
							
							<tr>
								<td><c:out value="${user.id}"></c:out></td>
								<td><c:out value="${user.name}"></c:out></td>
								<td><c:out value="${user.email}"></c:out></td>
								<td><c:out value="**************"></c:out></td>
								<td><a href='<c:out value="${editURL}" escapeXml="true"></c:out>'>Edit</a></td>
								<td><a href='<c:out value="${deleteURL}" escapeXml="true"></c:out>'>Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>	
		</div>
	</div>
</body>
</html>