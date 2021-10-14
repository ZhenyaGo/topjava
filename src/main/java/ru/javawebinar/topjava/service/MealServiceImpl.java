package ru.javawebinar.topjava.service;


import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;


import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MealServiceImpl implements MealService {

    //meal storage
    private static final Map<Integer, Meal> MEAL_REPOSITORY_MAP = createMap();

    //Переменная для генерации ID
    private static final AtomicInteger MEAL_ID = new AtomicInteger();


    private static Map<Integer, Meal> createMap() {
         Map<Integer, Meal> map = new HashMap<>();
         map.put(MEAL_ID.incrementAndGet(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500 ));
         map.put(MEAL_ID.incrementAndGet(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
         map.put(MEAL_ID.incrementAndGet(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
         map.put(MEAL_ID.incrementAndGet(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
         map.put(MEAL_ID.incrementAndGet(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
         map.put(MEAL_ID.incrementAndGet(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
         map.put(MEAL_ID.incrementAndGet(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
         return map;
    }



    @Override
    public void addMeal(Meal meal) {
        final int mealId = MEAL_ID.incrementAndGet();
        meal.setId(mealId);
        MEAL_REPOSITORY_MAP.put(mealId, meal);

    }

    @Override
    public Meal getById(int id) {
        return MEAL_REPOSITORY_MAP.get(id);
    }

    @Override
    public void deleteMeal(int id) {
        MEAL_REPOSITORY_MAP.remove(id);
    }

    @Override
    public void updateUser(Meal meal) {
        MEAL_REPOSITORY_MAP.put(meal.getId(), meal);

    }

    @Override
    public List<MealTo> getAllMeals() {
        return MealsUtil.getMealToList(new ArrayList<>(MEAL_REPOSITORY_MAP.values()), Meal.LIMIT);
    }
}
