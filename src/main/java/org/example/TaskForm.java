package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskForm {
    MainForm mainForm;

    private static final String PATH = "src/main/resources/map.txt";
    File file = new File(PATH);


    private final  Map<String, ArrayList<String>> list = new HashMap<>();

    public TaskForm(MainForm mainForm, String executor, String priority) {
        this.mainForm = mainForm;
        initComponents(executor, priority);
    }

    private void initComponents(String executor, String priority) {
        JFrame frame;
        JPanel panel;
        JLabel label;
        JTextArea textArea;
        JButton buttonCreate;
        JButton buttonBack;

        frame = new JFrame("Создание новой задачи");
        frame.setSize(640, 640);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel = new JPanel(new GridBagLayout());
        panel.setSize(640, 640);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        label = new JLabel("Задача для " + executor + ", приоритет: " + priority);
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(label, constraints);

        textArea = new JTextArea();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(textArea, constraints);

        buttonCreate = new JButton("Создать");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(buttonCreate, constraints);

        buttonBack = new JButton("Назад");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(buttonBack, constraints);

        buttonCreate.addActionListener(e -> {
            String taskDescription = textArea.getText();
            ArrayList<String> task = new ArrayList<>();
            task.add(taskDescription);
            list.put(executor, task);
            saveTXT(list);
            task.clear();
            if (taskDescription.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Описание задачи не может быть пустым!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            } else {
                frame.setVisible(false);
                try {
                    new ListForm(mainForm);
                } catch (IOException ignored) {
                }
            }
        });

        buttonBack.addActionListener(e -> {
            frame.setVisible(false);
            mainForm.getFrame().setVisible(true);
        });
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void saveTXT(Map<String,ArrayList<String>> map) {

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(file, true))) {

            for (Map.Entry<String, ArrayList<String>> entry :
                    map.entrySet()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < entry.getValue().size(); i++) {
                    sb.append(entry.getValue().get(i));
                }
                bf.write(entry.getKey() + ":"
                        + sb.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                bf.newLine();
            }
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
