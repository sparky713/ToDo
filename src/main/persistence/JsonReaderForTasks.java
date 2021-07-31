package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Course;
import model.Task;
import model.TaskList;
import org.json.*;

// Functionality and methods are implemented from JsonReaderForTasks in JsonSerializationDemo. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads taskList from JSON data stored in file
public class JsonReaderForTasks {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderForTasks(String source) {
        this.source = source;
    }

    // EFFECTS: reads tasks list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TaskList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTaskList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    protected String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses task list from JSON object and returns it
    private TaskList parseTaskList(JSONObject jsonObject) {
        TaskList tl = new TaskList();
        addTasks(tl, jsonObject);
        return tl;
    }

    // MODIFIES: tl
    // EFFECTS: parses tasks from JSON object and adds them to task list
    private void addTasks(TaskList tl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tasksToDo");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(tl, nextTask);
        }
    }

    // MODIFIES: tl
    // EFFECTS: parses task from JSON object and adds it to task list
    private void addTask(TaskList tl, JSONObject jsonObject) {
        String description = jsonObject.getString("taskDescription");
        String dueDate = jsonObject.getString("dueDate");
        boolean status = jsonObject.getBoolean("status");
        Task task = new Task(description, dueDate, status);
        tl.addTask(task);
    }
}


