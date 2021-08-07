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
    private static final int DIVIDER_POSITION = 380;

    private JLabel header;
    private JTextField remove;
    private JTextField addName;
    private JTextField addSTime;
    private JTextField addETime;
    private JTextField addProf;
    private JButton home;
    private JSplitPane splitPane;
    private JScrollPane leftPane = new JScrollPane();
    private JTextArea leftTextArea;
    private JPanel rightPane = new JPanel();
    private TaskList tl;
    private CourseList cl;
    private TaskList ctl;
    private SoundEffect se;

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
        addName = new JTextField("enter course", 10);
        addSTime = new JTextField("start time", 5);
        addETime = new JTextField("end time", 5);
        addProf = new JTextField("professor", 10);
        mouseHandlerForAdd();
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

    private void mouseHandlerForAdd() {
        addNameME();
        addSTimeME();
        addETimeME();
        addProfME();
    }


    private void removeCourseMenu() {
        remove = new JTextField("enter course", 10);
        remove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                if (remove.getText().equalsIgnoreCase("enter course")) {
                    remove.setText("");
                }
            }
        });
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
        header = new JLabel("MY COURSES");
        header.setFont(new Font("header", 1, FONT_SIZE));
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

    public void resetTextField() {
        addName.setText("enter course");
        addSTime.setText("start time");
        addETime.setText("end time");
        addProf.setText("professor");
    }

    // Audio is downloaded from: https://www.youtube.com/watch?v=h6_8SlZZwvQ&ab_channel=ChristopherHuppertz
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
    public void deleteHelper() {
        se = new SoundEffect();
        String completeSound = "./data/sound/Delete.wav";
        try {
            cl.removeCourse(cl.getCourse(remove.getText()));
        } catch (CourseNotFoundException courseNotFoundException) {
            courseNotFoundException.printStackTrace();
        }
        se.setFile(completeSound);
        se.playSound();
        repaint();
        leftTextArea.setText(printCourses());
        remove.setText("enter course");
    }

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




