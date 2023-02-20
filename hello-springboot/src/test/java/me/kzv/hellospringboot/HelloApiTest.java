package me.kzv.hellospringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloApiTest {

//    @LocalServerPort
//    int randomServerPort;

    @Test
    public void helloApi() throws Exception {
        //http localhost:8080/hello?name=Spring
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res = rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");

        // status cod 200
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);

        // header(content-type) text/plain
        assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);

        //body Hello Spring
        assertThat(res.getBody()).isEqualTo("*Hello Spring*");
    }

    @Test
    public void failHelloApi() throws Exception {
        //http localhost:8080/hello?name=Spring
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res = rest.getForEntity("http://localhost:8080/hello?name=", String.class, "");

        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}