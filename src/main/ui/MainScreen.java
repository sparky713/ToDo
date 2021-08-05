package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame implements ActionListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 650;
    private static int BUTTON = WIDTH / 2 - 20;
    private static int GAP_FROM_TOP = 70;
    private static int BUTTON_GAP = 10;
    private JButton viewCourses;
    private JButton viewTasks;
    private JButton viewCompletedTasks;
    private JFrame frame;

    // MODIFIES: this
    // EFFECTS: creates JFrame window for the application with buttons and a label
    public MainScreen() {
        super("My Agenda");

        setVisible(true);
        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(true);
        setLayout(null);
        getContentPane().setBackground(Color.white); // BG COLOR
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLabel welcome = new JLabel("WELCOME");
        welcome.setFont(new Font("Header", 1, 30));
        welcome.setBounds(12, 10, 200, 50);
        add(welcome);
        buttons();
    }

    // MODIFIES: this
    // EFFECTS: creates the view tools, courses, and completed tasks buttons
    public void buttons() {
        // COURSES
        viewCourses = new JButton("COURSES");
        viewCourses.setBounds(10, GAP_FROM_TOP, BUTTON, BUTTON);
        viewCourses.setBackground(Color.pink);
        viewCourses.setFocusable(false);
        viewCourses.setActionCommand("View Courses");
        viewCourses.addActionListener(this);
        add(viewCourses);
        // TASKS
        viewTasks = new JButton("TASKS");
        viewTasks.setBounds(BUTTON + BUTTON_GAP * 2, GAP_FROM_TOP, BUTTON, BUTTON);
        viewTasks.setBackground(Color.orange);
        viewTasks.setFocusable(false);
        viewTasks.setActionCommand("View Tasks");
        viewTasks.addActionListener(this);
        add(viewTasks);
        // COMPLETED TASKS
        viewCompletedTasks = new JButton("COMPLETED TASKS");
        viewCompletedTasks.setBounds(10, GAP_FROM_TOP + BUTTON_GAP + BUTTON,
                BUTTON * 2 + 10, BUTTON);
        viewCompletedTasks.setBackground(new Color(137, 207, 240));
        viewCompletedTasks.setFocusable(false);
        viewCompletedTasks.setActionCommand("View Completed Tasks");
        viewCompletedTasks.addActionListener(this);
        add(viewCompletedTasks);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View Courses")) {
            dispose();
            //CoursePage coursePage = new CoursePage();

        } else if (e.getActionCommand().equals("View Tasks")) {
            // TODO add a tasks page
            System.out.println("tasks");
        } else if (e.getActionCommand().equals("View Completed Tasks")) {
            // TODO add a ct page
            System.out.println("ct");
        }
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}
