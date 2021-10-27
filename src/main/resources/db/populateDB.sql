DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

insert into meals (user_id, date_time, description, calories) values (100000, '2020-01-30 10:00:00', 'Завтрак', 500);
insert into meals (user_id, date_time, description, calories) values (100000, '2020-01-30 13:00:00', 'Обед', 1000);
insert into meals (user_id, date_time, description, calories) values (100000, '2020-01-30 20:00:00', 'Ужин', 500);
insert into meals (user_id, date_time, description, calories) values (100000, '2020-01-31 00:00:00', 'Еда на граничное значение', 100);
insert into meals (user_id, date_time, description, calories) values (100000, '2020-01-31 10:00:00', 'Завтрак', 1000);
insert into meals (user_id, date_time, description, calories) values (100000, '2020-01-31 13:00:00', 'Обед', 500);
insert into meals (user_id, date_time, description, calories) values (100000, '2020-01-31 20:00:00', 'Ужин', 410);
insert into meals (user_id, date_time, description, calories) values (100000, '2020-06-15 08:00:00', 'Завтрак', 700);
insert into meals (user_id, date_time, description, calories) values (100000, '2020-06-15 13:00:00', 'Обед', 1000);
insert into meals (user_id, date_time, description, calories) values (100000, '2021-10-20 20:00:00', 'Ужин', 500);
insert into meals (user_id, date_time, description, calories) values (100001, '2015-06-01 11:00:00', 'Админ завтрак', 410);
insert into meals (user_id, date_time, description, calories) values (100001, '2015-06-01 14:00:00', 'Админ ланч', 410);
insert into meals (user_id, date_time, description, calories) values (100001, '2015-06-01 21:00:00', 'Админ ужин', 410);
