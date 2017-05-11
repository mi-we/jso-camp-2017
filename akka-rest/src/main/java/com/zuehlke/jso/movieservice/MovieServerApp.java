package com.zuehlke.jso.movieservice;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.remote.WireFormats;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.util.Timeout;
import com.zuehlke.jso.movieservice.repository.MovieRepositoryActor;
import com.zuehlke.jso.movieservice.rest.MovieRestService;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeoutException;

/**
 * Akka App
 */
public class MovieServerApp {
    public static void main(String[] args) {
        // boot up server using the route as defined below
        ActorSystem system = ActorSystem.create("MovieServerApp");

        ActorRef calculatorServiceActor = system.actorOf(
                MovieRepositoryActor.propsFor("mongodb://localhost/movie"), "movieRepository");

        //In order to access all directives we need an instance where the routes are define.
        MovieRestService endpoint = new MovieRestService(system, calculatorServiceActor, "localhost", 8897);

        try {
            Await.ready(system.whenTerminated(), Duration.Inf());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
//        endpoint.getBinding().thenCompose(ServerBinding::) // trigger unbinding from the port
//        .thenAccept(unbound -> system.terminate()); // and shutdown when done
    }
}