CREATE DATABASE university_db;
USE university_db;

SET FOREIGN_KEY_CHECKS = 0;

-- BASE PERSON

CREATE TABLE Person (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  dni VARCHAR(20) UNIQUE NOT NULL,
  email VARCHAR(100),
  phone VARCHAR(20)
);

-- UNIVERSITY STRUCTURE

CREATE TABLE University (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150) NOT NULL
);

CREATE TABLE Faculty (
  id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    university_id INT NOT NULL,

    FOREIGN KEY (university_id)
        REFERENCES University(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Career (
  id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    total_subjects INT NOT NULL
);

CREATE TABLE Program (
  id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    career_id INT NOT NULL,
    faculty_id INT NOT NULL,

    FOREIGN KEY (career_id)
        REFERENCES Career(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    FOREIGN KEY (faculty_id)
        REFERENCES Faculty(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- COURSES

CREATE TABLE Course (
  id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    professor_id INT,
    program_id INT NOT NULL,

    FOREIGN KEY (professor_id)
        REFERENCES Professor(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE,

    FOREIGN KEY (program_id)
        REFERENCES Program(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- PEOPLE ROLES

CREATE TABLE Student (
  id INT AUTO_INCREMENT PRIMARY KEY,
    person_id INT UNIQUE NOT NULL,

    FOREIGN KEY (person_id)
        REFERENCES Person(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Professor (
  id INT AUTO_INCREMENT PRIMARY KEY,
    person_id INT UNIQUE NOT NULL,
    specialization VARCHAR(100) NOT NULL,

    FOREIGN KEY (person_id)
        REFERENCES Person(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE CoursePrerequisite (
    course_id INT,
    prerequisite_course_id INT,

    PRIMARY KEY (course_id, prerequisite_course_id),

    FOREIGN KEY (course_id)
        REFERENCES Course(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    FOREIGN KEY (prerequisite_course_id)
        REFERENCES Course(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- ENROLLMENT

CREATE TABLE Enrollment (
  student_id INT,
    course_id INT,

    PRIMARY KEY (student_id, course_id),

    FOREIGN KEY (student_id)
        REFERENCES Student(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    FOREIGN KEY (course_id)
        REFERENCES Course(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- EVALUATION

CREATE TABLE Exam (
  id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT NOT NULL,
    year_number INT NOT NULL,
    semester_number INT NOT NULL,
    exam_date DATE NOT NULL,

    FOREIGN KEY (course_id)
        REFERENCES Course(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Grade (
  id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    exam_id INT NOT NULL,
    value DECIMAL(4,2) NOT NULL,

    FOREIGN KEY (student_id)
        REFERENCES Student(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    FOREIGN KEY (exam_id)
        REFERENCES Exam(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    UNIQUE(student_id, exam_id)
);

SET FOREIGN_KEY_CHECKS = 1;