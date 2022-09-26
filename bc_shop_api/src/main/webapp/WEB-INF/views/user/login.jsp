<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:url value='/login' var="url"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${url}" method="post">
		<input name="username" type="text" placeholder="username">
		<input name="password" type="password" placeholder="Mat khau">
		<button type="submit">Login</button>
	</form>
</body>
</html>