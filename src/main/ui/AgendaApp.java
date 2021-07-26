package ui;

import model.Course;
import model.CourseList;
import model.Task;
import model.TaskList;

import java.util.Scanner;


// UI Functionality and methods are implemented from Teller App. Link below:
// Course tasks application
public class AgendaApp {

    private Task quiz;
    private Task reading;
    private Task essay;
    private Task poemAnalysis;
    private Task midterm;
    private TaskList homework;
    private Course cs210;
    private Course eng110;
    private Course math221;
    private Course math220;
    private CourseList defaultCourses;
    private Scanner userInput;
    String selection = "";

    // EFFECTS: runs the application
    public AgendaApp() {
        runProgram();
    }

    // MODIFIES: this and defaultCourses
    // EFFECTS: initializes the default course list with the default courses added
    private void courses() {
        defaultCourses = new CourseList();
        cs210 = new Course("CPSC210", 9.5, 1, "Felix Grund");
        eng110 = new Course("ENGL110", 12, 3.5, "Rebecca Sheppard");
        math221 = new Course("MATH221", 9.5, 1, "TBD");
        math220 = new Course("MATH220", 9.5, 1, "TBD");
        defaultCourses.addCourse(cs210);
        defaultCourses.addCourse(eng110);
        defaultCourses.addCourse(math221);
        defaultCourses.addCourse(math220);
    }

    // MODIFIES: this
    // EFFECTS: initializes tasks and a task list, adds the tasks to the list
    private void tasks() {
        homework = new TaskList();
        quiz = new Task("quiz1", cs210, false);
        reading = new Task("Read C5-8", cs210, false);
        essay = new Task("Theme Analysis Essay", eng110, false);
        poemAnalysis = new Task("Poem Analysis", eng110, false);
        midterm = new Task("Midterm 1", math221, false);
        homework.addTask(quiz);
        homework.addTask(reading);
        homework.addTask(essay);
        homework.addTask(poemAnalysis);
        homework.addTask(midterm);
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

            if (input.equals("s")) {
                selectCourse();
                selectedCourseToView();
            } else if (input.equals("+")) {
                addCourse();
            } else if (input.equals("-")) {
                removeCourse();
            } else if (input.equals("t")) {
                addTask();
            } else if (input.equals("q")) {
                running = false;
            }
        }

        System.out.println("See you again!");
    }

    // MODIFIES: this
    // EFFECTS: adds a task
    private void addTask() {
        tasks();
        courses();
        Course course;
        System.out.println("Enter Task Description:");
        String description = userInput.next();
        selectCourse();
        if (selection.equals("1")) {
            course = cs210;
        } else if (selection.equals("2")) {
            course = eng110;
        } else if (selection.equals("3")) {
            course = math221;
        } else {
            course = math220;
        }
        Task newTask = new Task(description, course, false);
        homework.addTask(newTask);

        System.out.println("Successfully added " + description + "!");
    }


    // EFFECTS: prompts user to select a listed course and returns the selected course
    private void selectCourse() {
        while (!(selection.equals("1") || selection.equals("2")
                || selection.equals("3") || selection.equals("4"))) {
            System.out.println("\nChoose a course:");
            System.out.println("1 = CPSC210");
            System.out.println("2 = ENGL110");
            System.out.println("3 = MATH221");
            System.out.println("4 = MATH220");
            selection = userInput.next();
        }
    }

    // EFFECTS: displays a welcome sign for the selected course and the course's tasks
    private void selectedCourseToView() {
        courses();
        tasks();
        if (selection.equals("1")) {
            System.out.println("Welcome to " + cs210.getCourseName() + "!");
            System.out.println("Your Tasks:");
            System.out.println("- " + quiz.getTaskDescription());
            System.out.println("- " + reading.getTaskDescription());
        } else if (selection.equals("2")) {
            System.out.println("Welcome to " + eng110.getCourseName() + "!");
            System.out.println("Your Tasks: ");
            System.out.println("- " + essay.getTaskDescription());
            System.out.println("- " + poemAnalysis.getTaskDescription());
        } else if (selection.equals("3")) {
            System.out.println("Welcome to " + math221.getCourseName() + "!");
            System.out.println("Your Tasks: ");
            System.out.println("- " + midterm.getTaskDescription());
        } else {
            System.out.println("Welcome to " + math220.getCourseName() + "!");
            System.out.println("Your Tasks: ");
        }
    }


    // EFFECTS: displays the menu of options to the user
    private void displaysOptions() {
        System.out.println("\nChoose an option:");
        System.out.println("\ts -> view courses");
        System.out.println("\t+ -> add a new course");
        System.out.println("\t- -> remove a course");
        System.out.println("\tt -> add new task");
        System.out.println("\tq -> quit application");
    }

    // MODIFIES: this
    // EFFECTS: removes a course
    private void removeCourse() {
        System.out.println("Select a course to remove");
        selectCourse();
        courses();
        if (selection.equals("1")) {
            defaultCourses.removeCourse(cs210);
            System.out.println("Successfully removed" + cs210.getCourseName() + "!");
        } else if (selection.equals("2")) {
            defaultCourses.removeCourse(eng110);
            System.out.println("Successfully removed ENGL110!");
        } else if (selection.equals("3")) {
            defaultCourses.removeCourse(math221);
            System.out.println("Successfully removed MATH221!");
        } else {
            defaultCourses.removeCourse(math220);
            System.out.println("Successfully removed MATH220!");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a course
    private void addCourse() {
        courses();
        System.out.println("Enter Course Name:");
        String code = userInput.next();
        System.out.println("Enter Class Starting Time:");
        double start = userInput.nextDouble();
        System.out.println("Enter Class Ending Time:");
        double end = userInput.nextDouble();
        System.out.println("Professor:");
        String prof = userInput.next();
        String stime = String.valueOf(start);
        String etime = String.valueOf(end);
        Course newCourse = new Course(code, start, end, prof);
        defaultCourses.addCourse(newCourse);

        System.out.println("Successfully added " + code + "!");
        System.out.println("Class starts at " + stime + " and ends at " + etime);
        System.out.println(code + " Professor: " + prof);

    }

}
