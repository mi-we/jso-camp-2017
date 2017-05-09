package com.zuehlke.jso.camp2017.db;

import com.zuehlke.jso.camp2017.api.Movie;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(MovieMapper.class)
public interface MovieDAO {

    @SqlUpdate("CREATE TABLE IF NOT EXISTS movies (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(100), year VARCHAR(4))")
    void createTableIfNotExists();

    @SqlQuery("SELECT * FROM movies")
    List<Movie> findAll();

    @SqlQuery("SELECT DISTINCT * FROM movies WHERE id = :id")
    Movie findById(@Bind("id") long id);

    @SqlUpdate("INSERT INTO movies (title, year) VALUES (:title, :year)")
    void insert(@Bind("title") String title, @Bind("year") String year);

    @SqlUpdate("DELETE FROM movies")
    void deleteAll();
}
