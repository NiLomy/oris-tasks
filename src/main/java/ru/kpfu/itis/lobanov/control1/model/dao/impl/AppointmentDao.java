package ru.kpfu.itis.lobanov.control1.model.dao.impl;

import ru.kpfu.itis.lobanov.control1.model.entity.Appointment;
import ru.kpfu.itis.lobanov.control1.model.entity.Master;
import ru.kpfu.itis.lobanov.dao.Dao;
import ru.kpfu.itis.lobanov.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao implements Dao<Appointment> {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public Appointment get(int id) {
        try {
            String sql = "SELECT * from appointments where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                if (resultSet.next()) {
                    return new Appointment(
                            resultSet.getInt("id"),
                            resultSet.getInt("master_id"),
                            resultSet.getInt("service_id"),
                            resultSet.getTimestamp("timestamp"),
                            resultSet.getString("phone")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get appointment from DB.", e);
        }
    }

    @Override
    public List<Appointment> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from appointments";
            ResultSet resultSet = statement.executeQuery(sql);
            List<Appointment> appointments = new ArrayList<>();

            if (resultSet != null) {
                while (resultSet.next()) {
                    appointments.add(
                            new Appointment(
                                    resultSet.getInt("id"),
                                    resultSet.getInt("master_id"),
                                    resultSet.getInt("service_id"),
                                    resultSet.getTimestamp("timestamp"),
                                    resultSet.getString("phone")
                            )
                    );
                }
            }
            return appointments;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get appointment list from DB.", e);
        }
    }

    @Override
    public void save(Appointment appointment) {
        String sql = "insert into appointments (master_id, service_id, time, phone) values (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, appointment.getMasterId());
            preparedStatement.setInt(2, appointment.getServiceId());
            preparedStatement.setTimestamp(3, appointment.getTimestamp());
            preparedStatement.setString(4, appointment.getPhone());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Can't save appointment into DB.", e);
        }
    }
}
