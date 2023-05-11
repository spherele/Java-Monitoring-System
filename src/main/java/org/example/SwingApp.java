package org.example;

import javax.swing.*;

public class SwingApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainForm mainForm = new MainForm();
            mainForm.setVisible(true);
            mainForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        });
    }
}



