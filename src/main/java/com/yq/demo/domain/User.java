package com.yq.demo.domain;

public class User {
    private  long id;
    private  String name;
    private  String email;
    private  String description;

    public User(long id, String name,String email, String desc) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.description = desc;
    }

    public User(long id, String name) {
        this(id,name, null, null);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
