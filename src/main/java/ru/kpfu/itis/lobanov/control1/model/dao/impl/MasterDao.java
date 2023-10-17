package ru.kpfu.itis.lobanov.control1.model.dao.impl;

import ru.kpfu.itis.lobanov.control1.model.dao.Dao;
import ru.kpfu.itis.lobanov.control1.model.entity.Master;
import ru.kpfu.itis.lobanov.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterDao implements Dao<Master> {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public Master get(int id) {
        try {
            String sql = "SELECT * from masters where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                if (resultSet.next()) {
                    return new Master(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("lastname")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get master from DB.", e);
        }
    }

    public Master get(String name, String lastname) {
        try {
            String sql = "SELECT * from masters where name = ? and lastname = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                if (resultSet.next()) {
                    return new Master(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("lastname")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get master from DB.", e);
        }
    }

    @Override
    public List<Master> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from masters";
            ResultSet resultSet = statement.executeQuery(sql);
            List<Master> masters = new ArrayList<>();

            if (resultSet != null) {
                while (resultSet.next()) {
                    masters.add(
                            new Master(
                                    resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getString("lastname")
                            )
                    );
                }
            }
            return masters;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get master list from DB.", e);
        }
    }

    @Override
    public void save(Master master) {
        String sql = "insert into masters (name, lastname) values (?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, master.getName());
            preparedStatement.setString(2, master.getLastname());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Can't save master into DB.", e);
        }
    }
}
