package ru.kpfu.itis.lobanov;

import ru.kpfu.itis.lobanov.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/test",
                "postgres",
                "nikita2004"
        );

        Statement statement = connection.createStatement();
        String sql = "SELECT * from users";
        ResultSet resultSet = statement.executeQuery(sql);
        List<User> users = new ArrayList<>();

        if (resultSet != null) {
            while (resultSet.next()) {
                users.add(
                        new User(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("lastname"),
                                resultSet.getString("email"),
                                resultSet.getString("login"),
                                resultSet.getString("password")
                        )
                );
            }
        }
        System.out.println(users.size());
    }
}
