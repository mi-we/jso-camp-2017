package com.cbo.entity;

import javax.persistence.*;

/**
 * Created by cbo on 09.05.17.
 */
@Entity
@NamedQueries({
    @NamedQuery(name=Movie.getAll, query="SELECT m FROM Movie m"),
    @NamedQuery(name=Movie.getById, query="SELECT m FROM Movie m where m.id = :id")
})

public class Movie {

  public static final String getAll = "GET_ALL";
  public static final String getById = "GET_BY_ID";

  @Id
  private long id;

  @Column
  private String title;

  @Column
  private int year;

  public  Movie() {}

  public Movie(long id, String title, int year) {
    this.id = id;
    this.title = title;
    this.year = year;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }
}
