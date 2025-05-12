package me.kzv.stubs;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClient {

    private SampleWebClient client = new SampleWebClient();

    @BeforeAll
    public static void setUp() throws Exception {
        Undertow server = Undertow.builder()
                .addHttpListener(8081, "0.0.0.0")
                .setHandler(exchange -> {
                    String path = exchange.getRequestPath();

                    switch (path) {
                        case "/testGetContentOk":
                            new TestGetContentOkHandler().handleRequest(exchange);
                            break;
                        case "/testGetContentError":
                            new TestGetContentServerErrorHandler().handleRequest(exchange);
                            break;
                        case "/testGetContentNotFound":
                            new TestGetContentNotFoundHandler().handleRequest(exchange);
                            break;
                        default:
                            exchange.setStatusCode(404);
                            exchange.getResponseSender().send("Not Found");
                            break;
                    }
                })
                .build();

        server.start();
    }

    @AfterAll
    public static void tearDown() {
        // Empty
    }

    @Test
    public void testGetContentOk() throws MalformedURLException {
        String workingContent = client.getContent(new URL("http://localhost:8081/testGetContentOk"));
        assertEquals("It works", workingContent);
    }

    public static class TestGetContentOkHandler implements HttpHandler {
        @Override
        public void handleRequest(HttpServerExchange exchange) {
            String content = "It works";
            byte[] contentBytes = content.getBytes(StandardCharsets.ISO_8859_1);

            exchange.setStatusCode(HttpServletResponse.SC_OK);
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain; charset=ISO-8859-1");
            exchange.getResponseHeaders().put(Headers.CONTENT_LENGTH, String.valueOf(contentBytes.length));
            exchange.getResponseSender().send(content);
        }
    }

    public static class TestGetContentServerErrorHandler implements HttpHandler {
        @Override
        public void handleRequest(HttpServerExchange exchange) {
            exchange.setStatusCode(HttpServletResponse.SC_SERVICE_UNAVAILABLE); // SERVICE_UNAVAILABLE
            exchange.getResponseSender().send("Service Unavailable");
        }
    }

    public static class TestGetContentNotFoundHandler implements HttpHandler {
        @Override
        public void handleRequest(HttpServerExchange exchange) {
            exchange.setStatusCode(HttpServletResponse.SC_NOT_FOUND); // NOT_FOUND
            exchange.getResponseSender().send("Not Found");
        }
    }
}
