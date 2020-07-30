package com.practice.example.model;

public class Employee {
    private int id;
    private String name;
    private String role;
    private String email;

    public Employee(){}

    public Employee(String name, String role, String email){
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Employee(int id, String name, String role, String email) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Id:"+this.id+", Name:"+this.name+", Role"+this.role+", Email:"+this.email;
    }

}
