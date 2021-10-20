package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}


//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        String id = request.getParameter("id");
//
//        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
//                LocalDateTime.parse(request.getParameter("dateTime")),
//                request.getParameter("description"),
//                Integer.parseInt(request.getParameter("calories")));
//
//        log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
//        repository.save(SecurityUtil.authUserId(),meal);
//        response.sendRedirect("meals");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//
//        switch (action == null ? "all" : action) {
//            case "delete":
//                int id = getId(request);
//                log.info("Delete {}", id);
//                repository.delete(id);
//                response.sendRedirect("users");
//                break;
//            case "create":
//            case "update":
//                final User user = "create".equals(action) ?
//                        new User(null, "", "", "", Role.USER) :
//                        repository.get(getId(request));
//                request.setAttribute("user", user);
//                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
//                break;
//            case "all":
//            default:
//                log.info("getAll");
//                request.setAttribute("users", repository.getAll());
//                request.getRequestDispatcher("/users.jsp").forward(request, response);
//                break;
//        }
//    }
//
//    private int getId(HttpServletRequest request) {
//        String paramId = Objects.requireNonNull(request.getParameter("id"));
//        return Integer.parseInt(paramId);
//    }



//<%@ page contentType="text/html;charset=UTF-8" %>
//<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
//<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
//<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
//<html lang="ru">
//<head>
//    <title>Users</title>
//</head>
//<body>
//<h3><a href="index.html">Home</a></h3>
//<hr>
//<h2>Users</h2>
//<section>
//    <h3><a href="index.html">Home</a></h3>
//    <hr/>
//    <h2>Meals</h2>
//    <a href="meals?action=create">Add Meal</a>
//    <br><br>
//    <table border="1" cellpadding="8" cellspacing="0">
//        <thead>
//        <tr>
//            <th>Name</th>
//            <th>Email</th>
//            <th>Role</th>
//            <th>Enabled</th>
//            <th>Registration date</th>
//        </tr>
//        </thead>
//        <c:forEach items="${users}" var="user">
//            <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User"/>
//            <tr>
//                <td>${user.name}</td>
//                <td>${user.email}</td>
//                <td>${user.roles}</td>
//                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
//                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
//            </tr>
//        </c:forEach>
//    </table>
//</section>
//</body>
//</html>