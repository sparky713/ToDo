package model;

import java.util.ArrayList;

// Represents an arraylist of tasks to be handled
public class TaskList {

    private ArrayList<Task> tasksToDo;

    // EFFECTS: make a new empty list of tasks
    public TaskList() {

        tasksToDo = new ArrayList<>();
    }

    public int getSize() {
        return tasksToDo.size();
    }

    // EFFECTS: returns the task with the given task description, return null if not found
    public Task getTask(String task) {
        for (Task t: tasksToDo) {
            if (t.getTaskDescription() == task) {
                return t;
            }
        }
        return null;
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

        for (Task t: tasksToDo) {
            if (t == task) {
                t.setToComplete();
            }
        }
    }

    // EFFECTS: returns true if list is empty, false otherwise
    public boolean isEmpty() {
        if (tasksToDo.size() == 0) {
            return true;
        }
        return false;
    }
}

