package persistence;

import org.json.JSONObject;

// Functionality and methods are implemented from JsonSerializationDemo. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
