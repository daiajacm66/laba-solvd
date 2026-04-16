package com.solvd.university;

import java.util.List;
import java.util.ArrayList;
import com.solvd.university.exceptions.PrerequisiteNotMetException;
import com.solvd.university.exceptions.InvalidGradeException;


import com.solvd.university.interfaces.functional.Action;
import com.solvd.university.interfaces.functional.CourseFilter;
import com.solvd.university.interfaces.functional.GradeEvaluator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.university.generics.EvaluationResult;
import com.solvd.university.generics.Pair;
import com.solvd.university.generics.Repository;
import com.solvd.university.interfaces.Describable;
import com.solvd.university.model.*;
import com.solvd.university.utils.FileWordCounter;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {

        // UNIVERSITY STRUCTURE

        University utn = new University(1, "UTN");

        Faculty frc = new Faculty(1, "FRC");
        utn.getFaculties().add(frc);

        Program engineering = new Program(1, "Engineering");
        frc.getPrograms().add(engineering);

        Career systems = new Career(1, "Information Systems Engineering", 45);
        engineering.setCareer(systems);

        // PROFESSORS

        Professor p1 = new Professor(1, "Gomez", "Algorithms");
        Professor p2 = new Professor(2, "Martinez", "Mathematics");

        // COURSES

        Course algebra = new Course(1, "Algebra and Geometry");
        Course analysis = new Course(2, "Mathematical Analysis I");
        Course algorithms = new Course(3, "Algorithms and Data Structures");
        Course architecture = new Course(4, "Computer Architecture");
        Course statistics = new Course(5, "Probability and Statistics");

        // Assign professors
        algebra.setProfessor(p2);
        analysis.setProfessor(p2);
        algorithms.setProfessor(p1);
        architecture.setProfessor(p1);
        statistics.setProfessor(p2);

        // PREREQUISITES
        architecture.getPrerequisites().add(algorithms);
        statistics.getPrerequisites().add(analysis);

        // ADD COURSES TO PROGRAM
        engineering.getCourses().add(algebra);
        engineering.getCourses().add(analysis);
        engineering.getCourses().add(algorithms);
        engineering.getCourses().add(architecture);
        engineering.getCourses().add(statistics);

        // EXAMS
        Exam exam1 = new Exam(1, "2024-12-10");
        Exam exam2 = new Exam(2, "2024-12-15");

        algorithms.getExams().add(exam1);
        analysis.getExams().add(exam2);

        // STUDENT
        Student student = new Student(1, "Lucia");

        // Grades (passed subjects)
        Grade g1 = new Grade(1, 8, algorithms);
        Grade g2 = new Grade(2, 7, analysis);

        student.getGrades().add(g1);
        student.getGrades().add(g2);

        // OUTPUT STRUCTURE
        logger.info("\nUniversity: " + utn.getName());
        logger.info("Faculty: " + frc.getName());
        logger.info("Program: " + engineering.getName());
        logger.info("Career: " + systems.getName());
        logger.info("Courses in program:");

        engineering.getCourses()
                .stream()
                .forEach(c -> logger.info(
                        "\t- " + c.getName() + " (Professor: " + c.getProfessor().getName() + ")"
                ));

        logger.info("\nStudent: " + student.getName());
        logger.info("Passed courses:");
        student.getGrades()
                .stream()
                .filter(g -> g.getValue() >= 6)
                .forEach(g -> logger.info(g.getCourse().getName()));


        logger.info("\n\nEnrollment simulation:");
        engineering.getCourses()
                .stream()
                .forEach(c -> {
                    try {
                        student.enroll(c);
                        logger.info("Enrolled in: " + c.getName());
                    } catch (PrerequisiteNotMetException e) {
                        logger.error(e.getMessage());
                    }
                });

        logger.info("Passed courses: " + student.countPassedCourses());
        if (student.canAdvance()) {
            logger.info("Student can advance to next year!");
        } else {
            logger.info("Student cannot advance yet.");
        }

        logger.info("\nPolymorphism example:");

        Evaluation e1 = new Exam(10, "2025-03-10");
        Evaluation e2 = new Grade(11, 9, algorithms);

        logger.info(e1.getDescription());
        logger.info(e2.getDescription());

        // LAST ADD
        logger.info("\nInterface polymorphism:");

        Describable d1 = new Exam(20, "2025-05-10");
        Describable d2 = new Grade(21, 10, algebra);

        logger.info(d1.getDescription());
        logger.info(d2.getDescription());

        // Example with Teachable
        logger.info("\nTeaching example:");
        logger.info(p1.teach());

        // Example with Gradable
        try {
            student.addGrade(new Grade(30, 9, algebra));
            logger.info("Grade added successfully");
        } catch (InvalidGradeException e) {
            logger.error(e.getMessage());
        }

        // Example with Enrollable
        try {
            student.enroll(algebra);
        } catch (PrerequisiteNotMetException e) {
            logger.error(e.getMessage());
        }

        List<Describable> evaluations = new ArrayList<>();
        evaluations.add(new Exam(1, "2025-01-01"));
        evaluations.add(new Grade(2, 8, algorithms));

        for (Describable d : evaluations) {
            logger.info(d.getDescription());
        }
        //History
        logger.info("\nStudent actions history:");
        student.showHistory();

        logger.info("Grade in Algebra: " + student.getGradeForCourse(algebra));

        //Add Generics
        Repository<Course> courseRepo = new Repository<>();
        courseRepo.add(algebra);
        courseRepo.add(analysis);

        logger.info("\nRepository courses:");
        for (Course c : courseRepo.getAll()) {
            logger.info(c.getName());
        }

        Pair<Course, Integer> pair = new Pair<>(algebra, 10);
        logger.info("Pair -> " + pair.getKey().getName() + ": " + pair.getValue());

        EvaluationResult<String> result = new EvaluationResult<>("Passed");
        logger.info("Evaluation result: " + result.getResult());

        //Add Enum + StringUtils + FileUtils
        try {
            FileWordCounter.countSpecialWords(
                    "src/main/resources/article.txt",
                    "src/main/resources/results.txt"
            );
            logger.info("Word count saved successfully.");
        } catch (Exception e) {
            logger.error("Error processing file: " + e.getMessage());
        }

        //Add lambda and streams
        CourseFilter difficultCourses = c -> c.getName().length() > 15;
        CourseFilter dataCourses = c -> c.getName().contains("Data");
        GradeEvaluator passedGrades = g -> g.getValue() >= 6;
        Action<Course> printCourse = c -> logger.info("Course: " + c.getName());

        logger.info("\nDifficult courses (name length > 15):");
        engineering.getCourses()
                .stream()
                .filter(difficultCourses::test)
                .map(Course::getName)
                .forEach(logger::info);

        logger.info("\nCourses containing 'Data':");
        engineering.getCourses()
                .stream()
                .filter(dataCourses::test)
                .map(Course::getName)
                .forEach(logger::info);

        logger.info("\nPassed courses:");
        student.getGrades()
                .stream()
                .filter(passedGrades::evaluate)
                .map(g -> g.getCourse().getName())
                .forEach(logger::info);

        logger.info("\nAll courses:");
        engineering.getCourses()
                .forEach(printCourse::execute);

    }
}
