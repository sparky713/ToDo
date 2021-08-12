package ui;

import model.CourseList;
import model.TaskList;
import persistence.CourseReader;
import persistence.CourseWriter;
import persistence.TaskReader;
import persistence.TaskWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

// Agenda Application
public class AgendaAppGUI extends JFrame implements ActionListener {

    private static final String JSON_TASKS = "./data/tasks.json";
    private static final String JSON_COURSES = "./data/courses.json";
    private static final String JSON_COMPLETED_TASKS = "./data/completed.json";
    private static final int WIDTH = 400;
    private static final int HEIGHT = 650;
    private static int BUTTON = WIDTH / 2 - 20;
    private static int GAP_FROM_TOP = 70;
    private static int BUTTON_GAP = 10;
    private static final int FONT_SIZE = 30;


    private JButton viewCourses;
    private JButton viewTasks;
    private JButton viewCompletedTasks;
    private JButton viewStatus;
    private JButton save;
    private JLabel welcome;
    private TaskList myTasks;
    private TaskList completedTasks;
    private CourseList myCourses;
    private TaskWriter jsonTasksWriter;
    private TaskWriter jsonCompletedTasksWriter;
    private CourseWriter jsonCoursesWriter;
    private TaskReader jsonTasksReader;
    private TaskReader jsonCompletedTasksReader;
    private CourseReader jsonCoursesReader;
    private DateTimeFormatter loadTimeFormat;
    private LocalDateTime loadLocalTime;
    private String loadTime;
    private DateTimeFormatter saveTimeFormat;
    private LocalDateTime saveLocalTime;
    private String saveTime;

    // MODIFIES: this
    // EFFECTS: runs the application and creates JFrame window for the application with buttons and a label
    public AgendaAppGUI() throws FileNotFoundException {
        super("My Agenda");
        initialize();
        loadData();
        saveLoadTime();
        setVisible(true);
        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Color.white); // BG COLOR

        addHeader();
        addButtons();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: loads my courses, tasks, and completed tasks from file
    public void loadData() {
        loadCourses();
        loadTasks();
        loadCompletedTasks();
    }

    // EFFECTS: saves the time application was opened/when data is loaded from file
    public void saveLoadTime() {
        loadTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        loadLocalTime = LocalDateTime.now();
        loadTime = loadTimeFormat.format(loadLocalTime);
    }

    // EFFECTS: initializes lists, JSon readers and writers
    public void initialize() {
        myCourses = new CourseList();
        myTasks = new TaskList();
        completedTasks = new TaskList();
        jsonTasksWriter = new TaskWriter(JSON_TASKS);
        jsonTasksReader = new TaskReader(JSON_TASKS);
        jsonCoursesWriter = new CourseWriter(JSON_COURSES);
        jsonCoursesReader = new CourseReader(JSON_COURSES);
        jsonCompletedTasksWriter = new TaskWriter(JSON_COMPLETED_TASKS);
        jsonCompletedTasksReader = new TaskReader(JSON_COMPLETED_TASKS);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds a JLabel to the Main page
    public void addHeader() {
        welcome = new JLabel("WELCOME!");
        welcome.setFont(new Font("Header", 1, FONT_SIZE));
        welcome.setBounds(12, 10, 200, 50);
        add(welcome);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds the save, check loading status, view tools, courses, and completed tasks
    // buttons to main page
    public void addButtons() {
        coursesButton();
        tasksButton();
        completedTasksButton();
        saveButton();
        loadingStatusButton();
    }

    // MODIFIES: this
    // EFFECTS: creates and adds a save button to the main page that saves all changes to file, shows a popup pane to
    // confirm the save
    public void saveButton() {
        save = new JButton("SAVE CHANGES");
        save.setBounds(10, HEIGHT - 75, BUTTON - 30, 30);
        save.setFocusable(false);
        save.setActionCommand("Save");
        save.addActionListener(this);
        save.setBorder(BorderFactory.createRaisedBevelBorder());
        add(save);
    }

    // MODIFIES: this
    // EFFECTS: creates a button to open the loading status page
    public void loadingStatusButton() {
        viewStatus = new JButton("LOADING STATUS");
        viewStatus.setBounds(WIDTH - (BUTTON - 10), HEIGHT - 75,
                BUTTON - 30, 30);
        viewStatus.setFocusable(false);
        viewStatus.setActionCommand("View Status");
        viewStatus.addActionListener(this);
        viewStatus.setBorder(BorderFactory.createRaisedBevelBorder());
        add(viewStatus);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds the view courses button which leads to the courses page to the main screen
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
    // EFFECTS: creates and adds the view tasks button which leads to the tasks page to the main screen
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
    // EFFECTS: creates and adds the view completed tasks button which leads to the completed tasks page
    // to the main screen
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
    //          opens new pages corresponding to the buttons or saves changes to file
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View Courses")) {
            new CoursesPage(myCourses);

        } else if (e.getActionCommand().equals("View Tasks")) {
            new TasksPage(myTasks, completedTasks);

        } else if (e.getActionCommand().equals("View Completed Tasks")) {
            new CompletedTasksPage(completedTasks);

        } else if (e.getActionCommand().equals("View Status")) {
            new StatusPage(loadTime);

        } else if (e.getActionCommand().equals("Save")) {
            saveCourses();
            saveTasks();
            saveCompletedTasks();
            saveSavedTime();
            JOptionPane.showMessageDialog(this,
                    "Saved Changes at: " + saveTime);
        }
    }

    // EFFECTS: stores the time at which the save button is clicked as a String
    public void saveSavedTime() {
        saveTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        saveLocalTime = LocalDateTime.now();
        saveTime = saveTimeFormat.format(saveLocalTime);
    }


    // MODIFIES: this
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

    // MODIFIES: this
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

    // MODIFIES: this
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



