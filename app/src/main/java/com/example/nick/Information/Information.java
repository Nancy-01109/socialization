package com.example.nick.Information;

public class Information{
    Integer id;
    Integer idTheme;
    String steps;
    Integer idInterest;

    public Information(Integer id, String steps,Integer idTheme,Integer idInterest) {
        this.id = id;
        this.steps = steps;
        this.idTheme = idTheme;
        this.idInterest = idInterest;
    }

    public Information( String steps, Integer idTheme, Integer idInterest) {
        this.steps = steps;
        this.idTheme = idTheme;
        this.idInterest=idInterest;
    }

    public Information(String steps){
        this.steps=steps;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(Integer idTheme) {
        this.idTheme = idTheme;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }


    public Integer getIdInterest() {
        return idInterest;
    }

    public void setIdInterest(Integer idInterest) {
        this.idInterest = idInterest;
    }


}
