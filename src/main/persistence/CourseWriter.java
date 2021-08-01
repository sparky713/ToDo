package persistence;

import model.CourseList;
import org.json.JSONObject;

// Functionality and methods are implemented from JsonWriter. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a writer that writes JSON representation of CourseList to file
public class CourseWriter extends JsonWriter {

    public CourseWriter(String destination) {
        super(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of CourseList to file
    public void write(CourseList cl) {
        JSONObject json = cl.toJson();
        saveToFile(json.toString(TAB));
    }
}
