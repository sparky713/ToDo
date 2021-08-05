package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoursesPage extends JFrame implements ActionListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 650;
    private JLabel header;
    private JButton home;

    public CoursesPage() {
        super("Courses");

        setVisible(true);
        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Color.white); // BG COLOR
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        home = new JButton("Home");
        home.setFocusable(false);
        home.setBounds(10, 50, 70, 30);
        home.setActionCommand("Go back to main page");
        home.addActionListener(this);
        add(home);
        addLabel();

    }

    public void addLabel() {
        header = new JLabel("COURSES");
        header.setVerticalTextPosition(JLabel.TOP);
        header.setHorizontalTextPosition(JLabel.LEFT);
        header.setFont(new Font("Header", Font.BOLD, 30));
        add(header);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Go back to main page")) {
            System.out.println("home");
        }

    }

    public static void main(String[] args) {
        new CoursesPage();
    }
}


