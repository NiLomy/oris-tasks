package ru.kpfu.itis.lobanov.servlets.service;

import ru.kpfu.itis.lobanov.servlets.dto.UserDto;
import ru.kpfu.itis.lobanov.servlets.model.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto get(int id);
    void save(User user);
}
