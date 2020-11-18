CREATE TABLE IF NOT EXISTS Users (
  id UUID PRIMARY KEY,
  email VARCHAR(100) NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  age INT,
  gender VARCHAR(50),
  interests TEXT,
  city VARCHAR(50)
);