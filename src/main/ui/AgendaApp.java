package ui;

import exceptions.CourseNotFoundException;
import model.Course;
import model.CourseList;
import model.Task;
import model.TaskList;
import persistence.JsonReaderForCourses;
import persistence.JsonReaderForTasks;
import persistence.JsonWriterForCourses;
import persistence.JsonWriterForTasks;

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
    private TaskList myTasks;
    private CourseList myCourses;
    private Scanner userInput;
    //  String selection = "";
    private JsonWriterForTasks jsonTasksWriter;
    private JsonWriterForCourses jsonCoursesWriter;
    private JsonReaderForTasks jsonTasksReader;
    private JsonReaderForCourses jsonCoursesReader;

    // EFFECTS: runs the application
    public AgendaApp() throws FileNotFoundException {
        myCourses = new CourseList();
        myTasks = new TaskList();
        jsonTasksWriter = new JsonWriterForTasks(JSON_TASKS);
        jsonTasksReader = new JsonReaderForTasks(JSON_TASKS);
        jsonCoursesWriter = new JsonWriterForCourses(JSON_COURSES);
        jsonCoursesReader = new JsonReaderForCourses(JSON_COURSES);
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
        System.out.println("\tst -> save tasks to file");
        System.out.println("\tsc -> save courses to file");
        System.out.println("\tlt -> load tasks from file");
        System.out.println("\tlc -> load courses from file");
        System.out.println("\tq -> quit application");

        System.out.println("\t+t -> add a task");
        System.out.println("\t-t -> remove a task");
        System.out.println("\t+c -> add a new course");
        System.out.println("\t-c -> remove a course");
    }

    // TODO method line too long
    // MODIFIES: this
    // EFFECTS: processes the user's input
    private void processUserInput(String input) {
        if (input.equals("c")) {
            displayCourses();
        } else if (input.equals("+c")) {
            addCourse();
        } else if (input.equals("-c")) {
            removeCourse();
        } else if (input.equals("t")) {
            viewTasks();
        } else if (input.equals("+t")) {
            addATask();
        } else if (input.equals("-t")) {
            removeATask();
        } else if (input.equals("ct")) {
            chooseTaskToComplete();
        } else if (input.equals("st")) {
            saveTasks();
        } else if (input.equals("sc")) {
            saveCourses();
        } else if (input.equals("lt")) {
            loadTasks();
        } else if (input.equals("lc")) {
            loadCourses();
        } else {
            System.out.println("Option not found, please select again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads courses from file
    private void loadCourses() {
        try {
            myCourses = jsonCoursesReader.read();
            System.out.println("Loaded my courses from " + JSON_TASKS);
        } catch (IOException e) {
            System.out.println("Unable to retrieve from file: " + JSON_TASKS);
        }
    }

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

    // MODIFIES: this
    // EFFECTS: loads tasks and courses from file
    private void loadTasks() {
        try {
            myTasks = jsonTasksReader.read();
            System.out.println("Loaded my tasks from " + JSON_TASKS);
        } catch (IOException e) {
            System.out.println("Unable to retrieve from file: " + JSON_TASKS);
        }
    }

    // EFFECTS: saves the task list and course list to file
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

    // MODIFIES: this
    // EFFECTS: removes a task in the tasks list
    private void removeATask() {
        //    tasks();
        System.out.println("Enter the task to remove:");

        Task remove = myTasks.getTask(userInput.next());
        String desc = remove.getTaskDescription();
        myTasks.removeTask(remove);
        System.out.println("Removed task: " + desc);
    }

    // MODIFIES: this
    // EFFECTS: completes a task in the tasks list and adds it to the completed tasks list
    private void chooseTaskToComplete() {
        //   tasks();
        System.out.println("Enter the task you wish to complete:");

        Task toComplete = myTasks.getTask(userInput.next());
        myTasks.completeTask(toComplete);
        System.out.println("Completed task: " + toComplete.getTaskDescription());
    }

    // EFFECTS: displays list of tasks in task list
    private void viewTasks() {
        List<Task> tasks = myTasks.getTasks();

        for (Task t : tasks) {
            System.out.println(t);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a task
    private void addATask() {
        //   tasks();
        System.out.println("Enter Task Description:");
        String description = userInput.next();
        System.out.println("When is it due?");
        String dueDate = userInput.next();
        Task newTask = new Task(description, dueDate, false);
        myTasks.addTask(newTask);

        System.out.println("Successfully added " + description + "!");
    }


    // EFFECTS: displays the courses in the course list
    private void displayCourses() {
        List<Course> courses = myCourses.getCourses();
        System.out.println("\nYour current courses:");

        for (Course c : courses) {
            System.out.println(c.getCode() + " (Starts: " + c.getStartTime() + " Ends: " + c.getEndTime()
                    + " Prof: " + c.getProfessor() + ")");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a course
    private void addCourse() {
        // courses();
        System.out.println("Enter Course Name:");
        String code = userInput.next();
        System.out.println("Enter Class Starting Time:");
        String start = userInput.next();
        System.out.println("Enter Class Ending Time:");
        String end = userInput.next();
        System.out.println("Professor:");
        String prof = userInput.next();
        myCourses.addCourse(new Course(code, start, end, prof));

        System.out.println("Successfully added " + code + "!");
        System.out.println("Class starts at " + start + " and ends at " + end);
        System.out.println(code + " Professor: " + prof);

    }

    // REQUIRES: list containing courses is not empty
    // MODIFIES: this
    // EFFECTS: removes a course
    private void removeCourse() {
        displayCourses();
        System.out.println("Enter the course you would like to remove:");
        String input = userInput.next();
        try {
            Course remove = myCourses.getCourse(input);
            myCourses.removeCourse(remove);
            System.out.println("Successfully removed " + input);
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
            System.out.println(input + " was not found in your courses.");
        }

    }

}
