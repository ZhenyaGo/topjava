package ru.javawebinar.topjava.web;


import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;


import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final MealService mealService;

    public MealServlet() {
            mealService = new MealServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to users");
        response.setContentType("text/html;charset=utf-8");

        String forward;
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            mealService.deleteMeal(mealId);
            forward = "/listMeal.jsp";
            request.setAttribute("meals", mealService.getAllMeals());
        } else if(action.equalsIgnoreCase("edit")) {
            forward = "/meal.jsp";
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealService.getById(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeal")){
            forward = "/listMeal.jsp";
            request.setAttribute("meals", mealService.getAllMeals());
        } else {
            forward = "/meal.jsp";
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to users");
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        LocalDateTime dateTime =  LocalDateTime.parse(request.getParameter("dateTime"));

        Meal meal = new Meal(dateTime, description, calories);

        String mealID = request.getParameter("mealID");
        if(mealID == null || mealID.isEmpty()) {
            mealService.addMeal(meal);
        } else {
            meal.setId(Integer.parseInt(mealID));
            mealService.updateUser(meal);
        }

        RequestDispatcher view = request.getRequestDispatcher("/listMeal.jsp");
        request.setAttribute("meals", mealService.getAllMeals());
        view.forward(request, response);

    }
}
