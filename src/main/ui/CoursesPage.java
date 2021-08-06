package ui;

import exceptions.CourseNotFoundException;
import model.Course;
import model.CourseList;
import model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CoursesPage extends JFrame implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 650;
    private static final int FONT_SIZE = 30;
    private static final int DIVIDER_POSITION = 380;

    private JLabel header;
    private JTextField remove;
    private JTextField addName;
    private JTextField addSTime;
    private JTextField addETime;
    private JTextField addProf;
    private JButton home;
    private JButton addCourse;
    private JButton removeCourse;
    private JSplitPane splitPane;
    private JScrollPane leftPane = new JScrollPane();
    private JTextArea leftTextArea;
    private JPanel rightPane = new JPanel();
    private TaskList tl;
    private CourseList cl;
    private TaskList ctl;

    public CoursesPage(TaskList tl, CourseList cl, TaskList ctl) {
        this.tl = tl;
        this.cl = cl;
        this.ctl = ctl;
        setUpSplitPane();

        createHeader();
        addAllButtonsToPage();
        add(splitPane);

        setVisible(true);
        setResizable(false);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 209, 209));
        pack();
    }

    public void addAllButtonsToPage() {
        createHomeButton();
        addCourseMenu();
        removeCourseMenu();
    }

    public void setUpSplitPane() {
        setUpLeftPane();
        setUpRightPane();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
        splitPane.setDividerLocation(DIVIDER_POSITION);
    }

    public void setUpRightPane() {
        rightPane.setLayout(new FlowLayout());
        rightPane.setBackground(new Color(255, 212, 213));
    }

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

    private void addCourseMenu() {
        addName = new JTextField(10);
        addSTime = new JTextField(5);
        addETime = new JTextField(5);
        addProf = new JTextField(10);
        JButton confirmAdd = new JButton("Add");
        confirmAdd.setActionCommand("add item");
        confirmAdd.addActionListener(this);
        confirmAdd.setFocusable(false);
        rightPane.add(addName);
        rightPane.add(addSTime);
        rightPane.add(addETime);
        rightPane.add(addProf);
        rightPane.add(confirmAdd);
        pack();
    }

    private void removeCourseMenu() {
        remove = new JTextField(10);
        JButton confirmDelete = new JButton("Delete");
        confirmDelete.setActionCommand("delete item");
        confirmDelete.addActionListener(this);
        confirmDelete.setFocusable(false);
        rightPane.add(remove);
        rightPane.add(confirmDelete);
        pack();
    }

    private void createHomeButton() {
        home = new JButton("Home");
        home.setFocusable(false);
        home.setActionCommand("Go back to main page");
        home.addActionListener(this);
        home.setBackground(Color.white);
        rightPane.add(home);
    }

    private void createHeader() {
        header = new JLabel("COURSES");
        header.setFont(new Font("header",1,FONT_SIZE));
        //header.setBounds(15, 5, 50, 50);
        add(header);
    }

    public String printCourses() {
        List<Course> courseList = cl.getCourses();
        String courses = "";

        for (Course c : courseList) {
            courses += c.getCode() + "   (" + c.getStartTime() + " - " + c.getEndTime()
                    + " Prof: " + c.getProfessor() + ")" + "\n";
        }

        return "\n \n" + courses;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Go back to main page")) {
            dispose();
            new MainScreen(tl, cl, ctl);
        } else if (e.getActionCommand().equals("add item")) {
            cl.addCourse(new Course(addName.getText(), addSTime.getText(), addETime.getText(), addProf.getText()));
            repaint();
            leftTextArea.setText(printCourses());
            addName.setText("");
            addSTime.setText("");
            addETime.setText("");
            addProf.setText("");
        } else if (e.getActionCommand().equals("delete item")) {
            try {
                cl.removeCourse(cl.getCourse(remove.getText()));
            } catch (CourseNotFoundException courseNotFoundException) {
                courseNotFoundException.printStackTrace();
            }
            repaint();
            leftTextArea.setText(printCourses());
            remove.setText("");
        }
    }
}




