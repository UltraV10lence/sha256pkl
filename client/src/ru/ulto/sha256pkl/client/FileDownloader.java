package ru.ulto.sha256pkl.client;

import java.net.URL;

public class FileDownloader {
    private FileDownloader() {}

    public static byte[] GetFile(String url) {
        try {
            //noinspection resource
            return new URL(url).openStream().readAllBytes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
