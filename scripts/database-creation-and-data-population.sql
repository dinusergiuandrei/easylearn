DROP TABLE IF EXISTS submissions;

CREATE TABLE submissions (
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  userId varchar(50) NOT NULL,
  problemId int(11) NOT NULL,
  mainSource varchar(255) NOT NULL,
  language varchar(25) NOT NULL,
  date datetime DEFAULT CURRENT_TIMESTAMP,
  state varchar(50) NOT NULL
);

INSERT INTO submissions (id, userId, problemId, mainSource, language, date, state) VALUES
(1, '1', 1, 'asd', 'Java', '2000-12-12 12:12:12', 'Waiting'),
(2, '1', 1, 'string', 'Java', '2018-05-20 12:05:16', 'Waiting'),
(3, '1', 1, 'string', 'Java', '2018-05-20 12:05:30', 'Waiting'),
(4, '1', 1, 'string', 'Java', '2018-05-20 12:05:31', 'Waiting'),
(5, '1', 1, 'string', 'Java', '2018-05-20 12:05:10', 'Waiting'),
(6, '1', 1, 'mezzaa', 'Python', '2018-05-20 19:05:11', 'Waiting');

-------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS runs;

CREATE TABLE runs (
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  submissionId varchar(50) NOT NULL,
  testId int(11) NOT NULL,
  runTimeMs int NOT NULL,
  memoryBytes int NOT NULL,
  status varchar(50) NOT NULL
);

-------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  name varchar(100) NOT NULL,
  firstName varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  password varchar(100) NOT NULL
);

-------------------------------------------------------------------------------------------
