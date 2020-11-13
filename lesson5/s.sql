-- Решение домашнего задания #5

-- Создание таблицы school
CREATE TABLE school (
	id INT NOT NULL PRIMARY KEY,
	name VARCHAR(255),
	address VARCHAR(255)
);

COMMENT ON COLUMN school.name
IS 'Наименование школы';

COMMENT ON COLUMN school.address
IS 'Адрес школы';

-- Создание таблицы subject
CREATE TABLE subject (
	id INT NOT NULL PRIMARY KEY,
	name VARCHAR(255)
);

COMMENT ON COLUMN subject.name
IS 'Наименование предмета';

-- Создание таблицы teacher
CREATE TABLE teacher (
	id INT NOT NULL PRIMARY KEY,
	name VARCHAR(255),
	age INT NOT NULL,
	gender BOOL NOT NULL,
	school_id INT NOT NULL REFERENCES school(id)
);

COMMENT ON COLUMN teacher.name
IS 'ФИО преподавателя';

COMMENT ON COLUMN teacher.age
IS 'Возраст преподавателя';

COMMENT ON COLUMN teacher.gender
IS 'Пол преподавателя';

COMMENT ON COLUMN teacher.school_id
IS 'Школа преподавателя';

-- Создание таблицы student
CREATE TABLE student (
	id INT NOT NULL PRIMARY KEY,
	name VARCHAR(255),
	age INT NOT NULL,
	gender BOOL NOT NULL,
	school_id INT NOT NULL REFERENCES school(id)
);

COMMENT ON COLUMN student.name
IS 'ФИО ученика';

COMMENT ON COLUMN student.age
IS 'Возраст ученика';

COMMENT ON COLUMN student.gender
IS 'Пол ученика';

COMMENT ON COLUMN student.school_id
IS 'Школа ученика';

-- Создание таблицы teacher_subject
CREATE TABLE teacher_subject (
	teacher_id INT NOT NULL REFERENCES teacher(id),
	subject_id INT NOT NULL REFERENCES subject(id),
	PRIMARY KEY(teacher_id, subject_id)
);

-- Создание таблицы student_subject
CREATE TABLE student_subject (
	student_id INT NOT NULL REFERENCES student(id),
	subject_id INT NOT NULL REFERENCES subject(id),
	PRIMARY KEY(student_id, subject_id)
);