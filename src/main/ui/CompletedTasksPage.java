package ui;

import model.Task;
import model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CompletedTasksPage extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 650;
    private static final int FONT = 30;

    private JTextArea textArea;
    private JLabel header;
    private TaskList ctl;

    public CompletedTasksPage(TaskList ctl) {
        super("Completed Tasks");
        this.ctl = new TaskList();
        this.ctl = ctl;
        getContentPane().setBackground(new Color(203, 240, 255));
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        // HEADER
        header = new JLabel("COMPLETED TASKS");
        header.setFont(new Font("header", 1, FONT));
        add(header);
        // TEXT AREA
        textArea = new JTextArea();
        // POSITION
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.setFont(new Font("completed list", Font.PLAIN, 20));
        textArea.setBackground(new Color(203, 240, 255));
        textArea.setForeground(Color.darkGray);
        textArea.setText(printCompletedTasks());
        textArea.setEditable(false);
        getContentPane().add(scroll);
        getContentPane().add(textArea);
        pack();
    }


    public String printCompletedTasks() {
        List<Task> completedList = ctl.getTasks();
        String completedTasks = "";

        for (Task c : completedList) {
            completedTasks += "- " + c.getTaskDescription() + "   (Due: " + c.getDueDate() + ") \n";
        }

        return "\n" + completedTasks;
    }
}
