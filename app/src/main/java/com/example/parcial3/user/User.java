package com.example.parcial3.user;

import java.util.ArrayList;

public class User {
    public int id;
    public String name;
    public String email;
    public int age;

    public User(int id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public static ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<User>();
        return  users;
    }
}
