package ui;

import model.CourseList;
import model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class MainScreen extends JFrame implements ActionListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 650;
    private static int BUTTON = WIDTH / 2 - 20;
    private static int GAP_FROM_TOP = 70;
    private static int BUTTON_GAP = 10;
    private static final int FONT_SIZE = 30;

    private JButton viewCourses;
    private JButton viewTasks;
    private JButton viewCompletedTasks;
    private TaskList tl;
    private CourseList cl;
    private TaskList ctl;

    // MODIFIES: this
    // EFFECTS: creates JFrame window for the application with buttons and a label
    public MainScreen(TaskList tl, CourseList cl, TaskList ctl) {
        super("My Agenda");
        this.tl = tl;
        this.cl = cl;
        this.ctl = ctl;
        setVisible(true);
        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Color.white); // BG COLOR
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addHeader();
        addButtons();
    }

    // MODIFIES: this
    // EFFECTS: creates and adds a JLabel to main screen
    public void addHeader() {
        JLabel welcome = new JLabel("WELCOME");
        welcome.setFont(new Font("Header", 1, FONT_SIZE));
        welcome.setBounds(12, 10, 200, 50);
        add(welcome);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds the view tools, courses, and completed tasks buttons to main screen
    public void addButtons() {
        coursesButton();
        tasksButton();
        completedTasksButton();
    }

    // MODIFIES: this
    // EFFECTS: creates and adds the view courses button to main screen
    public void coursesButton() {
        viewCourses = new JButton("COURSES");
        viewCourses.setBounds(10, GAP_FROM_TOP, BUTTON, BUTTON);
        viewCourses.setBackground(Color.pink);
        viewCourses.setFocusable(false);
        viewCourses.setActionCommand("View Courses");
        viewCourses.addActionListener(this);
        viewCourses.setBorder(BorderFactory.createEmptyBorder());
        add(viewCourses);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds the view tasks button to main screen
    public void tasksButton() {
        viewTasks = new JButton("TASKS");
        viewTasks.setBounds(BUTTON + BUTTON_GAP * 2, GAP_FROM_TOP, BUTTON, BUTTON);
        viewTasks.setBackground(Color.orange);
        viewTasks.setFocusable(false);
        viewTasks.setActionCommand("View Tasks");
        viewTasks.addActionListener(this);
        viewTasks.setBorder(BorderFactory.createEmptyBorder());
        add(viewTasks);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds the view completed tasks button to main screen
    public void completedTasksButton() {
        viewCompletedTasks = new JButton("COMPLETED TASKS");
        viewCompletedTasks.setBounds(10, GAP_FROM_TOP + BUTTON_GAP + BUTTON,
                BUTTON * 2 + 10, BUTTON);
        viewCompletedTasks.setBackground(new Color(137, 207, 240));
        viewCompletedTasks.setFocusable(false);
        viewCompletedTasks.setActionCommand("View Completed Tasks");
        viewCompletedTasks.addActionListener(this);
        viewCompletedTasks.setBorder(BorderFactory.createEmptyBorder());
        add(viewCompletedTasks);
    }

    // MODIFIES: this
    // EFFECTS: handles action event when buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View Courses")) {
            new CoursesPage(tl, cl, ctl);

        } else if (e.getActionCommand().equals("View Tasks")) {
            new TasksPage(tl, cl, ctl);

        } else if (e.getActionCommand().equals("View Completed Tasks")) {
            new CompletedTasksPage(tl, cl, ctl);
        }
    }
}
