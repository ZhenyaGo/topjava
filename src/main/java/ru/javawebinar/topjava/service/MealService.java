package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;


import java.util.List;

public interface MealService {

    void addMeal(Meal meal);

    Meal getById(int id);

    void deleteMeal(int id);

    void updateUser(Meal meal);

    List<MealTo> getAllMeals();


}
