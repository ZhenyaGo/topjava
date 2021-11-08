package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {
    private static final Sort SORT_DATE_TIME = Sort.by(Sort.Direction.DESC, "dateTime");


    private final CrudMealRepository crudRepository;
    private final UserRepository userRepository;


    public DataJpaMealRepository(CrudMealRepository crudRepository, UserRepository userRepository) {
        this.crudRepository = crudRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Meal save(Meal meal, int userId) {
        if(meal.isNew() || get(meal.id(),userId) != null) {
            User user = userRepository.get(userId);
            meal.setUser(user);
            return crudRepository.save(meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = crudRepository.findById(id).orElse(null);
        return meal != null && meal.getUser().getId() == userId ? meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.getAllByUserId(userId, SORT_DATE_TIME);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudRepository.getByUserIdAndDateTimeGreaterThanEqualAndDateTimeLessThan(userId, startDateTime, endDateTime, SORT_DATE_TIME);
    }


}
