package model;

import exceptions.CourseNotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents an arraylist of courses
public class CourseList implements Writable {

    private ArrayList<Course> myCourses;

    // EFFECTS: makes a new empty list of courses
    public CourseList() {
        myCourses = new ArrayList<>();
    }

    public int getSize() {
        return myCourses.size();
    }

    // method implementation from Thingy in WorkRoom app
    // EFFECTS: returns an unmodifiable list of Courses in this course list
    public List<Course> getCourses() {
        return Collections.unmodifiableList(myCourses);
    }

    // EFFECTS: makes a new list holding the codes of the courses in the course list
    // and returns the course with the given course code,
    // throws CourseNotFoundException if the course is not in the list
    public Course getCourse(String courseCode) throws CourseNotFoundException {
        List<String> courseCodes = new ArrayList<>();
        for (Course c : myCourses) {
            courseCodes.add(c.getCode());
        }
        if (!courseCodes.contains(courseCode)) {
            throw new CourseNotFoundException();
        } else {
            return myCourses.get(courseCodes.indexOf(courseCode));
        }
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("coursesEnrolled", coursesEnrolledJson());
        return json;
    }

    // EFFECTS: returns courses in this course list as a JSON array
    private JSONArray coursesEnrolledJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : myCourses) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}



