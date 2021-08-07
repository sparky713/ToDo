package ui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

// contains and represents data related to the state of persistence of the AgendaApp
public class StatusPage extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;
    private static final String JSON_TASKS = "./data/tasks.json";
    private static final String JSON_COURSES = "./data/courses.json";
    private static final String JSON_COMPLETED_TASKS = "./data/completed.json";

    private JTextArea statusText;
    private String loadTime;

    // EFFECTS: constructs a new StatusPage JFrame with the load and save times
    public StatusPage(String loadTime) {
        super("Status");
        this.loadTime = loadTime;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setUpTextArea();
        setVisible(true);
        pack();
    }

    // MODIFIES: this
    // EFFECTS: makes and adds the JTextArea for this window
    public void setUpTextArea() {
        statusText = new JTextArea();
        statusText.setFont(new Font("status", Font.PLAIN, 15));
        statusText.setBackground(Color.white);
        statusText.setForeground(Color.darkGray);
        statusText.setText(printStatus());
        statusText.setEditable(false);
        add(statusText);
    }


    // EFFECTS: returns the time that data was loaded and saved as a String
    private String printStatus() {

        return "Loaded my tasks from " + JSON_TASKS + " at " + loadTime + "\n"
                + "Loaded my courses from " + JSON_COURSES + " at " + loadTime + "\n"
                + "Loaded my completed tasks from " + JSON_COMPLETED_TASKS + " at " + loadTime;
    }
}
