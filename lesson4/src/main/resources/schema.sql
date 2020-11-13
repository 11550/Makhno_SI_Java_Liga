-- Создание таблицы с покупателями
CREATE TABLE CUSTOMER (
     id  INT PRIMARY KEY AUTO_INCREMENT,
     name VARCHAR(50) NOT NULL,
     email VARCHAR(50) NOT NULL
);

-- Создание таблицы с заказами
CREATE TABLE ORDERS (
     id INT PRIMARY KEY AUTO_INCREMENT,
     name VARCHAR (50) NOT NULL,
     price INT NOT NULL,
     customer_id INT REFERENCES CUSTOMER(id)
);
