package ui;

import model.CourseList;
import model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TasksPage extends JFrame implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 650;
    private static final int FONT_SIZE = 30;

    private JLabel header;
    private JButton home;
    private JSplitPane splitPane;
    private JScrollPane leftPane = new JScrollPane();
    private JPanel rightPane = new JPanel();
    private TaskList tl;
    private CourseList cl;
    private TaskList ctl;

    public TasksPage(TaskList tl, CourseList cl, TaskList ctl) {
        this.tl = tl;
        this.cl = cl;
        this.ctl = ctl;
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
        splitPane.setDividerLocation(350);
        add(splitPane);
        setVisible(true);
        setResizable(false);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 209, 209));
        pack();

        createHeader();
        createHomeButton();

    }

    private void createHomeButton() {
        home = new JButton("Home");
        home.setFocusable(false);
        home.setActionCommand("Go back to main page");
        home.addActionListener(this);
        rightPane.add(home);
    }

    private void createHeader() {
        header = new JLabel("TASKS");
        header.setFont(new Font("header",1,FONT_SIZE));
        header.setBounds(10, 10, 50, 50);
        add(header);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Go back to main page")) {
            dispose();
            new MainScreen(tl, cl, ctl);
        }
    }
}
