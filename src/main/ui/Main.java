package ui;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        try {
            new AgendaAppGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to run application due to file not being found");
        }
    }
}
