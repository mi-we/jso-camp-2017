package com.zuehlke.jso.camp2017.db;

public class Movie {

    private long id;
    private String title;
    private String year;

    public Movie() {
    }

    public Movie(long id, String title, String year) {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }
}
