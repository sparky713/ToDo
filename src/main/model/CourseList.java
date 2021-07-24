package model;

import java.util.ArrayList;

// Represents an arraylist of courses
public class CourseList {

    private ArrayList<Course> myCourses;

    // EFFECTS: makes a new empty list of courses
    public CourseList() {

        myCourses = new ArrayList<>();
    }

    public int getSize() {
        return myCourses.size();
    }

    // REQUIRES: The course is not already in the list
    // MODIFIES: this
    // EFFECTS: adds given course to this list
    public void addCourse(Course course) {
        this.myCourses.add(course);
    }

    // REQUIRES: the course is already in the list
    // MODIFIES: this
    // EFFECTS: removes given course from this list
    public void removeCourse(Course course) {
        this.myCourses.remove(course);
    }

    // EFFECTS: returns true if list is empty, false otherwise
    public boolean isEmpty() {
        if (myCourses.size() == 0) {
            return true;
        }
        return false;
    }

    // EFFECTS: returns true if given course is in list, false otherwise
    public boolean contains(Course course) {
        if (myCourses.contains(course)) {
            return true;
        }
        return false;
    }
}
