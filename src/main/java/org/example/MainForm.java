package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainForm extends JFrame {
    private final JList<Task> taskList;
    private final JComboBox<String> statusComboBox;
    private final DefaultListModel<Task> taskListModel;
    private final List<Task> tasks;

    public MainForm() {
        setTitle("Главное окно");
        setLayout(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setCellRenderer(new TaskListCellRenderer());
        taskList.addListSelectionListener(e -> updateStatusComboBox());
        add(new JScrollPane(taskList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton createButton = new JButton("Создать");
        createButton.addActionListener(e -> {
            TaskForm taskForm = new TaskForm(MainForm.this);
            taskForm.setLocationRelativeTo(MainForm.this);
            taskForm.setVisible(true);
        });
        buttonPanel.add(createButton);

        JButton updateButton = new JButton("Обновить");
        updateButton.addActionListener(e -> removeDuplicates());
        buttonPanel.add(updateButton);

        JButton changeStatusButton = new JButton("Изменить");
        changeStatusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < tasks.size()) {
                    Task task = tasks.get(selectedIndex);
                    String currentStatus = task.getStatus();
                    String newStatus = (String) statusComboBox.getSelectedItem();

                    if (!currentStatus.equals("Завершена")) {
                        task.setStatus(newStatus);
                        taskList.repaint();
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

        statusComboBox = new JComboBox<>(new String[]{"Открыта", "В процессе", "Завершена"});
        buttonPanel.add(statusComboBox);

        add(buttonPanel, BorderLayout.SOUTH);

        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskListModel.addElement(task);
    }

    public void removeDuplicates() {
        List<Task> uniqueTasks = new ArrayList<>(tasks.size());
        for (Task task : tasks) {
            if (!uniqueTasks.contains(task)) {
                uniqueTasks.add(task);
            }
        }
        tasks.clear();
        tasks.addAll(uniqueTasks);
        taskListModel.clear();
        for (Task task : tasks) {
            taskListModel.addElement(task);
        }
    }

    private void updateStatusComboBox() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < tasks.size()) {
            Task task = tasks.get(selectedIndex);
            String currentStatus = task.getStatus();
            statusComboBox.setSelectedItem(currentStatus);
        }
    }

    private static class TaskListCellRenderer extends JPanel implements ListCellRenderer<Task> {
        private final JLabel executorLabel;
        private final JLabel nameLabel;
        private final JLabel priorityLabel;
        private final JLabel statusLabel;

        public TaskListCellRenderer() {
            setLayout(new GridLayout(4, 1));

            executorLabel = new JLabel();
            add(executorLabel);

            nameLabel = new JLabel();
            add(nameLabel);

            priorityLabel = new JLabel();
            add(priorityLabel);

            statusLabel = new JLabel();
            add(statusLabel);
        }

        public Component getListCellRendererComponent(JList<? extends Task> list, Task value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            executorLabel.setText("Исполнитель: " + value.getExecutor());
            nameLabel.setText("Заявка: " + value.getName());
            priorityLabel.setText("Приоритет: " + value.getPriority());
            statusLabel.setText("Статус: " + value.getStatus());

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return this;
        }
    }

}


