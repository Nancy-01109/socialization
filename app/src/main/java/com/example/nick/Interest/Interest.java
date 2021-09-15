package com.example.nick.Interest;

public class Interest {

    private Integer id;
    private String interest;
    private String hobby;
    private String tvshow;
    private String books;
    private Integer iduser;


    public Interest(Integer id, String interest, String hobby, String tvshow, String books, Integer iduser) {
        this.id = id;
        this.interest = interest;
        this.hobby = hobby;
        this.tvshow = tvshow;
        this.books = books;
        this.iduser = iduser;
    }

    public Interest(String interest, String hobby, String tvshow, String books, Integer iduser) {
        this.interest = interest;
        this.hobby = hobby;
        this.tvshow = tvshow;
        this.books = books;
        this.iduser = iduser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getTvshow() {
        return tvshow;
    }

    public void setTvshow(String tvshow) {
        this.tvshow = tvshow;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }


}
