package me.kzv.mocks.web.mock;

import me.kzv.mocks.web.ConnectionFactory;

import java.io.InputStream;

public class CustomMockConnectionFactory implements ConnectionFactory {
    private InputStream inputStream;

    public void setData(InputStream stream) {
        this.inputStream = stream;
    }

    public InputStream getData() {
        return inputStream;
    }
}
