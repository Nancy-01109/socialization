package com.example.nick.Themes;

import java.util.ArrayList;

public class Theme {

    String name;
    Integer id;
    Integer type;

    public Theme(String name, Integer id,Integer type) {
        this.name = name;
        this.id = id;
        this.type=type;
    }

    public Theme(String name,Integer type) {
        this.name = name;
        this.type=type;

    }
    public Theme(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
