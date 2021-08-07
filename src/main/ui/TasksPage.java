package ui;

import exceptions.TaskNotFoundException;
import model.CourseList;
import model.Task;
import model.TaskList;

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
    private static final int SUB_FONT = 15;
    private static final int DIVIDER_POSITION = 380;
    private static final int RIGHT_PANE_WIDTH = 50;
    private static final int RIGHT_PANE_HEIGHT = 650;
    private static final int BUTTON_X_POS = RIGHT_PANE_WIDTH / 3 + 7;

    private JLabel header;
    private JLabel addTaskHeader;
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
    private JLabel removeTaskHeader;
    private JLabel completeTaskHeader;

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
        rightPane.setLayout(null);
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
        makeTextFields();
        mouseHandlerForAdd();
        JButton confirmAdd = new JButton("Add");
        confirmAdd.setBounds(BUTTON_X_POS, 115, 150, 30);
        confirmAdd.setBackground(Color.white);
        confirmAdd.setActionCommand("add item");
        confirmAdd.addActionListener(this);
        confirmAdd.setFocusable(false);
        rightPane.add(addDescription);
        rightPane.add(addDueDate);
        rightPane.add(confirmAdd);
        pack();
    }

    private void makeTextFields() {
        addDescription = new JTextField("enter task");
        addDescription.setBounds(BUTTON_X_POS, 50, 150, 25);
        addDueDate = new JTextField("due date");
        addDueDate.setBounds(37, 80, 120, 25);
        removeTask = new JTextField("enter task", 10);
        removeTask.setBounds(BUTTON_X_POS, 190, 150, 25);
        completeTask = new JTextField("enter task", 10);
        completeTask.setBounds(BUTTON_X_POS, 300, 150, 25);
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
        makeTextFields();
        removeTask.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                if (removeTask.getText().equalsIgnoreCase("enter task")) {
                    removeTask.setText("");
                    removeTask.repaint();
                }
            }
        });
        JButton confirmDelete = new JButton("Delete");
        confirmDelete.setBounds(BUTTON_X_POS, 225, 150, 30);
        confirmDelete.setBackground(Color.white);
        confirmDelete.setActionCommand("delete item");
        confirmDelete.addActionListener(this);
        confirmDelete.setFocusable(false);
        rightPane.add(removeTask);
        rightPane.add(confirmDelete);
        pack();
    }

    private void completeTaskMenu() {
        makeTextFields();
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
        confirmComplete.setBounds(BUTTON_X_POS, 335, 150, 30);
        confirmComplete.setBackground(Color.white);
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
        home.setBounds(RIGHT_PANE_WIDTH / 3 + 10, RIGHT_PANE_HEIGHT - 80, 150, 30);
        home.setBorder(BorderFactory.createRaisedBevelBorder());
        rightPane.add(home);
    }

    private void createHeader() {
        header = new JLabel("MY TASKS");
        header.setFont(new Font("header", 1, FONT_SIZE));
        add(header);
        addTaskHeader = new JLabel("ADD TASK:");
        addTaskHeader.setFont(new Font("options", 1, SUB_FONT));
        addTaskHeader.setBounds(10, 20, 200, 30);
        rightPane.add(addTaskHeader);
        removeTaskHeader = new JLabel("REMOVE TASK: ");
        removeTaskHeader.setFont(new Font("options", 1, SUB_FONT));
        removeTaskHeader.setBounds(10, 160, 200, 30);
        rightPane.add(removeTaskHeader);
        completeTaskHeader = new JLabel("COMPLETE TASK: ");
        completeTaskHeader.setFont(new Font("options", 1, SUB_FONT));
        completeTaskHeader.setBounds(10, 270, 200, 30);
        rightPane.add(completeTaskHeader);
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
