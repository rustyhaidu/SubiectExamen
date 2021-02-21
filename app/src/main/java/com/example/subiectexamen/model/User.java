package com.example.subiectexamen.model;

public class User {
    private String username;
    private String password;
    private int time;
    private String gen;
    private String functie;

    public User() {
    }

    public User(String username, String password, int time, String gen, String functie) {
        this.username = username;
        this.password = password;
        this.time = time;
        this.gen = gen;
        this.functie = functie;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", time=" + time +
                ", gen='" + gen + '\'' +
                ", functie=" + functie +
                '}';
    }
}
