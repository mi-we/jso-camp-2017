package com.zuehlke.jso.camp2017.wildflyswarmmovieservice.rest.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = Movie.GET_ALL, query = "SELECT m FROM Movie m")
})
public class Movie {

    static final String GET_ALL = "GET_ALL";

    @Id
    @GeneratedValue
    private long id;

    private String title;
    private String year;

    protected Movie() {

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
