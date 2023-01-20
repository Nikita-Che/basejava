<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.urise.webapp.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>

    <table>
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry" type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.AbstractSection>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="com.urise.webapp.model.AbstractSection"/>
            <tr>
                <td colspan="2"><h2><a name="type.name">${type.title}</a></h2></td>
            </tr>
            <c:choose>
                <c:when test="${type=='OBJECTIVE'}">
                    <tr>
                        <td colspan="2">
                            <h3><%=((TextSection) section).getContent()%></h3>
                        </td>
                    </tr>
                </c:when>
                <c:when test="${type=='PERSONAL'}">
                    <tr>
                        <td colspan="2">
                            <%=((TextSection) section).getContent()%>
                        </td>
                    </tr>
                </c:when>
                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <tr>
                        <td colspan="2">
                            <ul>
                                <c:forEach var="item" items="<%=((ListSection) section).getItems()%>">
                                    <li>${item}</li>
                                </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </c:when>

                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizationList()%>">
                        <tr>
                            <td colspan="2">
                                <c:choose>
                                    <c:when test="${empty org.name}">
                                        <h3>${org.name}</h3>
                                    </c:when>
                                    <c:otherwise>
                                        <h3><a href="${org.name}">${org.name}</a></h3>
                                        <h4><a href="${org.website}">${org.website}</a></h4>

                                        <c:forEach var="position" items="${org.periods}">
                                            <jsp:useBean id="position" type="com.urise.webapp.model.Organization.Period"/>
                                            <tr>
                                                <td><b>${position.title}</b><br>${position.description}</td><br>
                                            </tr>
                                            <td><b>${position.startDate}</b><br>${position.endDate}</td>
                                        </c:forEach>

                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>