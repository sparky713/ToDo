package model;

// Represents a task having a description, associated course, alarm setting
// and completed/not completed status
public class Task {

    private String taskDescription;
    private boolean status;
    private Date deadline;
    private Course associatedCourse;

    // EFFECTS: makes a new task with a description of the task, course it's related
    //          to, deadline, and whether or not an alarm is set for this task
    public Task(String description, Course course, Date dueDate, boolean isItComplete) {
        taskDescription = description;
        deadline = dueDate;
        associatedCourse = course;
        status = isItComplete;
    }

    public String getTaskDescription() {

        return this.taskDescription;
    }

    public Course getCourse() {
        return this.associatedCourse;
    }

    public Date getDeadline() {

        return this.deadline;
    }

    public boolean getStatus() {

        return this.status;
    }

    public void setTaskDescription(String description) {
        this.taskDescription = description;
    }

    public void setDeadline(Date dueDate) {
        this.deadline = dueDate;
    }

    // REQUIRES: Task is not yet completed
    // EFFECTS: sets the status of the task to completed
    public void setToComplete() {
        this.status = true;
    }
}

