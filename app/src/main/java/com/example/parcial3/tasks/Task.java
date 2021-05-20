package com.example.parcial3.tasks;

public class Task {
    public int id;
    public String title;
    public String description;
    public String status;

    public Task(int id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Task(String title, String description, String status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Task() {
    }
}
