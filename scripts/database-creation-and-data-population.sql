-- IF YOU WANT TO REBUID TABLES IN THE DATABASE

ALTER TABLE submissions DROP FOREIGN KEY fk_user;
ALTER TABLE submissions DROP FOREIGN KEY fk_problem;
ALTER TABLE runs DROP FOREIGN KEY fk_submission;
ALTER TABLE runs DROP FOREIGN KEY fk_test;
ALTER TABLE problems DROP FOREIGN KEY fk_user_problem;
ALTER TABLE problems DROP FOREIGN KEY fk_category;
ALTER TABLE submission_tests DROP FOREIGN KEY fk_submission_test;
ALTER TABLE submission_code DROP FOREIGN KEY fk_submission_code;
ALTER TABLE tests DROP FOREIGN KEY fk_problem_test;


DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS problems;
DROP TABLE IF EXISTS submissions;
DROP TABLE IF EXISTS runs;
DROP TABLE IF EXISTS submission_code;
DROP TABLE IF EXISTS submission_tests;
DROP TABLE IF EXISTS tests;


CREATE TABLE submissions (
  id int AUTO_INCREMENT PRIMARY KEY,
  userId int NOT NULL,
  problemId int NOT NULL,
  mainSource varchar(255) NOT NULL,
  language varchar(25) NOT NULL,
  date datetime DEFAULT CURRENT_TIMESTAMP,
  state varchar(50) NOT NULL,
  score int
);

CREATE TABLE runs (
  id int AUTO_INCREMENT PRIMARY KEY,
  submissionId int NOT NULL,
  testId int NOT NULL,
  runTimeMs int NOT NULL,
  memoryBytes int NOT NULL,
  status varchar(50) NOT NULL
);

CREATE TABLE users (
  id int AUTO_INCREMENT PRIMARY KEY,
  name varchar(100) NOT NULL,
  firstName varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  password varchar(200) NOT NULL,
  secretPassword varchar(200) NOT NULL,
  secretAnswer varchar(200) NOT NULL
);


CREATE TABLE problems(
    id int AUTO_INCREMENT PRIMARY KEY,
    userId int NOT NULL,
    categoryId int NOT NULL,
    title varchar(100) NOT NULL,
    description varchar(200) NOT NULL,
    requirement varchar(1000) NOT NULL,
    input varchar(100) NOT NULL,
    output varchar(100) NOT NULL,
    restrictions varchar(100) NOT NULL,
    difficulty int NOT NULL,
    dataType varchar(100),
    inputExample varchar(100) NOT NULL,
    outputExample varchar(100) NOT NULL,
    inputFile varchar(100) NOT NULL,
    outputFile varchar(100) NOT NULL,
    memoryLimit int NOT NULL,
    timeLimit int NOT NULL
);

CREATE TABLE categories(
    id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL
);

CREATE TABLE submission_code (
    id int AUTO_INCREMENT PRIMARY KEY,
    submissionId int NOT NULL,
    fileName varchar(50) NOT NULL,
    sourceCode varchar(2000) NOT NULL
);

CREATE TABLE submission_tests (
    id int AUTO_INCREMENT PRIMARY KEY,
    submissionId int NOT NULL,
    testId int NOT NULL,
    runTimeMs int NOT NULL,
    memoryBytes int NOT NULL,
    status varchar(20)
);

CREATE TABLE tests(
    id int AUTO_INCREMENT PRIMARY KEY,
    problemId int NOT NULL,
    input varchar(200) NOT NULL,
    expectedOutput VARCHAR(200) NOT NULL
);

-- ADD CONSTRAINTS

ALTER TABLE submissions
ADD CONSTRAINT fk_user FOREIGN KEY (userId) REFERENCES users(id),
ADD CONSTRAINT fk_problem FOREIGN KEY (problemId) REFERENCES problems(id)
ON DELETE CASCADE;
COMMIT;

ALTER TABLE runs
ADD CONSTRAINT fk_submission FOREIGN KEY (submissionId) REFERENCES submissions(id),
ADD CONSTRAINT fk_test FOREIGN KEY (testId) REFERENCES tests(id)
ON DELETE CASCADE;
COMMIT;

ALTER TABLE problems
ADD CONSTRAINT fk_user_problem FOREIGN KEY (userId) REFERENCES users(id),
ADD CONSTRAINT fk_category FOREIGN KEY (categoryId) REFERENCES categories(id)
ON DELETE CASCADE;
COMMIT;

ALTER TABLE submission_code
ADD CONSTRAINT fk_submission_code FOREIGN KEY (submissionId) REFERENCES submissions(id)
ON DELETE CASCADE;
COMMIT;

ALTER TABLE submission_tests
ADD CONSTRAINT fk_submission_test FOREIGN KEY (submissionId) REFERENCES submissions(id)
ON DELETE CASCADE;
COMMIT;

ALTER TABLE tests
ADD CONSTRAINT fk_problem_test FOREIGN KEY (problemId) REFERENCES problems(id)
ON DELETE CASCADE;
COMMIT;



-- POPULATE TABLES (initially, for testing reasons, or for default problems)
-- these values are already in the tables
INSERT INTO users(name, firstName, email, password, secretPassword, secretAnswer) VALUES
('Popescu', 'Andra', 'andra.popescu@gmai.com', 'andra123', 'andra321', 'study');

INSERT INTO categories (name) VALUES ('medium');

INSERT INTO problems (userId,  categoryId, title, description, requirement, input, output, restrictions, difficulty , dataType, inputExample, outputExample, inputFile, outputFile, memoryLimit, timeLimit) VALUES
(1, 1, 'Camioane', 'Problema Camioane', 'O firma are doua tipuri de camioane: camioane de tipul 1, care pot transporta cate t1 tone de marfa pe zi, si camioane de tipul 2, care pot transporta cate t2 tone de marfa pe zi. \n Stiind ca firma are n camioane de tipul 1 si m camioane de tipul 2, cate tone de marfa pot transporta camioanele firmei in z zile.', 'Programul citeste de la tastatura numerele naturale t1 t2 n m z.', 'Programul va afisa pe ecran rezultatul.', '2 < t1, t2 < 100\n2 < n, m < 100\n2 < z < 30', 1, 'tastatura', '3 5 4 2 5', '110', '', '', 1, 1.0000);

INSERT INTO submissions (userId, problemId, mainSource, language, date, state) VALUES
(1, 1, 'HelloWorld.java', 'Java', '2000-12-12 12:12:12', 'Waiting');


INSERT INTO submission_code (submissionId, fileName, sourceCode) VALUES
(1, 'Hello.java', 'content'),
(1, 'World.java', 'content');
