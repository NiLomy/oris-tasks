package ru.kpfu.itis.lobanov.homeworks.task2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.stream.Collectors;

@WebServlet(name = "requestInfoServlet", urlPatterns = "/request")
public class RequestInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html><html><head><title>First Web Page</title></head><body><div style=\"font-family:MV BOLI; font-size:300%; text-align:center\">Hello!!!</div><div style=\"font-family:segoe print; font-size:200%; text-align:center\">You are on my first webpage!</div><center><img src=\"https://bigheadaffiliate.com/wp-content/uploads/hacker.jpg\"></center></body></html>");
        showRequestInfo(req, "GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        showRequestInfo(req, "POST");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
        showRequestInfo(req, "PUT");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
        showRequestInfo(req, "DELETE");
    }

    private void showRequestInfo(HttpServletRequest req, String method) throws IOException {
        String parameters = getRequestParameters(req);
        String body = getRequestBody(req);
        String headers = getRequestHeaders(req);

        System.out.println("Method:\n" + method);
        System.out.println("Parameters:\n" + parameters);
        System.out.println("Body:\n" + body);
        System.out.println("Headers:\n" + headers);
        System.out.println();
    }

    private String getRequestParameters(HttpServletRequest req) {
        StringBuilder parameters = new StringBuilder();
        req.getParameterMap().forEach((k, v) -> parameters.append(k).append("=").append(Arrays.toString(v)));
        return parameters.toString();
    }

    private String getRequestBody(HttpServletRequest req) throws IOException {
        return req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    }

    private String getRequestHeaders(HttpServletRequest req) {
        StringBuilder headers = new StringBuilder();
        Enumeration<String> en = req.getHeaderNames();

        while (en.hasMoreElements()) {
            String headerName = en.nextElement();
            headers.append(headerName);
            headers.append("=");
            headers.append(req.getHeader(headerName));
            if (en.hasMoreElements()) {
                headers.append("\n");
            }
        }
        return headers.toString();
    }
}
