package ui;

import exceptions.CourseNotFoundException;
import exceptions.TaskNotFoundException;
import model.CourseList;
import model.Task;
import model.TaskList;

import javax.sound.sampled.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

// represents the tasks page
public class TasksPage extends JFrame implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 650;
    private static final int FONT_SIZE = 30;
    private static final int DIVIDER_POSITION = 380;

    private JLabel header;
    private JTextField addDescription;
    private JTextField addDueDate;
    private JTextField removeTask;
    private JTextField completeTask;
    private JButton home;
    private JSplitPane splitPane;
    private JTextArea leftTextArea;
    private JScrollPane leftPane;
    private JPanel rightPane = new JPanel();
    private TaskList tl;
    private CourseList cl;
    private TaskList ctl;
    private SoundEffect se;

    public TasksPage(TaskList tl, CourseList cl, TaskList ctl) {
        super("My Tasks");
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
        addTaskMenu();
        removeTaskMenu();
        completeTaskMenu();
    }

    public void setUpSplitPane() {
        setUpLeftPane();
        setUpRightPane();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
        splitPane.setDividerLocation(DIVIDER_POSITION);
    }

    public void setUpRightPane() {
        rightPane.setLayout(new FlowLayout());
        rightPane.setBackground(new Color(245, 204, 144));
    }

    public void setUpLeftPane() {
        leftTextArea = new JTextArea();
        leftTextArea.setFont(new Font("courses list", Font.PLAIN, 20));
        leftTextArea.setBackground(Color.white);
        leftTextArea.setForeground(Color.darkGray);
        leftTextArea.setText(printTasks());
        leftTextArea.setEditable(false);
        leftPane = new JScrollPane(leftTextArea);
        leftPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        leftPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void addTaskMenu() {
        addDescription = new JTextField("enter task", 10);
        addDueDate = new JTextField("due date", 5);
        mouseHandlerForAdd();
        JButton confirmAdd = new JButton("Add");
        confirmAdd.setActionCommand("add item");
        confirmAdd.addActionListener(this);
        confirmAdd.setFocusable(false);
        rightPane.add(addDescription);
        rightPane.add(addDueDate);
        rightPane.add(confirmAdd);
        pack();
    }

    private void mouseHandlerForAdd() {
        addDescription.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                if (addDescription.getText().equalsIgnoreCase("enter task")) {
                    addDescription.setText("");
                }
            }
        });
        addDueDate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                if (addDueDate.getText().equalsIgnoreCase("due date")) {
                    addDueDate.setText("");
                }
            }
        });
    }

    private void removeTaskMenu() {
        removeTask = new JTextField("enter task", 10);
        removeTask.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                if (removeTask.getText().equalsIgnoreCase("enter task")) {
                    removeTask.setText("");
                }
            }
        });
        JButton confirmDelete = new JButton("Delete");
        confirmDelete.setActionCommand("delete item");
        confirmDelete.addActionListener(this);
        confirmDelete.setFocusable(false);
        rightPane.add(removeTask);
        rightPane.add(confirmDelete);
        pack();
    }

    private void completeTaskMenu() {
        completeTask = new JTextField("enter task", 10);
        completeTask.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                if (completeTask.getText().equalsIgnoreCase("enter task")) {
                    completeTask.setText("");
                }
            }
        });
        JButton confirmComplete = new JButton("Complete");
        confirmComplete.setActionCommand("complete item");
        confirmComplete.addActionListener(this);
        confirmComplete.setFocusable(false);
        rightPane.add(completeTask);
        rightPane.add(confirmComplete);
        pack();
    }

    private void createHomeButton() {
        home = new JButton("Home");
        home.setFocusable(false);
        home.setActionCommand("Go back to main page");
        home.addActionListener(this);
        home.setBackground(Color.white);
        home.setBounds(WIDTH / 3, 600, 50, 30);
        rightPane.add(home);
    }

    private void createHeader() {
        header = new JLabel("MY TASKS");
        header.setFont(new Font("header", 1, FONT_SIZE));
        //header.setBounds(15, 5, 50, 50);
        add(header);
    }

    public String printTasks() {
        List<Task> tasksList = tl.getTasks();
        String tasks = "";

        for (Task t : tasksList) {
            tasks += "- " + t.getTaskDescription() + "   (Due: " + t.getDueDate() + ") \n";
        }

        return "\n \n" + tasks;
    }

    // Audio is downloaded from: https://www.youtube.com/watch?v=h6_8SlZZwvQ&ab_channel=ChristopherHuppertz
    private void addingAction() {
        se = new SoundEffect();
        String completeSound = "./data/sound/Click.wav";
        se.setFile(completeSound);
        se.playSound();
        tl.addTask(new Task(addDescription.getText(), addDueDate.getText(), false));
        repaint();
        leftTextArea.setText(printTasks());
        addDescription.setText("enter task");
        addDueDate.setText("due date");
    }

    // Audio is downloaded from: https://www.youtube.com/watch?v=LIgwJQo8IuA&ab_channel=SoundLibrary
    private void deletingAction() {
        se = new SoundEffect();
        String completeSound = "./data/sound/Delete.wav";
        try {
            tl.removeTask(tl.getTask(removeTask.getText()));
        } catch (TaskNotFoundException taskNotFoundException) {
            taskNotFoundException.printStackTrace();
        }
        se.setFile(completeSound);
        se.playSound();
        repaint();
        leftTextArea.setText(printTasks());
        removeTask.setText("enter task");
    }

    // Audio is downloaded from: https://www.youtube.com/watch?v=GVAF07-2Xic&ab_channel=GamingSoundFX
    private void completingAction() {
        se = new SoundEffect();
        String completeSound = "./data/sound/Ding.wav";
        try {
            ctl.addTask(tl.getTask(completeTask.getText()));
            tl.removeTask(tl.getTask(completeTask.getText()));
        } catch (TaskNotFoundException taskNotFoundException) {
            taskNotFoundException.printStackTrace();
        }
        se.setFile(completeSound);
        se.playSound();
        repaint();
        leftTextArea.setText(printTasks());
        completeTask.setText("enter task");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Go back to main page")) {
            dispose();
        } else if (e.getActionCommand().equals("add item")) {
            addingAction();
        } else if (e.getActionCommand().equals("delete item")) {
            deletingAction();
        } else if (e.getActionCommand().equals("complete item")) {
            completingAction();
        }
    }
}
