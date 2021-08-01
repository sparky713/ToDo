package persistence;

import model.Course;
import model.CourseList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Functionality and methods are implemented from JsonReaderForTasks in JsonSerializationDemo. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads courseList from JSON data stored in file
public class CourseReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public CourseReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads tasks list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CourseList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCourseList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses course list from JSON object and returns it
    private CourseList parseCourseList(JSONObject jsonObject) {
        CourseList cl = new CourseList();
        addCourses(cl, jsonObject);
        return cl;
    }

    // MODIFIES: cl
    // EFFECTS: parses courses from JSON object and adds them to course list
    private void addCourses(CourseList cl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("coursesEnrolled");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addCourse(cl, nextTask);
        }
    }

    // MODIFIES: tl
    // EFFECTS: parses task from JSON object and adds it to task list
    private void addCourse(CourseList cl, JSONObject jsonObject) {
        String code = jsonObject.getString("code");
        String startTime = jsonObject.getString("start");
        String endTime = jsonObject.getString("end");
        String professor = jsonObject.getString("professor");
        Course course = new Course(code, startTime, endTime, professor);
        cl.addCourse(course);
    }
}




