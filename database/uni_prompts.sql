DROP DATABASE IF EXISTS university_db;

CREATE DATABASE university_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE university_db;

SET FOREIGN_KEY_CHECKS = 0;

-- ======================
-- PERSON
-- ======================

CREATE TABLE person (
    person_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    dni VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20)
);

-- ======================
-- UNIVERSITY
-- ======================

CREATE TABLE university (
    university_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL
);

-- ======================
-- FACULTY
-- ======================

CREATE TABLE faculty (
    faculty_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    university_id INT NOT NULL,

    FOREIGN KEY (university_id)
        REFERENCES university(university_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- ======================
-- CAREER
-- ======================

CREATE TABLE career (
    career_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    total_subjects INT NOT NULL
);

-- ======================
-- PROGRAM
-- ======================

CREATE TABLE program (
    program_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    career_id INT NOT NULL,
    faculty_id INT NOT NULL,

    FOREIGN KEY (career_id)
        REFERENCES career(career_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    FOREIGN KEY (faculty_id)
        REFERENCES faculty(faculty_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- ======================
-- PROFESSOR
-- ======================

CREATE TABLE professor (
    professor_id INT AUTO_INCREMENT PRIMARY KEY,
    person_id INT UNIQUE NOT NULL,
    specialization VARCHAR(100) NOT NULL,

    FOREIGN KEY (person_id)
        REFERENCES person(person_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- ======================
-- COURSE
-- ======================

CREATE TABLE course (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    professor_id INT,
    program_id INT NOT NULL,

    FOREIGN KEY (professor_id)
        REFERENCES professor(professor_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE,

    FOREIGN KEY (program_id)
        REFERENCES program(program_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- ======================
-- COURSE PREREQUISITES
-- ======================

CREATE TABLE course_prerequisite (
    course_id INT,
    prerequisite_course_id INT,

    PRIMARY KEY (course_id, prerequisite_course_id),

    FOREIGN KEY (course_id)
        REFERENCES course(course_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    FOREIGN KEY (prerequisite_course_id)
        REFERENCES course(course_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- ======================
-- STUDENT
-- ======================

CREATE TABLE student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    person_id INT UNIQUE NOT NULL,

    FOREIGN KEY (person_id)
        REFERENCES person(person_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- ======================
-- ENROLLMENT
-- ======================

CREATE TABLE enrollment (
    student_id INT,
    course_id INT,
    enrollment_date DATE NOT NULL,

    PRIMARY KEY (student_id, course_id),

    FOREIGN KEY (student_id)
        REFERENCES student(student_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    FOREIGN KEY (course_id)
        REFERENCES course(course_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- ======================
-- EXAM
-- ======================

CREATE TABLE exam (
    exam_id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT NOT NULL,
    year_number INT NOT NULL,
    semester_number INT NOT NULL,
    exam_date DATE NOT NULL,

    FOREIGN KEY (course_id)
        REFERENCES course(course_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- ======================
-- GRADE
-- ======================

CREATE TABLE grade (
    grade_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    exam_id INT NOT NULL,
    grade_value DECIMAL(4,2) NOT NULL,

    FOREIGN KEY (student_id)
        REFERENCES student(student_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    FOREIGN KEY (exam_id)
        REFERENCES exam(exam_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    UNIQUE(student_id, exam_id)
);

SET FOREIGN_KEY_CHECKS = 1;


USE university_db;

-- ======================
-- PERSON
-- ======================

INSERT INTO person (first_name, last_name, dni, email, phone)
VALUES ('John', 'Doe', '12345678', 'john.doe@email.com', '+1 555 1001');

INSERT INTO person (first_name, last_name, dni, email, phone)
VALUES ('Alice', 'Smith', '23456789', 'alice.smith@email.com', '+1 555 1002');

INSERT INTO person (first_name, last_name, dni, email, phone)
VALUES ('Robert', 'Johnson', '34567890', 'robert.johnson@email.com', '+1 555 1003');

INSERT INTO person (first_name, last_name, dni, email, phone)
VALUES ('Emma', 'Brown', '45678901', 'emma.brown@email.com', '+1 555 1004');

INSERT INTO person (first_name, last_name, dni, email, phone)
VALUES ('Michael', 'Davis', '56789012', 'michael.davis@email.com', '+1 555 1005');

-- ======================
-- UNIVERSITY
-- ======================

INSERT INTO university (name)
VALUES ('National University');

INSERT INTO university (name)
VALUES ('Tech University');

-- ======================
-- FACULTY
-- ======================

INSERT INTO faculty (name, university_id)
VALUES ('Engineering', 1);

INSERT INTO faculty (name, university_id)
VALUES ('Computer Science', 2);

-- ======================
-- CAREER
-- ======================

INSERT INTO career (name, total_subjects)
VALUES ('Software Engineering', 40);

INSERT INTO career (name, total_subjects)
VALUES ('Computer Science', 38);

-- ======================
-- PROGRAM
-- ======================

INSERT INTO program (name, career_id, faculty_id)
VALUES ('Undergraduate Program', 1, 1);

INSERT INTO program (name, career_id, faculty_id)
VALUES ('AI Specialization', 2, 2);

-- ======================
-- PROFESSOR
-- ======================

INSERT INTO professor (person_id, specialization)
VALUES (1, 'Databases');

INSERT INTO professor (person_id, specialization)
VALUES (2, 'Artificial Intelligence');

-- ======================
-- COURSE
-- ======================

INSERT INTO course (name, professor_id, program_id)
VALUES ('Database Systems', 1, 1);

INSERT INTO course (name, professor_id, program_id)
VALUES ('Machine Learning', 2, 2);

INSERT INTO course (name, professor_id, program_id)
VALUES ('Algorithms', 1, 1);

-- ======================
-- COURSE PREREQUISITES
-- ======================

INSERT INTO course_prerequisite (course_id, prerequisite_course_id)
VALUES (2, 3);

-- ======================
-- STUDENT
-- ======================

INSERT INTO student (person_id)
VALUES (3);

INSERT INTO student (person_id)
VALUES (4);

INSERT INTO student (person_id)
VALUES (5);

-- ======================
-- ENROLLMENT
-- ======================

INSERT INTO enrollment (student_id, course_id, enrollment_date)
VALUES (1, 1, '2026-03-01');

INSERT INTO enrollment (student_id, course_id, enrollment_date)
VALUES (1, 3, '2026-03-01');

INSERT INTO enrollment (student_id, course_id, enrollment_date)
VALUES (2, 1, '2026-03-02');

INSERT INTO enrollment (student_id, course_id, enrollment_date)
VALUES (3, 2, '2026-03-03');

-- ======================
-- EXAM
-- ======================

INSERT INTO exam (course_id, year_number, semester_number, exam_date)
VALUES (1, 2026, 1, '2026-06-15');

INSERT INTO exam (course_id, year_number, semester_number, exam_date)
VALUES (2, 2026, 1, '2026-06-20');

INSERT INTO exam (course_id, year_number, semester_number, exam_date)
VALUES (3, 2026, 1, '2026-06-10');

-- ======================
-- GRADE
-- ======================

INSERT INTO grade (student_id, exam_id, grade_value)
VALUES (1, 1, 8.50);

INSERT INTO grade (student_id, exam_id, grade_value)
VALUES (1, 3, 9.00);

INSERT INTO grade (student_id, exam_id, grade_value)
VALUES (2, 1, 6.00);

INSERT INTO grade (student_id, exam_id, grade_value)
VALUES (3, 2, 4.00);


USE university_db;

-- =====================================================
-- INSERT STATEMENTS
-- =====================================================

INSERT INTO university (name)
VALUES ('Global Tech University');

INSERT INTO faculty (name, university_id)
VALUES ('Mathematics', 1);

INSERT INTO career (name, total_subjects)
VALUES ('Data Science', 42);

INSERT INTO program (name, career_id, faculty_id)
VALUES ('Data Analytics Program', 1, 1);

INSERT INTO person (first_name, last_name, dni, email, phone)
VALUES ('Sophia', 'Wilson', '67890123', 'sophia.wilson@email.com', '+1 555 1006');

INSERT INTO professor (person_id, specialization)
VALUES (6, 'Statistics');

INSERT INTO course (name, professor_id, program_id)
VALUES ('Statistics I', 3, 3);

INSERT INTO person (first_name, last_name, dni, email, phone)
VALUES ('Daniel', 'Taylor', '78901234', 'daniel.taylor@email.com', '+1 555 1007');

INSERT INTO student (person_id)
VALUES (7);

INSERT INTO enrollment (student_id, course_id, enrollment_date)
VALUES (4, 4, '2026-03-05');

-- =====================================================
-- UPDATE STATEMENTS
-- =====================================================

UPDATE person
SET phone = '+1 555 9999'
WHERE person_id = 1;

UPDATE professor
SET specialization = 'Advanced Databases'
WHERE professor_id = 1;

UPDATE career
SET total_subjects = 45
WHERE career_id = 1;

UPDATE course
SET name = 'Advanced Machine Learning'
WHERE course_id = 2;

UPDATE faculty
SET name = 'Software Engineering Faculty'
WHERE faculty_id = 1;

UPDATE program
SET name = 'Undergraduate Engineering Program'
WHERE program_id = 1;

UPDATE grade
SET grade_value = 10.00
WHERE grade_id = 2;

UPDATE enrollment
SET enrollment_date = '2026-03-15'
WHERE student_id = 1
AND course_id = 1;

UPDATE exam
SET exam_date = '2026-07-01'
WHERE exam_id = 1;

UPDATE person
SET email = 'updated.email@email.com'
WHERE person_id = 3;

-- =====================================================
-- DELETE STATEMENTS
-- =====================================================

DELETE FROM enrollment
WHERE student_id = 4
AND course_id = 4;

DELETE FROM grade
WHERE grade_id = 4;

DELETE FROM exam
WHERE exam_id = 3;

DELETE FROM course_prerequisite
WHERE course_id = 2
AND prerequisite_course_id = 3;

DELETE FROM course
WHERE course_id = 4;

DELETE FROM professor
WHERE professor_id = 3;

DELETE FROM student
WHERE student_id = 4;

DELETE FROM person
WHERE person_id = 7;

DELETE FROM program
WHERE program_id = 3;

DELETE FROM career
WHERE career_id = 3;

-- =====================================================
-- ALTER TABLE STATEMENTS
-- =====================================================

ALTER TABLE person
ADD birth_date DATE;

ALTER TABLE course
ADD credits INT NOT NULL DEFAULT 4;

ALTER TABLE professor
ADD office_number VARCHAR(20);

ALTER TABLE career
MODIFY total_subjects SMALLINT NOT NULL;

ALTER TABLE faculty
RENAME COLUMN name TO faculty_name;

-- =====================================================
-- BIG JOIN STATEMENT
-- =====================================================

SELECT
    s.student_id,
    p.first_name,
    p.last_name,
    c.name AS course_name,
    prf.specialization,
    e.exam_date,
    g.grade_value,
    prog.name AS program_name,
    f.faculty_name,
    u.name AS university_name
FROM student s
JOIN person p
    ON s.person_id = p.person_id
JOIN enrollment en
    ON s.student_id = en.student_id
JOIN course c
    ON en.course_id = c.course_id
JOIN professor prf
    ON c.professor_id = prf.professor_id
JOIN person pp
    ON prf.person_id = pp.person_id
JOIN exam e
    ON c.course_id = e.course_id
JOIN grade g
    ON e.exam_id = g.exam_id
    AND s.student_id = g.student_id
JOIN program prog
    ON c.program_id = prog.program_id
JOIN faculty f
    ON prog.faculty_id = f.faculty_id
JOIN university u
    ON f.university_id = u.university_id;

-- =====================================================
-- INNER JOIN
-- =====================================================

SELECT
    c.name,
    pp.first_name,
    pp.last_name
FROM course c
INNER JOIN professor pr
    ON c.professor_id = pr.professor_id
INNER JOIN person pp
    ON pr.person_id = pp.person_id;

SELECT
    s.student_id,
    p.first_name,
    c.name
FROM student s
INNER JOIN person p
    ON s.person_id = p.person_id
INNER JOIN enrollment e
    ON s.student_id = e.student_id
INNER JOIN course c
    ON e.course_id = c.course_id;

-- =====================================================
-- LEFT JOIN
-- =====================================================

SELECT
    c.name,
    cp.prerequisite_course_id
FROM course c
LEFT JOIN course_prerequisite cp
    ON c.course_id = cp.course_id;

-- =====================================================
-- RIGHT JOIN
-- =====================================================

SELECT
    p.first_name,
    p.last_name,
    pr.specialization
FROM person p
RIGHT JOIN professor pr
    ON p.person_id = pr.person_id;

-- =====================================================
-- OUTER JOIN
-- =====================================================

SELECT
    s.student_id,
    p.first_name
FROM student s
LEFT JOIN person p
    ON s.person_id = p.person_id

UNION

SELECT
    s.student_id,
    p.first_name
FROM student s
RIGHT JOIN person p
    ON s.person_id = p.person_id;

-- =====================================================
-- GROUP BY WITHOUT HAVING
-- =====================================================

SELECT
    course_id,
    COUNT(*) AS total_enrollments
FROM enrollment
GROUP BY course_id;

SELECT
    professor_id,
    COUNT(*) AS total_courses
FROM course
GROUP BY professor_id;

SELECT
    course_id,
    AVG(grade_value) AS average_grade
FROM exam e
JOIN grade g
    ON e.exam_id = g.exam_id
GROUP BY course_id;

SELECT
    faculty_id,
    COUNT(*) AS total_programs
FROM program
GROUP BY faculty_id;

SELECT
    specialization,
    COUNT(*) AS total_professors
FROM professor
GROUP BY specialization;

SELECT
    semester_number,
    COUNT(*) AS total_exams
FROM exam
GROUP BY semester_number;

SELECT
    year_number,
    MAX(grade_value) AS highest_grade
FROM exam e
JOIN grade g
    ON e.exam_id = g.exam_id
GROUP BY year_number;

-- =====================================================
-- GROUP BY WITH HAVING
-- =====================================================

SELECT
    course_id,
    COUNT(*) AS total_enrollments
FROM enrollment
GROUP BY course_id
HAVING COUNT(*) > 1;

SELECT
    course_id,
    AVG(grade_value) AS average_grade
FROM exam e
JOIN grade g
    ON e.exam_id = g.exam_id
GROUP BY course_id
HAVING AVG(grade_value) >= 7;

SELECT
    faculty_id,
    COUNT(*) AS total_programs
FROM program
GROUP BY faculty_id
HAVING COUNT(*) >= 1;

SELECT
    professor_id,
    COUNT(*) AS total_courses
FROM course
GROUP BY professor_id
HAVING COUNT(*) >= 1;

SELECT
    semester_number,
    COUNT(*) AS total_exams
FROM exam
GROUP BY semester_number
HAVING COUNT(*) > 1;

SELECT
    year_number,
    MIN(grade_value) AS minimum_grade
FROM exam e
JOIN grade g
    ON e.exam_id = g.exam_id
GROUP BY year_number
HAVING MIN(grade_value) >= 4;

SELECT
    specialization,
    COUNT(*) AS total_professors
FROM professor
GROUP BY specialization
HAVING COUNT(*) >= 1;