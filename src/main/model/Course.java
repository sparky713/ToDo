package model;

// Represents a course with the course's code name, starting time, ending time, and professor
public class Course {

    private String courseName;
    private double start;
    private double end;
    private String professor;

    // EFFECTS: makes a new course with the course code, start and end time, and professor
    public Course(String code, double startTime, double endTime, String prof) {
        courseName = code;
        start = startTime;
        end = endTime;
        professor = prof;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public double getStartTime() {
        return this.start;
    }

    public double getEndTime() {
        return this.end;
    }

    public String getProfessor() {
        return this.professor;
    }
}
