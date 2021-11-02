package ui;

import exceptions.CourseNotFoundException;
import model.Course;
import model.CourseList;
import model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

// represents the courses page
public class CoursesPage extends JFrame implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 650;
    private static final int FONT_SIZE = 30;
    private static final int SUB_FONT = 15;
    private static final int DIVIDER_POSITION = 380;
    private static final int RIGHT_PANE_WIDTH = 50;
    private static final int RIGHT_PANE_HEIGHT = 650;
    private static final int BUTTON_X_POS = RIGHT_PANE_WIDTH / 3 + 7;

    private JLabel header;
    private JLabel addCourseHeader;
    private JLabel removeCourseHeader;
    private JTextField remove;
    private JTextField addName;
    private JTextField addSTime;
    private JTextField addETime;
    private JTextField addProf;
    private JButton home;
    private JButton confirmAdd;
    private JButton confirmDelete;
    private JSplitPane splitPane;
    private JScrollPane leftPane = new JScrollPane();
    private JTextArea leftTextArea;
    private JPanel rightPane = new JPanel();
    private CourseList cl;
    private SoundEffect se;

    // EFFECTS: constructs a new CoursesPage with a split pane and additional features
    public CoursesPage(CourseList cl) {
        this.cl = cl;
        setUpSplitPane();

        createHeader();
        addAllButtonsToPage();
        add(splitPane);

        setVisible(true);
        setResizable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

    // MODIFIES: this
    // EFFECTS: creates and adds all the buttons on the courses page
    public void addAllButtonsToPage() {
        createHomeButton();
        addCourseMenu();
        removeCourseMenu();
    }

    // MODIFIES: this
    // EFFECTS: sets up the split pane
    public void setUpSplitPane() {
        setUpLeftPane();
        setUpRightPane();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
        splitPane.setDividerLocation(DIVIDER_POSITION);
    }

    // MODIFIES: this
    // EFFECTS: sets the layout and background color of the right pane
    public void setUpRightPane() {
        rightPane.setLayout(null);
        rightPane.setBackground(new Color(255, 212, 213));
    }

    // MODIFIES: this
    // EFFECTS: instantiates the text area and scroll pane of the left pane
    public void setUpLeftPane() {
        leftTextArea = new JTextArea();
        leftTextArea.setFont(new Font("courses list", Font.PLAIN, 20));
        leftTextArea.setBackground(Color.white);
        leftTextArea.setForeground(Color.darkGray);
        leftTextArea.setText(printCourses());
        leftTextArea.setEditable(false);
        leftPane = new JScrollPane(leftTextArea);
        leftPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        leftPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    // MODIFIES: this
    // EFFECTS: makes and adds the button and text fields to add a new course
    private void addCourseMenu() {
        makeTextFieldsForAdd();
        makeButtons();
        rightPane.add(addName);
        rightPane.add(addSTime);
        rightPane.add(addETime);
        rightPane.add(addProf);
        rightPane.add(confirmAdd);
    }

    // EFFECTS: makes a new add JButton for adding a new course
    private void makeButtons() {
        // Add Button
        confirmAdd = new JButton("Add");
        confirmAdd.setBounds(BUTTON_X_POS, 145, 150, 30);
        confirmAdd.setBackground(Color.white);
        confirmAdd.setActionCommand("add item");
        confirmAdd.addActionListener(this);
        confirmAdd.setFocusable(false);
        // Remove Button
        confirmDelete = new JButton("Delete");
        confirmDelete.setBounds(BUTTON_X_POS, 265, 150, 30);
        confirmDelete.setBackground(Color.white);
        confirmDelete.setActionCommand("delete item");
        confirmDelete.addActionListener(this);
        confirmDelete.setFocusable(false);
    }

    // EFFECTS: adds and handles a mouse event for the addName text field
    private void addNameME() {
        addName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                if (addName.getText().equalsIgnoreCase("enter course")) {
                    addName.setText("");
                }
            }
        });
    }

    // EFFECTS: adds and handles a mouse event for the addSTime text field
    private void addSTimeME() {
        addSTime.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                if (addSTime.getText().equalsIgnoreCase("start time")) {
                    addSTime.setText("");
                }
            }
        });
    }

    // EFFECTS: adds and handles a mouse event for the addETime text field
    private void addETimeME() {
        addETime.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                if (addETime.getText().equalsIgnoreCase("end time")) {
                    addETime.setText("");
                }
            }
        });
    }

    // EFFECTS: adds and handles a mouse event for the addProf text field
    private void addProfME() {
        addProf.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                if (addProf.getText().equalsIgnoreCase("professor")) {
                    addProf.setText("");
                }
            }
        });
    }

    // EFFECTS: helper for instantiating the addTask text fields and adds its mouse events
    private void makeTextFieldsForAdd() {
        addName = new JTextField("enter course", 10);
        addName.setBounds(BUTTON_X_POS, 50, 150, 25);
        addSTime = new JTextField("start time", 5);
        addSTime.setBounds(BUTTON_X_POS, 80, 70, 25);
        addETime = new JTextField("end time", 5);
        addETime.setBounds(BUTTON_X_POS + 80, 80, 70, 25);
        addProf = new JTextField("professor", 10);
        addProf.setBounds(BUTTON_X_POS, 110, 150, 25);
        addNameME();
        addSTimeME();
        addETimeME();
        addProfME();
    }

    // MODIFIES: this
    // EFFECTS: makes the button and text field to remove a course. Text Field has an associated mouse event
    private void removeCourseMenu() {
        remove = new JTextField("enter course", 10);
        remove.setBounds(BUTTON_X_POS, 230, 150, 25);
        remove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                if (remove.getText().equalsIgnoreCase("enter course")) {
                    remove.setText("");
                }
            }
        });
        makeButtons();
        rightPane.add(remove);
        rightPane.add(confirmDelete);
    }

    // MODIFIES: this
    // EFFECTS: creates the button to return to the main page and adds it to the Courses page
    private void createHomeButton() {
        home = new JButton("Home");
        home.setFocusable(false);
        home.setActionCommand("Go back to main page");
        home.addActionListener(this);
        home.setBounds(BUTTON_X_POS, RIGHT_PANE_HEIGHT - 80, 150, 30);
        home.setBorder(BorderFactory.createRaisedBevelBorder());
        rightPane.add(home);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds all the JLabel headers on the courses page
    private void createHeader() {
        header = new JLabel("MY COURSES");
        header.setFont(new Font("header", 1, FONT_SIZE));
        header.setBounds(10, 10, 300, 30);
        add(header);
        addCourseHeader = new JLabel("ADD A COURSE: ");
        addCourseHeader.setFont(new Font("options", 1, SUB_FONT));
        addCourseHeader.setBounds(10, 20, 200, 30);
        rightPane.add(addCourseHeader);
        removeCourseHeader = new JLabel("REMOVE COURSE: ");
        removeCourseHeader.setFont(new Font("options", 1, SUB_FONT));
        removeCourseHeader.setBounds(10, 200, 200, 30);
        rightPane.add(removeCourseHeader);
    }

    // EFFECTS: returns a string of all courses in the courses list with their start time, end time, and
    // professor in brackets
    public String printCourses() {
        String courses = "";

        for (Course c : cl) {
            courses += c.getCode() + "   (" + c.getStartTime() + " - " + c.getEndTime()
                    + " Prof: " + c.getProfessor() + ")" + "\n";
        }

        return "\n \n" + courses;
    }

    // MODIFIES: this
    // EFFECTS: resets the text of the text fields to their default text
    public void resetTextField() {
        addName.setText("enter course");
        addSTime.setText("start time");
        addETime.setText("end time");
        addProf.setText("professor");
    }

    // Audio is downloaded from: https://www.youtube.com/watch?v=h6_8SlZZwvQ&ab_channel=ChristopherHuppertz
    // MODIFIES: this
    // EFFECTS: helper for adding a course. Adds new course to current courses list. Plays audio on
    // click and reflects changes on page.
    public void addHelper() {
        cl.addCourse(new Course(addName.getText(), addSTime.getText(), addETime.getText(), addProf.getText()));
        se = new SoundEffect();
        String completeSound = "./data/sound/Click.wav";
        se.setFile(completeSound);
        se.playSound();
        repaint();
        leftTextArea.setText(printCourses());
        resetTextField();
    }

    // Audio is downloaded from: https://www.youtube.com/watch?v=LIgwJQo8IuA&ab_channel=SoundLibrary
    // MODIFIES: this
    // EFFECTS: helper for deleting the course. Removes course from current courses list. Plays audio on
    // click and reflects changes on page.
    public void deleteHelper() {
        se = new SoundEffect();
        String completeSound = "./data/sound/Delete.wav";
        String error = "./data/sound/Error.wav";
        try {
            cl.removeCourse(cl.getCourse(remove.getText()));
            se.setFile(completeSound);
            se.playSound();
        } catch (CourseNotFoundException courseNotFoundException) {
            System.out.println("That Course does not exist!");
            se.setFile(error);
            se.playSound();
        }

        repaint();
        leftTextArea.setText(printCourses());
        remove.setText("enter course");
    }

    // MODIFIES: this
    // EFFECTS: handles events for the home, add, and delete buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Go back to main page")) {
            dispose();

        } else if (e.getActionCommand().equals("add item")) {
            addHelper();

        } else if (e.getActionCommand().equals("delete item")) {
            deleteHelper();
        }
    }
}




