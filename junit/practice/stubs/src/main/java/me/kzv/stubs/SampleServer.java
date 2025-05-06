package me.kzv.stubs;

import io.undertow.Undertow;

public class SampleServer {
    public static Undertow start() {
        Undertow server = Undertow.builder()
                .addHttpListener(8081, "localhost")
//                .setHandler(exchange -> {
//                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
//                    exchange.getResponseSender().send("{\"message\":\"Hello from Undertow\"}");
//                })
                .build();
        server.start();
        return server;
    }

    public static void stop(Undertow server) {
        server.stop();
    }
}
