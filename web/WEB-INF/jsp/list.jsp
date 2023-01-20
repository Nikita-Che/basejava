<%@ page import="com.urise.webapp.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список всех резюме</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<body>
<jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Имя</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Delete</th>
            <th>Edit</th>
            <%--        </tr>--%>

            <%--        <%--%>
            <%--            for (Resume resume : (List<Resume>) request.getAttribute("resumes")) {--%>
            <%--        %>--%>
            <%--        <c:forEach items="${resumes}" var="resume">--%>
            <%--            <tr>--%>
            <%--                <td><a href="resume?uuid=<%=resume.getUuid()%>"><%=resume.getFullName()%>--%>
            <%--                </a>--%>
            <%--                </td>--%>
            <%--                <td><%=resume.getContact(ContactType.EMAIL)%>--%>
            <%--                </td>--%>
            <%--            </tr>--%>
            <%--        <%--%>
            <%--            }--%>
            <%--        %>--%>

            <jsp:useBean id="resumes" scope="request" type="java.util.List"/>
            <c:forEach items="${resumes}" var="resume">
                <jsp:useBean id="resume" type="com.urise.webapp.model.Resume"/>

        <tr>
            <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName} </a></td>
            <td>${resume.getContact(ContactType.EMAIL)} </td>
            <td>${resume.getContact(ContactType.PHONE)} </td>
            <td><a href="resume?uuid=${resume.uuid}&action=delete"> <img src="img/delete.png" alt="2"></a></td>
            <td><a href="resume?uuid=${resume.uuid}&action=edit"> <img src="img/pencil.png" alt="3"> </a></td>
        </tr>
        </c:forEach>

    </table>
    <td><a href="resume?&action=addNewResume"><img src="img/plus.png" alt="4"> </a></td>
</section>
</body>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</html>
