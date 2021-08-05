package ui;

import exceptions.CourseNotFoundException;
import exceptions.TaskNotFoundException;
import model.Course;
import model.CourseList;
import model.Task;
import model.TaskList;
import persistence.CourseReader;
import persistence.TaskReader;
import persistence.CourseWriter;
import persistence.TaskWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// UI Functionality and methods are implemented from Teller App and JsonSerializationDemo WorkRoomApp. Links below:
// https://github.students.cs.ubc.ca/CPSC210/TellerAppNotRobust/blob/c0c6a26c7e1625de943cd7ac6d4772eda9326239/src/main
// /ca/ubc/cpsc210/bank/model/Account.java#L11
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Course tasks application
public class AgendaApp {
    private static final String JSON_TASKS = "./data/tasks.json";
    private static final String JSON_COURSES = "./data/courses.json";
    private static final String JSON_COMPLETED_TASKS = "./data/completed.json";
    private TaskList myTasks;
    private TaskList completedTasks;
    private CourseList myCourses;
    private Scanner userInput;
    private TaskWriter jsonTasksWriter;
    private TaskWriter jsonCompletedTasksWriter;
    private CourseWriter jsonCoursesWriter;
    private TaskReader jsonTasksReader;
    private TaskReader jsonCompletedTasksReader;
    private CourseReader jsonCoursesReader;

    // EFFECTS: runs the application
    public AgendaApp() throws FileNotFoundException {
        myCourses = new CourseList();
        myTasks = new TaskList();
        completedTasks = new TaskList();
        jsonTasksWriter = new TaskWriter(JSON_TASKS);
        jsonTasksReader = new TaskReader(JSON_TASKS);
        jsonCoursesWriter = new CourseWriter(JSON_COURSES);
        jsonCoursesReader = new CourseReader(JSON_COURSES);
        jsonCompletedTasksWriter = new TaskWriter(JSON_COMPLETED_TASKS);
        jsonCompletedTasksReader = new TaskReader(JSON_COMPLETED_TASKS);
        runProgram();
    }


    // MODIFIES: this
    // EFFECTS: processes the users inputs
    private void runProgram() {
        userInput = new Scanner(System.in);
        boolean running = true;
        String input;

        while (running) {
            displaysOptions();
            input = userInput.next();

            if (input.equals("q")) {
                running = false;
            } else {
                processUserInput(input);
            }
        }
        System.out.println("See you again!");
    }

    // EFFECTS: displays the menu of options to the user
    private void displaysOptions() {
        System.out.println("\nChoose an option:");
        System.out.println("\tc -> view courses");
        System.out.println("\tt -> view tasks");
        System.out.println("\tct -> view completed tasks");
        System.out.println("\tst -> save tasks to file");
        System.out.println("\tsc -> save courses to file");
        System.out.println("\tsct -> save completed tasks to file");
        System.out.println("\tlt -> load tasks from file");
        System.out.println("\tlc -> load courses from file");
        System.out.println("\tlct -> load completed tasks from file");
        System.out.println("\tq -> quit application");
    }

    // MODIFIES: this
    // EFFECTS: processes the user's input
    private void processUserInput(String input) {
        if (input.equals("c")) {
            displayCourses();
            nextCourseCommand(input);
        } else if (input.equals("t")) {
            viewTasks();
            nextTaskCommand(input);
        } else if (input.equals("ct")) {
            viewCompletedTasks();
        } else if (input.equals("st")) {
            saveTasks();
        } else if (input.equals("sc")) {
            saveCourses();
        } else if (input.equals("sct")) {
            saveCompletedTasks();
        } else if (input.equals("lt")) {
            loadTasks();
        } else if (input.equals("lc")) {
            loadCourses();
        } else if (input.equals("lct")) {
            loadCompletedTasks();
        } else {
            System.out.println("Option not found, please select again.");
        }
    }


    // EFFECTS: displays the courses in the course list
    private void displayCourses() {
        List<Course> courses = myCourses.getCourses();
        System.out.println("\nYour current courses:");

        for (Course c : courses) {
            System.out.println(c.getCode() + " (Starts: " + c.getStartTime() + " Ends: " + c.getEndTime()
                    + " Prof: " + c.getProfessor() + ")");
        }
        System.out.println(" ");
    }

