package org.acme.camel.java;

import org.apache.camel.builder.RouteBuilder;

public class CamelRoute extends RouteBuilder {

    static final String I_AM_ALIVE_MESSAGE = "I'm alive !";

    @Override
    public void configure() {
        from("timer:keep-alive?period=5000").id("timer-route-java")
                .log("Creating test file in /tmp/")
                .setBody(constant(I_AM_ALIVE_MESSAGE))
                .to("file:/tmp/test.txt");

        from("file:/tmp/test.txt?delete=true")
                .log("Found a File!")
                .to("log:done");

    }

}
