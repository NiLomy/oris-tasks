package ru.kpfu.itis.lobanov.control1.model.dao;

import java.util.List;

public interface Dao<T> {
    T get(int id);
    List<T> getAll();
    void save(T t);
}
