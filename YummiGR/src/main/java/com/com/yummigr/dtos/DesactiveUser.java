package com.com.yummigr.dtos;

public class DesactiveUser {

    private String description;

    private String name;

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DesactiveUser(String name, String description){
     setName(name);
     setDescription(description);
    }
}
