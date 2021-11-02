package model;

import exceptions.CourseNotFoundException;
import exceptions.TaskNotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Represents an arraylist of tasks to be handled
public class TaskList implements Writable, Iterable<Task> {

    private ArrayList<Task> tasksToDo;

    // EFFECTS: make a new empty list of tasks
    public TaskList() {

        tasksToDo = new ArrayList<>();
    }

    public int getSize() {
        return tasksToDo.size();
    }

    // EFFECTS: makes a new list holding the descriptions of the tasks in the task list
    // and returns the course with the given course code,
    // throws TaskNotFoundException if the course is not in the list
    public Task getTask(String task) throws TaskNotFoundException {
        List<String> taskDescriptions = new ArrayList<>();
        for (Task t : tasksToDo) {
            taskDescriptions.add(t.getTaskDescription());
        }
        if (!taskDescriptions.contains(task)) {
            throw new TaskNotFoundException();
        } else {
            return tasksToDo.get(taskDescriptions.indexOf(task));
        }
    }

    // EFFECTS: returns true if given task is in list, false otherwise
    public boolean contains(Task task) {
        if (tasksToDo.contains(task)) {
            return true;
        }
        return false;
    }

    // REQUIRES: task is not yet completed
    // MODIFIES: this
    // EFFECTS: adds given task to this list
    public void addTask(Task task) {

        this.tasksToDo.add(task);
    }

    // REQUIRES: given task is already in the list
    // MODIFIES: this
    // EFFECTS: removes given task from this list
    public void removeTask(Task task) {

        this.tasksToDo.remove(task);
    }

    // REQUIRES: the list must contain the given task and the task is not yet completed
    // MODIFIES: this
    // EFFECTS: searches the given task in the list and
    //          marks it as completed
    public void completeTask(Task task) {

        for (Task t : tasksToDo) {
            if (t == task) {
                t.setToComplete();
            }
        }
    }

    // method implementation from Thingy in WorkRoom app
    // EFFECTS: returns an unmodifiable list of tasks in this tasks list
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasksToDo);
    }

    // EFFECTS: returns true if list is empty, false otherwise
    public boolean isEmpty() {
        if (tasksToDo.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public Iterator<Task> iterator() {
        return tasksToDo.iterator();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("tasksToDo", tasksToDoToJson());
        return json;
    }

    // EFFECTS: returns tasks in this task list as a JSON array
    private JSONArray tasksToDoToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : tasksToDo) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}

