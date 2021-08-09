package ui;

import model.CourseList;
import model.Task;
import model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// represents the completed tasks page, a page with a list of the completed tasks
public class CompletedTasksPage extends JFrame implements ActionListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 650;
    private static final int FONT = 30;

    private JTextArea textArea;
    private JLabel header;
    private JScrollPane scroll;
    private TaskList tl;
    private CourseList cl;
    private TaskList ctl;
    private Dimension textBox = new Dimension(WIDTH - 70, HEIGHT - 150);
    private JButton home;

    // EFFECTS: constructs a new CompletedTasksPage with a JTextArea and additional features
    public CompletedTasksPage(TaskList tl, CourseList cl, TaskList ctl) {
        super("Completed Tasks");
        this.ctl = ctl;
        this.cl = cl;
        this.tl = tl;
        getContentPane().setBackground(new Color(203, 240, 255));
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        createHeader();
        setUpTextArea();
        setUpScrollPane();
        createHomeButton();
        pack();
    }

    // MODIFIES: this
    // EFFECTS: sets the text, its font, and color for the non editable text area
    public void setUpTextArea() {
        textArea = new JTextArea();
        textArea.setFont(new Font("completed list", Font.PLAIN, 20));
        textArea.setBackground(Color.white);
        textArea.setForeground(Color.darkGray);
        textArea.setText(printCompletedTasks());
        textArea.setEditable(false);
    }

    // MODIFIES: this
    // EFFECTS: creates a scroll pane, adds JTextArea, textBox, to scroll pane and adds the scroll pane
    // to this
    public void setUpScrollPane() {
        scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(textBox);
        getContentPane().add(scroll);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds the header for the completed tasks page
    public void createHeader() {
        header = new JLabel("COMPLETED TASKS");
        header.setFont(new Font("header", 1, FONT));
        add(header);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds the button to return to the main page on the completed tasks page
    public void createHomeButton() {
        home = new JButton("Home");
        home.setFocusable(false);
        home.setActionCommand("Go back to main page");
        home.addActionListener(this);
        home.setBounds(WIDTH - 50, HEIGHT - 30, 35, 10);
        add(home);
    }

    // EFFECTS: returns a string of all tasks in the completed tasks list with their due dates in brackets
    public String printCompletedTasks() {
        List<Task> completedList = ctl.getTasks();
        String completedTasks = "";

        for (Task c : completedList) {
            completedTasks += "- " + c.getTaskDescription() + "   (Was Due: " + c.getDueDate() + ") \n";
        }

        return completedTasks;
    }

    // MODIFIES: this
    // EFFECTS: handles events for the home button, closes the current page
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Go back to main page")) {
            dispose();
        }
    }
}
