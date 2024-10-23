CREATE DATABASE magazine ENCODING 'UTF-8';

--Роли.
CREATE TABLE IF NOT EXISTS roles (
    id   SERIAL PRIMARY KEY,
    role VARCHAR(15) NOT NULL
    );

INSERT INTO roles (id, role) VALUES (DEFAULT, 'admin');
INSERT INTO roles (id, role) VALUES (DEFAULT, 'user');

--Пользователи.
CREATE TABLE IF NOT EXISTS users (
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(20) NOT NULL,
    role     INTEGER     NOT NULL,
    FOREIGN KEY (role) REFERENCES roles (id)
    );

--Добавляем тестовых пользователей.
INSERT INTO users (id, login, password, role) VALUES (DEFAULT, 'Sergey', '123', 1);
INSERT INTO users (id, login, password, role) VALUES (DEFAULT, 'user', '123', 2);


--Выгрузить пользователя с ролью.
SELECT u.id, u.login, u.password, r.id AS rol_id, r.role FROM users AS u LEFT JOIN roles AS r ON u.role = r.id WHERE u.login = (?);
--Удалить пользователя
DELETE FROM users WHERE id = (?) AND login = (?) AND password = (?) RETURNING id;
--Обновить пользователя
UPDATE users SET password = (?) WHERE id = (?) RETURNING id;



--Товары.
CREATE TABLE IF NOT EXISTS products (
    id   SERIAL PRIMARY KEY,
    productName VARCHAR(15) UNIQUE NOT NULL,
    productPrice NUMERIC NOT NULL
    );

INSERT INTO products (id, productName, productPrice) VALUES (DEFAULT, 'Название 1', 32.44)
    RETURNING id;

INSERT INTO products (id, productName, productPrice) VALUES (DEFAULT, 'Название 2', 25)
    RETURNING id;