    // MODIFIES: this
    // EFFECTS: displays options to user to add or remove a course and processes the input
    private void nextCourseCommand(String input) {
        System.out.println("\t+ -> add a course");
        System.out.println("\t- -> remove a course");
        String nextCommand = userInput.next();
        if (nextCommand.equals("+")) {
            addCourse();
        } else if (nextCommand.equals("-")) {
            removeCourse();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a course
    private void addCourse() {
        userInput.nextLine();
        System.out.println("Enter Course Name:");
        String code = userInput.nextLine();
        System.out.println("Enter Class Starting Time:");
        String start = userInput.nextLine();
        System.out.println("Enter Class Ending Time:");
        String end = userInput.nextLine();
        System.out.println("Professor:");
        String prof = userInput.nextLine();
        myCourses.addCourse(new Course(code, start, end, prof));

        System.out.println("Successfully added " + code + "!");
        System.out.println("Class starts at " + start + " and ends at " + end);
        System.out.println(code + " Professor: " + prof);

    }

    // REQUIRES: list containing courses is not empty
    // MODIFIES: this
    // EFFECTS: removes a course
    private void removeCourse() {
        userInput.nextLine();
        System.out.println("Enter the course you would like to remove:");
        String input = userInput.nextLine();
        try {
            Course remove = myCourses.getCourse(input);
            myCourses.removeCourse(remove);
            System.out.println("Successfully removed " + input);
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
            System.out.println(input + " was not found in your courses.");
        }

    }

    // EFFECTS: displays list of tasks in task list
    private void viewTasks() {
        List<Task> tasks = myTasks.getTasks();
        System.out.println("Your tasks:");

        for (Task t : tasks) {
            System.out.println(t.getTaskDescription() + " (Due: " + t.getDueDate() + ")");
        }
        System.out.println(" ");
    }

    // MODIFIES: this
    // EFFECTS: displays options to user to add, remove, or complete a task and processes the input
    private void nextTaskCommand(String input) {
        System.out.println("\t+ -> add a task");
        System.out.println("\t- -> remove a task");
        System.out.println("\tcomplete -> complete a task");
        String nextCommand = userInput.next();
        if (nextCommand.equals("+")) {
            addATask();
        } else if (nextCommand.equals("-")) {
            removeATask();
        } else if (nextCommand.equals("complete")) {
            chooseTaskToComplete();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a task
    private void addATask() {
        userInput.nextLine();
        System.out.println("Enter Task Description:");
        String description = userInput.nextLine();
        System.out.println("When is it due?");
        String dueDate = userInput.nextLine();
        Task newTask = new Task(description, dueDate, false);
        myTasks.addTask(newTask);

        System.out.println("Successfully added " + description + "!");
    }

    // MODIFIES: this
    // EFFECTS: removes a task in the tasks list
    private void removeATask() {
        userInput.nextLine();
        System.out.println("Enter the task to remove:");
        String input = userInput.nextLine();
        try {
            Task remove = myTasks.getTask(input);
            myTasks.removeTask(remove);
            System.out.println("Removed task: " + input);
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
            System.out.println(input + " was not found in your tasks.");
        }
    }

    // MODIFIES: this
    // EFFECTS: completes a task in the tasks list and adds it to the completed tasks list
    private void chooseTaskToComplete() {
        userInput.nextLine();
        System.out.println("Enter the task you wish to complete:");
        String complete = userInput.nextLine();
        try {
            Task toComplete = myTasks.getTask(complete);
            myTasks.completeTask(toComplete);
            completedTasks.addTask(toComplete);
            myTasks.removeTask(toComplete);
            System.out.println("Completed task: " + toComplete.getTaskDescription());
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
            System.out.println(complete + " was not found in your tasks.");
        }
    }

    // EFFECTS: displays tasks in the completed tasks list with their description and due date
    private void viewCompletedTasks() {
        List<Task> compTasks = completedTasks.getTasks();

        for (Task ct : compTasks) {
            System.out.println(ct.getTaskDescription() + " (Was Due: " + ct.getDueDate() + ")");
        }
    }

    // EFFECTS: saves the task list to file
    private void saveTasks() {
        try {
            jsonTasksWriter.open();
            jsonTasksWriter.write(myTasks);
            jsonTasksWriter.close();
            System.out.println("Saved tasks to " + JSON_TASKS);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file: " + JSON_TASKS);
        }
    }

    // EFFECTS: saves the course list to file
    private void saveCourses() {
        try {
            jsonCoursesWriter.open();
            jsonCoursesWriter.write(myCourses);
            jsonCoursesWriter.close();
            System.out.println("Saved courses to " + JSON_COURSES);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file: " + JSON_COURSES);
        }
    }

    // EFFECTS: saves the completed tasks list to file
    private void saveCompletedTasks() {
        try {
            jsonCompletedTasksWriter.open();
            jsonCompletedTasksWriter.write(completedTasks);
            jsonCompletedTasksWriter.close();
            System.out.println("Saved completed tasks to " + JSON_COMPLETED_TASKS);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file: " + JSON_COMPLETED_TASKS);
        }
    }


    // MODIFIES: this
    // EFFECTS: loads tasks from file
    private void loadTasks() {
        try {
            myTasks = jsonTasksReader.read();
            System.out.println("Loaded my tasks from " + JSON_TASKS);
        } catch (IOException e) {
            System.out.println("Unable to retrieve from file: " + JSON_TASKS);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads courses from file
    private void loadCourses() {
        try {
            myCourses = jsonCoursesReader.read();
            System.out.println("Loaded my courses from " + JSON_COURSES);
        } catch (IOException e) {
            System.out.println("Unable to retrieve from file: " + JSON_COURSES);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads completed tasks from file
    private void loadCompletedTasks() {
        try {
            completedTasks = jsonCompletedTasksReader.read();
            System.out.println("Loaded my completed tasks from " + JSON_COMPLETED_TASKS);
        } catch (IOException e) {
            System.out.println("Unable to retrieve from file: " + JSON_COMPLETED_TASKS);
        }
    }

}

