package com.zuehlke.jso.ccuz.swarm;

import com.zuehlke.jso.ccuz.swarm.rest.MovieRestService;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

/**
 * Created by ccu on 08.05.2017.
 */
public class Main {
    public static void main(String... args) throws Exception {
        Swarm swarm = new Swarm();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.addClass(MovieRestService.class);
        deployment.addAllDependencies();
        swarm.start().deploy(deployment);

    }
}
