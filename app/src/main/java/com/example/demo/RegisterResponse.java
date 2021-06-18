package com.example.demo;

public class RegisterResponse {

    private String email;
    private String name;
    private String surname;
    private String pass;
    private String passCon;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPassCon() {
        return passCon;
    }

    public void setPassCon(String passCon) {
        this.passCon = passCon;
    }
}
