package com.hesscom.helloworld.boundary;

import com.hesscom.helloworld.control.HelloWorldController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/hello")
public class HelloWorldEndpoint {

    @Inject
    HelloWorldController controller;


    @GET
    @Produces("text/plain")
    public Response doGet() {
        return Response.ok("Hello from WildFly Swarm!").build();
    }

    @PUT
    @Path("{data}")
    @Consumes("text/plain")
    public Response storeData(@PathParam("data") String data) {
                boolean success = controller.store(data);
                if (success) {
                    return Response.ok("Success").build();
                } else {
                    return Response.serverError().build();
                }
    }
}