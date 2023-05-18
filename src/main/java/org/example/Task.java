package org.example;

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
}