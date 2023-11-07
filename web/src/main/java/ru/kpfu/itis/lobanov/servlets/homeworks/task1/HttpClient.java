package ru.kpfu.itis.lobanov.servlets.homeworks.task1;

import java.io.IOException;
import java.util.Map;

public interface HttpClient {
    String get(String url, Map<String, String> params) throws IOException;
    String post(String url, Map<String, String> params) throws IOException;
    String put(String url, Map<String, String> params) throws IOException;
    String delete(String url, Map<String, String> params) throws IOException;
}
