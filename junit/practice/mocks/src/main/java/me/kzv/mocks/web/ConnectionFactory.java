package me.kzv.mocks.web;

import java.io.InputStream;

public interface ConnectionFactory {
    InputStream getData() throws Exception;
}