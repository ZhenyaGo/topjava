package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> map = new HashMap<>();
        List<UserMealWithExcess> list = new ArrayList<>();

        //определяем суммарное количество каллорий за каждый день
        for (UserMeal mealUser : meals) {
            LocalDate localDate = mealUser.getDateTime().toLocalDate();
            map.merge(localDate, mealUser.getCalories(), Integer::sum);
        }
        //Проверяем попадает ли прием пищи в заданый временной диапазон и если да, то заносим в список.
        for (UserMeal mealUser : meals) {
            LocalTime localTime = mealUser.getDateTime().toLocalTime();
            LocalDate localDate = mealUser.getDateTime().toLocalDate();
            if (TimeUtil.isBetweenHalfOpen(localTime, startTime, endTime)) {
                boolean excess = map.get(localDate) > caloriesPerDay;
                list.add(new UserMealWithExcess(mealUser.getDateTime(),
                        mealUser.getDescription(), mealUser.getCalories(), excess));
            }
        }
        return list;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> map = meals.stream()
                .collect(Collectors.toMap((p) -> p.getDateTime().toLocalDate(), UserMeal::getCalories, Integer::sum));

        return meals.stream()
                .filter((s) -> TimeUtil.isBetweenHalfOpen(s.getDateTime().toLocalTime(), startTime, endTime))
                .map((s) -> new UserMealWithExcess(s.getDateTime(), s.getDescription(), s.getCalories(),
                        map.get(s.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}