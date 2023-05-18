package org.example;

import java.util.Objects;

class Task {
    private final String executor;
    private final String name;
    private final String priority;
    private String status;

    public Task(String executor, String name, String priority, String status) {
        this.executor = executor;
        this.name = name;
        this.priority = priority;
        this.status = status;
    }

    public String getExecutor() {
        return executor;
    }

    public String getName() {
        return name;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Исполнитель: " + executor + "\n" +
                "Заявка: " + name + "\n" +
                "Приоритет: " + priority + "\n" +
                "Статус: " + status;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(executor, task.executor) &&
                Objects.equals(name, task.name) &&
                Objects.equals(priority, task.priority) &&
                Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(executor, name, priority, status);
    }

}