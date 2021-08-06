package ui;

import model.CourseList;
import model.Task;
import model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TasksPage extends JFrame implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 650;
    private static final int FONT_SIZE = 30;
    private static final int DIVIDER_POSITION = 380;

    private JLabel header;
    private JButton home;
    private JSplitPane splitPane;
    private JTextArea leftTextArea;
    private JScrollPane leftPane;
    private JPanel rightPane = new JPanel();
    private TaskList tl;
    private CourseList cl;
    private TaskList ctl;

    public TasksPage(TaskList tl, CourseList cl, TaskList ctl) {
        super("My Tasks");
        this.tl = tl;
        this.cl = cl;
        this.ctl = ctl;

        setUpLeftPane();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
        splitPane.setDividerLocation(DIVIDER_POSITION);
        rightPane.setLayout(new BorderLayout());
        rightPane.setBackground(new Color(255, 210, 141));
        add(splitPane);
        setVisible(true);
        setResizable(false);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 209, 209));
        pack();

        createHeader();
        createHomeButton();

    }

    public void setUpLeftPane() {
        leftTextArea = new JTextArea();
        leftTextArea.setFont(new Font("tasks list", Font.PLAIN, 20));
        leftTextArea.setBackground(Color.white);
        leftTextArea.setForeground(Color.darkGray);
        leftTextArea.setText(printTasks());
        leftTextArea.setEditable(false);
        leftPane = new JScrollPane(leftTextArea);
        leftPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        leftPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void createHomeButton() {
        home = new JButton("Home");
        home.setFocusable(false);
        home.setActionCommand("Go back to main page");
        home.addActionListener(this);
        home.setBackground(Color.white);
        rightPane.add(home, BorderLayout.PAGE_END);
    }

    private void createHeader() {
        header = new JLabel("TASKS");
        header.setFont(new Font("header",1,FONT_SIZE));
        header.setBounds(10, 10, 50, 50);
        add(header);
    }

    public String printTasks() {
        List<Task> tasksList = tl.getTasks();
        String tasks = "";

        for (Task t : tasksList) {
            tasks += "- " + t.getTaskDescription() + "   (Due: " + t.getDueDate() + ") \n";
        }

        return "\n" + tasks;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Go back to main page")) {
            dispose();
            new MainScreen(tl, cl, ctl);
        }
    }
}
