package com.zuehlke.jso.camp2017;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // TODO: application initialization
    }

    @Override
    public void run(final DropwizardMovieServiceConfiguration configuration,
                    final Environment environment) {
        MovieRepository movieRepository = new MovieRepository();
        movieRepository.save(new Movie(1, "Dr. Strange", "2016"));

        environment.healthChecks().register("movie data", new MovieHealthCheck(movieRepository));
        environment.jersey().register(new MovieResource(movieRepository));
    }

}
