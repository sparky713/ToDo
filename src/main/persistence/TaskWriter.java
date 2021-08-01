package persistence;

import model.TaskList;
import org.json.JSONObject;


import java.io.*;

// Functionality and methods are implemented from JsonWriter. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a writer that writes JSON representation of taskList to file
public class TaskWriter extends JsonWriter {

    // EFFECTS: constructs writer to write to destination file
    public TaskWriter(String destination) {
        super(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of TaskList to file
    public void write(TaskList tl) {
        JSONObject json = tl.toJson();
        saveToFile(json.toString(TAB));
    }
}
