package com.zuehlke.jso.movieservice.rest;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatcher1;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import akka.http.javadsl.server.directives.RouteAdapter;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.util.Timeout;
import com.zuehlke.jso.movieservice.model.Movie;
import com.zuehlke.jso.movieservice.repository.MovieRepositoryActor;
import scala.concurrent.duration.Duration;

import java.util.List;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.PathMatchers.longSegment;
import static akka.pattern.PatternsCS.ask;

/**
 * Sample REST service implementation using Akka Http.io extension
 */
public class MovieRestService extends AllDirectives {

    private final ActorSystem system;
    private final ActorRef controllerActor;
    private final CompletionStage<ServerBinding> binding;

    public MovieRestService(ActorSystem system, ActorRef controllerActor, String hostname, int portNumber) {
        this.system = system;
        this.controllerActor = controllerActor;

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = createRestServiceRoutes().flow(materializer.system(), materializer);
        binding = http.bindAndHandle(routeFlow,
                ConnectHttp.toHost(hostname, portNumber), materializer);
    }

    public CompletionStage<ServerBinding> getBinding() {
        return binding;
    }

    public Route createRestServiceRoutes() {
        final Timeout timeout = new Timeout(Duration.create(5, "seconds"));

        final PathMatcher1<String> movieId = PathMatchers.segment();

        return route(
                // matches the empty path -> static resource
                pathSingleSlash(() ->
                        getFromResource("web/index.html")
                ),
                // static resources
                pathPrefix("js", () ->
                        getFromResourceDirectory("web/js/")
                ),
                //path("movie", () -> route(
                // matches paths like this: /movie and /move/id
                pathPrefix("movies", () ->
                        route(
                                get(() -> getMovies()),
                                get(() -> path(longSegment(), (Long id) -> getMovieWithId(id)))
                        )
                ),
                pathPrefix("movie", () ->
                        get(() -> path(longSegment(), (Long id) -> getMovieWithId(id)))
                )
        );
    }

    private RouteAdapter getMovies() {
        final Timeout timeout = new Timeout(Duration.create(5, "seconds"));

        CompletionStage<List<Movie>> movies = ask(controllerActor,
                new MovieRepositoryActor.ListAllMoviesCommand(), timeout).thenApply((List.class::cast));
        return completeOKWithFuture(movies, Jackson.marshaller());
    }

    private RouteAdapter getMovieWithId(Long movieId) {
        final Timeout timeout = new Timeout(Duration.create(5, "seconds"));

//        final CompletionStage<Optional<PrettyPrinter.Item>> futureMaybeItem = fetchItem(id);
//        return onSuccess(() -> futureMaybeItem, maybeItem ->
//                maybeItem.map(item -> completeOK(item, Jackson.marshaller()))
//                        .orElseGet(() -> complete(StatusCodes.NOT_FOUND, "Not Found"))
//        );

        CompletionStage<Movie> movies = ask(controllerActor,
                new MovieRepositoryActor.MoviesDetailsCommand(Long.toString(movieId)), timeout).thenApply((Movie.class::cast));
        return completeOKWithFuture(movies, Jackson.marshaller());
    }

}

