package org.example;

class Task {
    private String executor;
    private String name;
    private String priority;
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

    public String toStringWithoutStatus() {
        return "Задача: " + name + ", Приоритет: " + priority;
    }

    @Override
    public String toString() {
        return executor + ": " + toStringWithoutStatus() + ", Статус: " + status;
    }
}