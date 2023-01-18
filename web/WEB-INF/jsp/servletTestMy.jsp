<%@ page import="com.urise.webapp.model.Resume" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>1 000 000</h1>
Дата создания страницы: <%= new java.util.Date() %>
<p>${2+3} ровно 5</p>
<a href="http://nchsoft.ru">My site</a>
<%out.print(new Resume("uuid", "vasya"));%>
<ul>
   <c:forEach var="user" items="${requestScope.users}">
     <li>${user.uuid}</li>
   </c:forEach>
</ul>
</body>
</html>
