package com.zuehlke.jso.camp2017.cli;

import io.dropwizard.cli.Command;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

public class HelloWorldCommand extends Command {

    public HelloWorldCommand() {
        super("hello", "Displays a hello world message");
    }

    @Override
    public void configure(Subparser subparser) {

    }

    @Override
    public void run(Bootstrap<?> bootstrap, Namespace namespace) throws Exception {
        System.out.println("Hello, World!");
    }
}
