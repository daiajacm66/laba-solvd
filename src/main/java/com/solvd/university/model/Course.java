package com.solvd.university.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;


public class Course extends AcademicElement {

    private Professor professor;
    private List<Course> prerequisites;
    private List<Exam> exams;
    private Queue<Exam> examQueue = new LinkedList<>();

    public Course(int id, String name) {
        super(id, name);
        this.prerequisites = new ArrayList<>();
        this.exams = new ArrayList<>();
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public List<Exam> getExams() {
        return exams;
    }

    @Override
    public String toString() {
        return "Course: " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Course))
            return false;
        Course c = (Course) o;
        return name.equals(c.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}