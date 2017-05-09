package com.zuehlke.jso.camp2017.db;

import com.zuehlke.jso.camp2017.api.Movie;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieMapper implements ResultSetMapper<Movie> {
    @Override
    public Movie map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Movie(resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getString("year"));
    }
}
