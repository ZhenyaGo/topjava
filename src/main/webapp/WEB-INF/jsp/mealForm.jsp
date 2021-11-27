<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<%--<head>--%>
<%--    <title>Meal</title>--%>
<%--    <link rel="stylesheet" href="css/style.css">--%>
<%--</head>--%>
<jsp:include page="fragments/headTag.jsp"/>
<jsp:include page="fragments/bodyHeader.jsp"/>
<body>
<section>
<%--    <h3><a href="index.html">Home</a></h3>--%>
<%--    <hr>--%>
<%--    <h2>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>--%>

    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <h3><spring:message code="${meal.isNew() ? 'meal.add' : 'meal.edit'}"/></h3>
    <form method="post" action="${pageContext.request.contextPath}/meals">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
<%--            <dt>DateTime:</dt>--%>
             <dt><spring:message code="meal.dateTime"/>:</dt>
            <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime" required></dd>
        </dl>
        <dl>
<%--            <dt>Description:</dt>--%>
            <dt><spring:message code="meal.description"/>:</dt>
            <dd><input type="text" value="${meal.description}" size=40 name="description" required></dd>
        </dl>
        <dl>
<%--            <dt>Calories:</dt>--%>
            <dt><spring:message code="meal.calories"/>:</dt>
            <dd><input type="number" value="${meal.calories}" name="calories" required></dd>
        </dl>
<%--        <button type="submit">Save</button>--%>
<%--        <button onclick="window.history.back()" type="button">Cancel</button>--%>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
