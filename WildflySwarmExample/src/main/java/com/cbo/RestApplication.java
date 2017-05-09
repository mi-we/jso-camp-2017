package com.cbo;
//
//import org.wildfly.swarm.Swarm;
//import org.wildfly.swarm.datasources.DatasourcesFraction;

import com.cbo.rest.resource.MovieResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;




/**
 * Created by cbo on 08.05.17.
 */
@ApplicationPath("/rest")
public class RestApplication extends Application {

  public static void main (String [] args) throws Exception {

    Swarm swarm = new Swarm();
    swarm.fraction(new DatasourcesFraction()
        .jdbcDriver("h2", (d) -> {
          d.driverClassName("org.h2.Driver");
          d.xaDatasourceClass("org.h2.jdbcx.JdbcDataSource");
          d.driverModuleName("com.h2database.h2");
        })
        .dataSource("MyDS", (ds) -> {
          ds.driverName("h2");
          ds.connectionUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
          ds.userName("sa");
          ds.password("sa");
        })
    );


    JAXRSArchive deployment = ShrinkWrap.create( JAXRSArchive.class );
    deployment.addResource( MovieResource.class );

  }

}
