package com.zuehlke.jso.movieservice.repository;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import com.zuehlke.jso.movieservice.model.Movie;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccu on 09.05.2017.
 */
public class MovieRepositoryActor extends UntypedActor {
    LoggingAdapter loggingAdapter = Logging.getLogger(getContext().system(), this);

    private MovieRepositoryActor(String connectionUrl){
    }

    public static final class ListAllMoviesCommand {
    }

    public static final class MoviesDetailsCommand {
        private final String id;
        public MoviesDetailsCommand(String id){
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    private static final String dbInstanceName = "movies";

    /**
     * Connect to a running mangodb
     * @param connectionUrl mongodb://localhost/movie
     * @return
     */
    public static Props propsFor(final String connectionUrl){
        return Props.create(MovieRepositoryActor.class, ()->new MovieRepositoryActor(connectionUrl));
    }

    public static Props propsFor(){
        return Props.create(MovieRepositoryActor.class, ()->new MovieRepositoryActor(null));
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof ListAllMoviesCommand){
            final List<Movie> movies = new ArrayList<Movie>();
            movies.add(mockDB(null));
            getSender().tell(movies, getSelf());
        }
        else if (message instanceof MoviesDetailsCommand){
            final String id = ((MoviesDetailsCommand)message).getId();
            getSender().tell(mockDB(id), getSelf());
        }
    }


    private Movie mockDB(String movieId){
        Movie movie = new Movie();
        movie.setMovieID(movieId == null ? "The Good Wife (2009)" : movieId);
        movie.setSeriesID(movieId == null ? "The Good Wife (2009)" : movieId);
        movie.setSeriesType("S");
        movie.setSeriesEndYear("");
        movie.setReleaseYear("2009");
        movie.setAlternativeTitles("Good Wife (2009)");
        return movie;
    }
}
