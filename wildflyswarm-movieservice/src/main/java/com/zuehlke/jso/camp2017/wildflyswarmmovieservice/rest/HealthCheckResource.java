package com.zuehlke.jso.camp2017.wildflyswarmmovieservice.rest;

import org.wildfly.swarm.health.Health;
import org.wildfly.swarm.health.HealthStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.File;

@Path("/app")
public class HealthCheckResource {

    @GET
    @Path("/diskspace")
    @Health
    public HealthStatus getDiskspaceStatus() {
        File file = new File(".");
        long freeBytes = file.getFreeSpace();
        long threshold = 1024 * 1024 * 100;
        return freeBytes > threshold ? HealthStatus.named("diskspace").up()
                : HealthStatus.named("diskspace").down().withAttribute("freebytes", freeBytes);
    }

    @GET
    @Path("/db")
    @Health
    public HealthStatus getDbStatus() {
        return HealthStatus.named("db").up();
    }
}
