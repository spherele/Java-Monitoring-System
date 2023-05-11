package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListForm {
    MainForm mainForm;

    private static final  String PATH = "src/main/resources/map.txt";

    public ListForm(MainForm mainForm) throws IOException {
        initComponents();
        this.mainForm = mainForm;
    }

    private void initComponents() throws IOException {
        JFrame frame;
        JLabel label;
        JPanel panel;
        JButton buttonCreate;
        JButton buttonClose;
        frame = new JFrame("Список задач");
        frame.setSize(640, 640);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        buttonCreate = new JButton("Создать");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(buttonCreate, constraints);


        label = new JLabel();

        Map<String, ArrayList<String>> restoredHashMap = loadTXT();
        StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, ArrayList<String>> entry : restoredHashMap.entrySet()) {
                stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("<br>");
            }

        String end = stringBuilder.toString().replaceAll("\\[", "").replaceAll("\\]", "");
        label.setText("<html>" + end + "</html>");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(label, constraints);



        buttonClose = new JButton("Завершить");
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(buttonClose, constraints);

        buttonCreate.addActionListener(e -> {
            frame.setVisible(false);
            mainForm.getFrame().setVisible(true);
        });
        buttonClose.addActionListener(e -> System.exit(0));
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private Map<String, ArrayList<String>> loadTXT() throws IOException {
        Map<String, ArrayList<String>> map = new HashMap<>();

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(PATH));
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
                } else {
                    System.out.println("ignoring line: " + line);
                }
            }
            return map;
        }
    }




