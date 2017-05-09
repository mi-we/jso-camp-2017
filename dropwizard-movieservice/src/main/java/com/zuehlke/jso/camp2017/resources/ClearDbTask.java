package com.zuehlke.jso.camp2017.resources;

import com.google.common.collect.ImmutableMultimap;
import com.zuehlke.jso.camp2017.db.MovieRepository;
import io.dropwizard.servlets.tasks.Task;

import java.io.PrintWriter;

public class ClearDbTask extends Task {
    private MovieRepository movieRepository;

    public ClearDbTask(MovieRepository movieRepository) {
        super("clear-db");
        this.movieRepository = movieRepository;
    }

    @Override
    public void execute(ImmutableMultimap<String, String> immutableMultimap, PrintWriter printWriter) throws Exception {
        printWriter.write("Clearing database...\n");
        movieRepository.clear();
        printWriter.write("Database cleared!\n");
    }
}
