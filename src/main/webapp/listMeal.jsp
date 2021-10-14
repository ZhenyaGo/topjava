<%--
  Created by IntelliJ IDEA.
  User: Zhenya
  Date: 11.10.2021
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Show All Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border width="60%" cellspacing="0" style="height: 300px">
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meal">
        <c:if test="${meal.isExcess() == true}">
        <tr style="color: red">
            <td>${meal.getDate()}  ${meal.getTime()}</td>
            <td><c:out value="${meal.getDescription()}" /></td>
            <td><c:out value="${meal.getCalories()}" /></td>
            <td><a href="MealServlet?action=edit&mealId=<c:out value="${meal.getId()}"/>">Update</a></td>
            <td><a href="MealServlet?action=delete&mealId=<c:out value="${meal.getId()}"/>">Delete</a></td>
        </tr>
        </c:if>
        <c:if test="${meal.isExcess() == false}">
            <tr style="color: green">
                <td>${meal.getDate()}  ${meal.getTime()}</td>
                <td><c:out value="${meal.getDescription()}" /></td>
                <td><c:out value="${meal.getCalories()}" /></td>
                <td><a href="MealServlet?action=edit&mealId=<c:out value="${meal.getId()}"/>">Update</a></td>
                <td><a href="MealServlet?action=delete&mealId=<c:out value="${meal.getId()}"/>">Delete</a></td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>
<p><a href="MealServlet?action=insert">Add Meal</a></p>
</body>
</html>
