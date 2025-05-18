package me.kzv.mocks.web;

import me.kzv.mocks.web.mock.CustomMockConnectionFactory;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClient1 {
    @Test
    public void testGetContentOk() throws Exception {
        CustomMockConnectionFactory customMockConnectionFactory = new CustomMockConnectionFactory();
        customMockConnectionFactory.setData(new ByteArrayInputStream("It works".getBytes()));

        WebClient2 client = new WebClient2();
        String workingContent = client.getContent(customMockConnectionFactory);

        assertEquals("It works", workingContent);
    }
}
