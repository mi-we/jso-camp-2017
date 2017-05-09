package com.zuehlke.jso.camp2017;

import com.zuehlke.jso.camp2017.api.Movie;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.assertj.core.api.Assertions;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleIntegrationTest {

    @ClassRule
    public static final DropwizardAppRule<DropwizardMovieServiceConfiguration> APP_RULE =
            new DropwizardAppRule<DropwizardMovieServiceConfiguration>(DropwizardMovieServiceApplication.class,
                    "config.yml");

    @Test
    public void givenApplicationIsSetup_whenRequestingAllMovies_thenDbEntriesAreReturned() {
        JerseyClient client = new JerseyClientBuilder().build();
        Response response = client.target(String.format("http://localhost:%d/api/movies", APP_RULE.getLocalPort()))
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(200);

        List<Movie> actual = response.readEntity(new GenericType<List<Movie>>() {
        });
        assertThat(actual).isNotEmpty();
    }

}
