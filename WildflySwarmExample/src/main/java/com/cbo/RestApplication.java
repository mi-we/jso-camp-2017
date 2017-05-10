package com.cbo;


import com.cbo.rest.resource.MovieResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;

import org.wildfly.swarm.config.logging.Level;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.logging.LoggingFraction;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by cbo on 08.05.17.
 */
@ApplicationPath("/rest")
public class RestApplication extends Application {

  private static String logFile = "/Users/cbo/swarm.log";
  private static String errorLogFile = "/Users/cbo/swarmError.log";
  private static String driverModule = "com.h2database.h2";

  public static void main (String ... args) throws Exception {

    Swarm swarm = new Swarm();
    // Datasource
//    swarm.fraction(new DatasourcesFraction()
//        .jdbcDriver("h2", (d) -> {
//          d.driverClassName("org.h2.Driver");
//          d.xaDatasourceClass("org.h2.jdbcx.JdbcDataSource");
//          d.driverModuleName(driverModule);
//        })
//        .dataSource("MyDS", (ds) -> {
//          ds.driverName("h2");
//          ds.connectionUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
//          ds.userName("sa");
//          ds.password("sa");
//        }));


    JAXRSArchive deployment = ShrinkWrap.create( JAXRSArchive.class );
    deployment.addResource( MovieResource.class );
//    deployment.addModule(driverModule);

    swarm.start();
    swarm.deploy(deployment);

  }

}
