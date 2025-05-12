package me.kzv.stubs;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClientSkeleton {

    @BeforeAll
    public static void setUp() {
        // Start Undertow Server and configure it to return "It works" when
        // the http://localhost:8081/testGetContentOk URL is
        // called.
    }

    @AfterAll
    public static void tearDown() {
        // Stop Undertow.
    }

    @Test
    @Disabled(value = "단순한 테스트 스켈레톤이므로 현재 이 테스트를 실행하면 실패한다")
    public void testGetContentOk() throws MalformedURLException {
        SampleWebClient client = new SampleWebClient();
        String workingContent = client.getContent(new URL("http://localhost:8081/testGetContentOk"));

        assertEquals("It works", workingContent);
    }
}
