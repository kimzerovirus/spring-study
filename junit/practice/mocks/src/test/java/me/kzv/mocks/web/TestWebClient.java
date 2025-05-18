package me.kzv.mocks.web;

import me.kzv.mocks.web.mock.CustomMockConnectionFactory;
import me.kzv.mocks.web.mock.CustomMockInputStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClient {
    @Test
    public void testGetContentOk() throws Exception {
        CustomMockInputStream mockStream = new CustomMockInputStream();
        mockStream.setBuffer("It works");

        CustomMockConnectionFactory customMockConnectionFactory = new CustomMockConnectionFactory();
        customMockConnectionFactory.setData(mockStream);

        WebClient2 client = new WebClient2();
        String workingContent = client.getContent(customMockConnectionFactory);

        assertEquals("It works", workingContent);
        mockStream.verify();
    }
}
