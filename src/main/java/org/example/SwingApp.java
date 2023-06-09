package org.example;


import javax.swing.*;
import java.awt.*;


public class SwingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingApp::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        MainForm mainForm = new MainForm();
        mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainForm.setPreferredSize(new Dimension(640, 480));
        mainForm.pack();
        mainForm.setVisible(true);
    }
}



