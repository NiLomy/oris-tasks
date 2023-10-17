package ru.kpfu.itis.lobanov.control1.model.entity;

import java.sql.Timestamp;

public class Appointment {
    private int id;
    private int masterId;
    private int serviceId;
    private Timestamp timestamp;
    private String phone;

    public Appointment(int masterId, int serviceId, Timestamp timestamp, String phone) {
        this.masterId = masterId;
        this.serviceId = serviceId;
        this.timestamp = timestamp;
        this.phone = phone;
    }

    public Appointment(int id, int masterId, int serviceId, Timestamp timestamp, String phone) {
        this.id = id;
        this.masterId = masterId;
        this.serviceId = serviceId;
        this.timestamp = timestamp;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
