package com.cbo.springmovie.entity;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by cbo on 10.05.17.
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Movie.GET_ALL, query = "select m from Movie m"),
    @NamedQuery(name = Movie.GET, query = "select m from Movie m where m.id = :id")
})
public class Movie {

  public static final String GET_ALL = "GET_ALL";
  public static final String GET = "GET";
  @Id
  private long id;
  @Column
  private String name;
  @Column
  private long year;

  public Movie() {};

  public Movie(long id, String name, long year) {
    this.id = id;
    this.name = name;
    this.year = year;
  }

  public long getYear() {
    return year;
  }

  public void setYear(long year) {
    this.year = year;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
