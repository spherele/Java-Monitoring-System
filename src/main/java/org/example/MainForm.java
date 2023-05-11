package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainForm extends JFrame {


    public MainForm() {
        initComponents();
    }

    private void initComponents() {
        JLabel label1;
        JComboBox<String> comboBox1;
        JLabel label2;
        JComboBox<String> comboBox2;
        JButton buttonNext;
        JButton buttonClose;
        setTitle("Создание заявки");
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        label1 = new JLabel("Исполнитель:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        add(label1, constraints);

        comboBox1 = new JComboBox<>(new String[]{
                "Алексей",
                "Владимир",
                "Максим",
                "Данил",
                "Пётр",
                "Кирилл",
                "Илья",
                "Евгений",
                "Сергей",
                "Дмитрий",
                "Михаил",
                "Павел",
                "Александр",
                "Константин",
                "Виктор",
                "Григорий",
                "Никита",
                "Артём",
                "Иван",
                "Ольга",
                "Елизавета",
                "Екатерина",
                "Валерия",
                "Софья",
        });

        constraints.gridx = 1;
        add(comboBox1, constraints);

        label2 = new JLabel("Приоритет заявки:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(label2, constraints);

        comboBox2 = new JComboBox<>(new String[]{
                "Высокий",
                "Средний",
                "Низкий"
        });
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(comboBox2, constraints);

        buttonNext = new JButton("Далее");
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(buttonNext, constraints);

        buttonClose = new JButton("Завершить");
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(buttonClose, constraints);

        buttonNext.addActionListener(e -> {
            String executor = Objects.requireNonNull(comboBox1.getSelectedItem()).toString();
            String priority = Objects.requireNonNull(comboBox2.getSelectedItem()).toString();
            setVisible(false);
            new TaskForm(MainForm.this, executor, priority);
        });

        buttonClose.addActionListener(e -> System.exit(0));
        pack();
        setLocationRelativeTo(null);
    }

    public Component getFrame() {
        return this;
    }
}


