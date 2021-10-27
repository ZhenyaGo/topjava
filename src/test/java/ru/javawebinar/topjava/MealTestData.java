package ru.javawebinar.topjava;


import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int NOT_FOUND = 10;

    //user's meal
    public static final Meal meal_1 = new Meal(100002,LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal meal_2 = new Meal(100003,LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal meal_3 = new Meal(100004,LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal meal_4 = new Meal(100005,LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal meal_5 = new Meal(100006,LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal meal_6 = new Meal(100007,LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
    public static final Meal meal_7 = new Meal(100008,LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
    public static final Meal meal_8 = new Meal(100009,LocalDateTime.of(2020, Month.JUNE, 15, 8, 0), "Завтрак", 700);
    public static final Meal meal_9 = new Meal(100010,LocalDateTime.of(2020, Month.JUNE, 15, 13, 0), "Обед", 1000);
    public static final Meal meal_10 = new Meal(100011,LocalDateTime.of(2021, Month.OCTOBER, 20, 20, 0), "Ужин", 500);

    //admins meal
    public static final Meal meal_11 = new Meal(100009,LocalDateTime.of(2015, Month.JUNE, 1, 11, 0), "Админ завтрак", 410);
    public static final Meal meal_12 = new Meal(100010,LocalDateTime.of(2015, Month.JUNE, 1, 11, 0), "Админ завтрак", 410);
    public static final Meal meal_13 = new Meal(100011,LocalDateTime.of(2015, Month.JUNE, 1, 11, 0), "Админ завтрак", 410);


    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2021, Month.OCTOBER, 20, 11, 0), "brunch", 1000);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal_3);
        updated.setId(meal_3.getId());//сетим id тк иначе id = null при создании объекта метод save в реп добавит в базу как новый
        updated.setDateTime(LocalDateTime.of(2021, Month.SEPTEMBER, 20, 17, 0));
        updated.setDescription("Полдник");
        updated.setCalories(1300);
        return updated;
    }

    public static LocalDate startDate = LocalDate.of(2020, Month.JANUARY, 31);
    public static LocalDate endDate = LocalDate.of(2020, Month.JUNE, 15);

    public static List<Meal> expected = Stream.of(meal_4, meal_5, meal_6, meal_7, meal_8, meal_9)
            .sorted(Comparator.comparing(Meal::getDateTime).reversed())
            .collect(Collectors.toList());

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }

}

