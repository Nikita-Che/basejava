<%@ page import="com.urise.webapp.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <%--    <link rel="stylesheet" href="css/style.css">--%>
    <title>Список всех резюме</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Имя</th>
            <th>Email</th>
            <%--            <th></th>--%>
            <%--            <th></th>--%>
        </tr>

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
                <td><a href="resume?uuid=${resume.uuid}">${resume.fullName}> </a></td>
                <td>${resume.getContact(ContactType.EMAIL)} </td>
            </tr>
        </c:forEach>

    </table>
</section>
</body>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</html>
