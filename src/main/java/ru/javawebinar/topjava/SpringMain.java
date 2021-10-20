package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalTime;
;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));

//            adminUserController.create(new User(null, "Evgenii", "gonzay@gmail.com", "HjjKK", Role.USER));
//            adminUserController.create(new User(null, "Peter", "alexeenkov@gmail.com", "HooUUy", Role.USER));
//            adminUserController.create(new User(null, "Denis", "osush@gmail.com", "vwOyCR", Role.USER));
//            adminUserController.create(new User(null, "Nikolay", "kozlov@gmail.com", "KGhUo", Role.USER));
//            adminUserController.create(new User(null, "Dmitrii", "strelec@gmail.com", "CnHtKt", Role.USER));
//            adminUserController.create(new User(null, "Dmitrii", "strelecaa@gmail.com", "CnHtKt", Role.USER));
//            adminUserController.create(new User(null, "Artem", "hanin@gmail.com", "JuRGuk", Role.USER));

            adminUserController.getAll().forEach(System.out::println);
//            System.out.println();
//            System.out.println(adminUserController.getByMail("osush@gmail.com"));
//            System.out.println();
//            System.out.println(adminUserController.get(6));
//            System.out.println();
//            adminUserController.delete(7);
//            adminUserController.getAll().forEach(System.out::println);
//            System.out.println();
//            adminUserController.update(new User(2, "Sasha", "gonzay@gmail.com", "HjjKK", Role.USER), 2);
//            adminUserController.getAll().forEach(System.out::println);

            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
//            mealRestController.getAll().forEach(System.out::println);
//            System.out.println(mealRestController.get(5));
//            mealRestController.create(new Meal(LocalDateTime.of(2021, Month.OCTOBER, 19, 23, 0), "Ночной жор", 1000));
//            mealRestController.getAll().forEach(System.out::println);
////            mealRestController.delete(8);
////            mealRestController.getAll().forEach(System.out::println);
//            Meal meal = new Meal(LocalDateTime.of(2021, Month.OCTOBER, 19, 20, 0), "Dinner", 800);
//            meal.setId(5);
//            mealRestController.update(meal, 5);
//            mealRestController.getAll().forEach(System.out::println);

            mealRestController.getFilteredByDate(LocalDate.of(2020, 1, 30),
                    LocalTime.of(10,0), LocalDate.of(2020, 1, 31),
                    LocalTime.of(11,0)).forEach(System.out::println);

//            mealRestController.getFilteredByDate(null, null, null, null).forEach(System.out::println);
        }
    }

    //            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
    //            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
    //            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
    //            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
    //            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
    //            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
    //            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
}
