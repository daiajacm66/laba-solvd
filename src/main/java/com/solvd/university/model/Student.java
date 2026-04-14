package com.solvd.university.model;
import java.util.ArrayList;
import java.util.List;
import com.solvd.university.interfaces.Enrollable;
import com.solvd.university.interfaces.PrerequisiteCheckable;
import com.solvd.university.interfaces.Gradable;
import com.solvd.university.exceptions.InvalidGradeException;
import com.solvd.university.exceptions.PrerequisiteNotMetException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

public class Student extends Person implements Enrollable, PrerequisiteCheckable, Gradable {
    private static final Logger logger = LogManager.getLogger(Student.class);
    private final List<Course> enrolledCourses;
    private final List<Grade> grades;
    private Set<Course> passedCourses = new HashSet<>();
    private final Map<Course, Integer> courseGrades;
    private Stack<String> actionsHistory = new Stack<>();

    public Student(int id, String name) {
        super(id, name);
        this.enrolledCourses = new ArrayList<>();
        this.grades = new ArrayList<>();
        this.courseGrades = new HashMap<>();
        this.passedCourses = new HashSet<>();
        this.actionsHistory = new Stack<>();
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    // CHECK IF COURSE IS APPROVED
    public boolean hasPassed(Course course) {
        return passedCourses.contains(course);
    }

    // CHECK PREREQUISITES
    public boolean canEnroll(Course course) {
        for (Course prereq : course.getPrerequisites()) {
            if (!hasPassed(prereq)) {
                return false;
            }
        }
        return true;
    }

    // ENROLL METHOD
    public void enroll(Course course) throws PrerequisiteNotMetException {

        if (!canEnroll(course)) {
            throw new PrerequisiteNotMetException(
                    "Cannot enroll in " + course.getName() + " due to missing prerequisites"
            );
        }
        enrolledCourses.add(course);
        actionsHistory.push("Enrolled in " + course.getName());
    }


    // COUNT PASSED COURSES
    public int countPassedCourses() {
        int count = 0;
        for (Grade g : grades) {
            if (g.getValue() >= 6) {
                count++;
            }
        }
        return count;
    }

    // CHECK IF CAN ADVANCE
    public boolean canAdvance() {
        return countPassedCourses() >= 3; // simple rule for demo
    }

    public Integer getGradeForCourse(Course course) {
        return courseGrades.get(course);
    }

    //History
    public void showHistory() {
        for (String action : actionsHistory) {
            logger.info(action);
        }
    }

    @Override
    public String toString() {
        return "Student: " + name + " | Passed: " + countPassedCourses();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Student))
            return false;
        Student s = (Student) o;
        return id == s.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public void addGrade(Grade grade) throws InvalidGradeException {

        if (grade.getValue() < 0 || grade.getValue() > 10) {
            throw new InvalidGradeException("Invalid grade value");
        }

        grades.add(grade);

        courseGrades.put(grade.getCourse(), grade.getValue());

        if (grade.getValue() >= 6) {
            passedCourses.add(grade.getCourse());
        }
        actionsHistory.push(
                "Added grade " + grade.getValue() +
                        " in " + grade.getCourse().getName()
        );
    }

}
