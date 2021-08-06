package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoursesPage extends JFrame implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 650;
    private static final int FONT_SIZE = 30;

    private JLabel header;
    private JList list1;
    private JTextField addCourseField;
    private JButton home;
    private JPanel coursesPanel;
    private JTextArea text;
    JSplitPane splitPane;

    public CoursesPage() {
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(new JLabel("Your Courses")),
                new JScrollPane(new JLabel("Options")));
        splitPane.setDividerLocation(350);
        add(splitPane);
        setResizable(false);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        //coursesPanel = new JPanel();
        //coursesPanel.setPreferredSize((new Dimension(WIDTH * (2/3), HEIGHT - 20)));
        //text = new JTextArea();
        //text.setFont(new Font("Arial", Font.PLAIN, 20));
        //text.setEditable(false);
        //text.setBackground(Color.gray);
        //add(coursesPanel, BorderLayout.WEST);
//
        createUIComponents();
        getContentPane().setBackground(Color.white); // BG COLOR
//
        home = new JButton("Home");
        home.setFocusable(false);
        home.setBounds(0, 50, 70, 30);
        home.setActionCommand("Go back to main page");
        home.addActionListener(this);
        getContentPane().add(home, BorderLayout.EAST);
        add(home);
    }

    private void createUIComponents() {
        header = new JLabel("COURSES");
        header.setFont(new Font("header",1,FONT_SIZE));
        header.setBounds(10, 10, 50, 50);
        add(header);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                    new CoursesPage().setVisible(true);
            }
        });
    }
}




