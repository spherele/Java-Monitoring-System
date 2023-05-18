package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class TaskForm extends JFrame {
    private final JComboBox<String> executorComboBox;
    private final JTextField nameField;
    private final JComboBox<String> priorityComboBox;

    public TaskForm(MainForm mainForm) {
        setTitle("Создание заявки");
        setPreferredSize(new Dimension(300, 200));
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        JLabel executorLabel = new JLabel("Исполнитель:");
        inputPanel.add(executorLabel);

        executorComboBox = new JComboBox<>(new String[]{"Алексей", "Дмитрий", "Максим", "Ярослав"});
        inputPanel.add(executorComboBox);

        JLabel nameLabel = new JLabel("Заявка:");
        inputPanel.add(nameLabel);

        nameField = new JTextField();
        inputPanel.add(nameField);

        JLabel priorityLabel = new JLabel("Приоритет:");
        inputPanel.add(priorityLabel);

        priorityComboBox = new JComboBox<>(new String[]{"Высокий", "Средний", "Низкий"});
        inputPanel.add(priorityComboBox);

        add(inputPanel, BorderLayout.CENTER);

        JButton createButton = new JButton("Создать");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String executor = (String) executorComboBox.getSelectedItem();
                String name = nameField.getText();
                String priority = (String) priorityComboBox.getSelectedItem();
                String status = "Открыта";
                Task task = new Task(executor, name, priority, status);
                mainForm.addTask(task);
                dispose();
            }
        });

        add(createButton, BorderLayout.SOUTH);

        pack();
    }
}

