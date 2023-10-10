package ru.kpfu.itis.lobanov.service;

import ru.kpfu.itis.lobanov.dto.UserDto;
import ru.kpfu.itis.lobanov.model.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto get(int id);
    void save(User user);
}
