package ru.kpfu.itis.lobanov.homeworks.task3;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final String USERS_STORAGE = "C:\\Users\\loban\\IdeaProjects\\oris-tasks\\src\\main\\java\\ru\\kpfu\\itis\\lobanov\\homeworks\\task3\\users.csv";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("task3/login.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean isUnauthorizedUser = true;

        try (CSVParser csvParser = CSVParser.parse(
                new BufferedReader(
                        new FileReader(USERS_STORAGE)
                ),
                CSVFormat.DEFAULT.withHeader("ID", "Login", "Password"))
        ) {
            for (CSVRecord csvRecord: csvParser.getRecords()) {
                if (csvRecord.get("Login").equalsIgnoreCase(login) && csvRecord.get("Password").equals(password)) {
                    isUnauthorizedUser = false;
                    resp.sendRedirect("/town");
                }
            }
            if (isUnauthorizedUser) {
                resp.sendRedirect("/login");
            }
        }
    }
}
