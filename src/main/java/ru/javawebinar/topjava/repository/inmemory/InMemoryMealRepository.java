package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final List<Meal> meals = new ArrayList<>();
    private final Map<Integer, List<Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);



    {
        MealsUtil.meals.forEach(meal -> save(1, meal));
//        save(2, new Meal(LocalDateTime.of(2021, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
//        save(2, new Meal(LocalDateTime.of(2021, Month.JANUARY, 30, 13, 0), "Обед", 1000));
//        save(2, new Meal(LocalDateTime.of(2021, Month.JANUARY, 30, 20, 0), "Ужин", 500));
    }

    @Override
    public Meal save(Integer userId, Meal meal) {
            if (meal.isNew()) {
                meal.setId(counter.incrementAndGet());
                meals.add(meal);
                repository.put(userId, meals);
                return meal;
            }

        for(Meal m: repository.get(userId)) {
            if(Objects.equals(m.getId(), meal.getId())) {
                int index = repository.get(userId).indexOf(m);
                repository.get(userId).set(index, meal);
                return meal;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Integer userId, int id) {
        return repository.containsKey(userId) && repository.get(userId).removeIf(u -> u.getId() == id);
    }

    @Override
    public Meal get(Integer userId, int id) {
        return repository.containsKey(userId) ? repository.get(userId).stream()
                .filter(meal -> meal.getId() == id)
                .findFirst().orElse(null) : null;
    }

    @Override
    public Collection<Meal> getAll(Integer userId) {
        return repository.get(userId).stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<MealTo> getAllByDate(Integer userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return MealsUtil.getFilteredTos(getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY, startDate, startTime, endDate, endTime);
    }

}

