package org.example;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainForm extends JFrame {
    private final JTextArea textArea;
    private final JComboBox<String> statusComboBox;
    private List<Task> tasks;
    private final List<Integer> taskIndexes;

    public MainForm() {
        setTitle("Главное окно");
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        statusComboBox = new JComboBox<>(new String[]{"Открыта", "В процессе", "Завершена"});
        buttonPanel.add(statusComboBox);

        JButton changeStatusButton = new JButton("Изменить статус");
        changeStatusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = textArea.getSelectionStart();
                int lineNumber = 0;
                try {
                    lineNumber = textArea.getLineOfOffset(selectedIndex);
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                if (lineNumber >= 0 && lineNumber < taskIndexes.size()) {
                    int taskIndex = taskIndexes.get(lineNumber);
                    Task task = tasks.get(taskIndex);
                    String currentStatus = task.getStatus();
                    String newStatus = (String) statusComboBox.getSelectedItem();

                    if (!currentStatus.equals("Завершена")) {
                        task.setStatus(newStatus);
                        updateTextArea();
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Невозможно изменить статус у закрытой заявки.",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonPanel.add(changeStatusButton);


        JButton updateButton = new JButton("Обновить");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeDuplicates();
            }
        });
        buttonPanel.add(updateButton);

        JButton createButton = new JButton("Создать");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskForm taskForm = new TaskForm(MainForm.this);
                taskForm.setLocationRelativeTo(MainForm.this);
                taskForm.setVisible(true);
            }
        });
        buttonPanel.add(createButton);

        add(buttonPanel, BorderLayout.SOUTH);

        tasks = new ArrayList<>();
        taskIndexes = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskIndexes.add(tasks.size() - 1);
        updateTextArea();
    }

    public void updateTextArea() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskIndexes.size(); i++) {
            int taskIndex = taskIndexes.get(i);
            Task task = tasks.get(taskIndex);
            sb.append(task.toString()).append("\n");
        }
        textArea.setText(sb.toString());
    }

    public void removeDuplicates() {
        List<Task> uniqueTasks = new ArrayList<>(tasks.size());
        for (int i = 0; i < taskIndexes.size(); i++) {
            int taskIndex = taskIndexes.get(i);
            Task task = tasks.get(taskIndex);
            if (!uniqueTasks.contains(task)) {
                uniqueTasks.add(task);
            }
        }
        tasks = uniqueTasks;
        taskIndexes.clear();
        for (int i = 0; i < tasks.size(); i++) {
            taskIndexes.add(i);
        }
        updateTextArea();
    }
}

