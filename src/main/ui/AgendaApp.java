package ui;

import model.Course;
import model.CourseList;
import model.Task;

import java.util.Scanner;

// Course tasks application
public class AgendaApp {

    private Task quiz;
    private Task reading;
    private Course cs210;
    private Course eng110;
    private Course math221;
    private Course math220;
    private Scanner userInput;

    // EFFECTS: runs the application
    public AgendaApp() {
        runProgram();
    }

    private void runProgram() {
        userInput = new Scanner(System.in);
        boolean running = true;
        String input = null;

        while (running) {
            displaysOptions();
            input = userInput.next();

            if (input.equals("s")) {
                selectCourse();
            } else if (input.equals("+")) {
                addCourse();
            } else if (input.equals("-")) {
                removeCourse();
            } else if (input.equals("q")) {
                running = false;
            }
        }

        System.out.println("See you again!");
    }



    private void removeCourse() {
    }

    private void addCourse() {
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
        System.out.println("Successfully added " + code + "!");
        System.out.println("Class starts at " + stime + " and ends at " + etime);
        System.out.println(code + " Professor: " + prof);

    }

    // MODIFIES: this
    // EFFECTS: initializes the courses
    private void courses() {
        cs210 = new Course("CPSC210", 9.5, 1, "Felix Grund");
        eng110 = new Course("ENGL110", 12, 3.5, "Rebecca Sheppard");
        math221 = new Course("MATH221", 9.5, 1, "TBD");
        math220 = new Course("MATH220", 9.5, 1, "TBD");
    }

    // EFFECTS: displays the menu of options to the user
    private void displaysOptions() {
        System.out.println("\nChoose an option:");
        System.out.println("\ts -> view courses");
        System.out.println("\t+ -> add a new course");
        System.out.println("\t- -> remove a course");
        System.out.println("\tq -> quit application");
    }


    // EFFECTS: prompts user to select a listed course and returns the selected course
    private void selectCourse() {
        String courseToView = "";

        while (!(courseToView.equals("1") || courseToView.equals("2")
                || courseToView.equals("3") || courseToView.equals("4"))) {
            System.out.println("\nChoose a course to view:");
            System.out.println("1 = CPSC210");
            System.out.println("2 = ENGL110");
            System.out.println("3 = MATH221");
            System.out.println("4 = MATH220");
            courseToView = userInput.next();
        }

        if (courseToView.equals("1")) {
            System.out.println("Welcome to CPSC210!");
        //    return cs210;
        } else if (courseToView.equals("2")) {
            System.out.println("Welcome to ENGL110!");
        //    return eng110;
        } else if (courseToView.equals("3")) {
            System.out.println("Welcome to MATH221!");
        //    return math221;
        } else {
            System.out.println("Welcome to MATH220!");
           // return math220;
        }
    }

}
