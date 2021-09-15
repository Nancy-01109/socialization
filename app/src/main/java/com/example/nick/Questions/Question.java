package com.example.nick.Questions;

public class Question {
    Integer id;
    String description;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Question(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Question(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
