package com.cbo.rest.resource;

/**
 * Created by cbo on 09.05.17.
 */
import org.wildfly.swarm.health.Health;
import org.wildfly.swarm.health.HealthStatus;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/app")
public class HealthCheckResource {

  @GET
  @Path("/diskSpace")
  @Health
  public HealthStatus checkDiskspace() {
    HealthStatus healthStatus = HealthStatus.named("checkDiskspace");
    File path = new File(".");
    long freeBytes = path.getFreeSpace();
    long threshold = 1024 * 1024 * 100; // 100mb
    return freeBytes>threshold ? healthStatus.up() : healthStatus.down().withAttribute("freebytes", freeBytes);
  }

  @GET
  @Path("/other")
  @Health
  public HealthStatus checkSomethingElse() {
    HealthStatus healthStatus = HealthStatus.named("checkSomethingElse");
    return healthStatus.up().withAttribute("Hallo", "Welt");
  }

}
