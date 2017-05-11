package com.zuehlke.jso.camp2017;

import com.zuehlke.jso.camp2017.api.Movie;
import com.zuehlke.jso.camp2017.cli.HelloWorldCommand;
import com.zuehlke.jso.camp2017.db.MovieDAO;
import com.zuehlke.jso.camp2017.db.MovieRepository;
import com.zuehlke.jso.camp2017.health.MovieHealthCheck;
import com.zuehlke.jso.camp2017.resources.ClearDbTask;
import com.zuehlke.jso.camp2017.resources.MovieResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class DropwizardMovieServiceApplication extends Application<DropwizardMovieServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardMovieServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardMovieService";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardMovieServiceConfiguration> bootstrap) {
        bootstrap.addCommand(new HelloWorldCommand());
        bootstrap.addBundle(new AssetsBundle("/assets", "/"));
        bootstrap.addBundle(new DBIExceptionsBundle());
    }

    @Override
    public void run(final DropwizardMovieServiceConfiguration configuration,
                    final Environment environment) {

        DBIFactory dbiFactory = new DBIFactory();
        DBI dbi = dbiFactory.build(environment, configuration.getDataSourceFactory(), "h2");
        MovieDAO movieDAO = dbi.onDemand(MovieDAO.class);
        movieDAO.createTableIfNotExists();

        MovieRepository movieRepository = new MovieRepository(movieDAO);
        movieRepository.save(new Movie(1, "Dr. Strange", "2016"));

        environment.healthChecks().register("movie data", new MovieHealthCheck(movieRepository));
        environment.jersey().register(new MovieResource(movieRepository));
        environment.admin().addTask(new ClearDbTask(movieRepository));
    }

}
