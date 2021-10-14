<%--
  Created by IntelliJ IDEA.
  User: Zhenya
  Date: 10.10.2021
  Time: 2:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border width="60%" cellspacing="0" style="height: 300px">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
        <c:forEach var="meal" items="${meals}">
            <c:if test="${meal.isExcess() == true}">
            <tr style="color: red">
                <td>${meal.getDate()}  ${meal.getTime()}</td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
            </tr>
            </c:if>
            <c:if test="${meal.isExcess() == false}">
                <tr style="color: green">
                    <td>${meal.getDate()}  ${meal.getTime()}</td>
                    <td>${meal.getDescription()}</td>
                    <td>${meal.getCalories()}</td>
                </tr>
            </c:if>
        </c:forEach>
</table>
</body>
</html>
