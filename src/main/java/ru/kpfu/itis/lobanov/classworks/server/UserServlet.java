package ru.kpfu.itis.lobanov.classworks.server;

import ru.kpfu.itis.lobanov.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "userServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {

    private static final List<UserDto> USERS = initUsers();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", USERS);
        req.getRequestDispatcher("users.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private static List<UserDto> initUsers() {
        List<UserDto> list = new ArrayList<>();
        list.add(new UserDto("Ivan", "Pupkin"));
        list.add(new UserDto("Katya", "Ivanova"));
        return list;
    }
}
