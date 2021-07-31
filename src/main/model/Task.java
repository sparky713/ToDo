package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a task having a description, associated course, alarm setting
// and completed/not completed status
public class Task implements Writable {

    private String taskDescription;
    private boolean status;
    private String dueDate;

    // EFFECTS: makes a new task with a description of the task, the due date entered by user,
    // and whether or not the task is completed
    public Task(String description, String dueDate, boolean isItComplete) {
        taskDescription = description;
        this.dueDate = dueDate;
        status = isItComplete;
    }

    // getters & setters
    public String getTaskDescription() {

        return this.taskDescription;
    }

    public String getDueDate() {

        return this.dueDate;
    }


    public boolean getStatus() {

        return this.status;
    }

    public void setDueDate(String date) {
        this.dueDate = date;
    }

    public void setTaskDescription(String description) {
        this.taskDescription = description;
    }


    // EFFECTS: changes the completed/not completed status of the task
    public void setToComplete() {
        this.status = !status;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("taskDescription", taskDescription);
        json.put("dueDate", dueDate);
        json.put("status", status);
        return json;
    }
}

