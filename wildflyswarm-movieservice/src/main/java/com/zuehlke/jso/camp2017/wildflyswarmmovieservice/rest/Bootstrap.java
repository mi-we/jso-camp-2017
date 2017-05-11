package com.zuehlke.jso.camp2017.wildflyswarmmovieservice.rest;

import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.config.logging.Level;
import org.wildfly.swarm.logging.LoggingFraction;

public class Bootstrap {

    public static void main(String[] args) throws Exception {
        Swarm swarm = new Swarm();

        swarm
                .start()
                .deploy();
    }
}
