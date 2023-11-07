package ru.kpfu.itis.lobanov.servlets.control1.model.dao.impl;

import ru.kpfu.itis.lobanov.servlets.control1.model.entity.Service;
import ru.kpfu.itis.lobanov.servlets.dao.Dao;
import ru.kpfu.itis.lobanov.servlets.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao implements Dao<Service> {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public Service get(int id) {
        try {
            String sql = "SELECT * from services where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                if (resultSet.next()) {
                    return new Service(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("duration")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get service from DB.", e);
        }
    }

    public Service get(String name) {
        try {
            String sql = "SELECT * from services where name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                if (resultSet.next()) {
                    return new Service(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("duration")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get service from DB.", e);
        }
    }

    @Override
    public List<Service> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from services";
            ResultSet resultSet = statement.executeQuery(sql);
            List<Service> services = new ArrayList<>();

            if (resultSet != null) {
                while (resultSet.next()) {
                    services.add(
                            new Service(
                                    resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getInt("duration")
                            )
                    );
                }
            }
            return services;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get service list from DB.", e);
        }
    }

    public List<Service> getAllFromMaster(int masterId) {
        try {
            String sql = "SELECT service_id from master_service where master_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, masterId);
            List<Service> services = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    services.add(
                            get(resultSet.getInt("service_id"))
                    );
                }
            }
            return services;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get service list from DB.", e);
        }
    }

    @Override
    public void save(Service service) {
        String sql = "insert into services (name, duration) values (?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, service.getName());
            preparedStatement.setInt(2, service.getDuration());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Can't save service into DB.", e);
        }
    }
}
