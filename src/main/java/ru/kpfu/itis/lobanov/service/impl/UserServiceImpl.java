package ru.kpfu.itis.lobanov.service.impl;

import ru.kpfu.itis.lobanov.dao.Dao;
import ru.kpfu.itis.lobanov.dao.impl.UserDaoImpl;
import ru.kpfu.itis.lobanov.dto.UserDto;
import ru.kpfu.itis.lobanov.model.User;
import ru.kpfu.itis.lobanov.service.UserService;
import ru.kpfu.itis.lobanov.util.PasswordUtil;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final Dao<User> dao = new UserDaoImpl();
    @Override
    public List<UserDto> getAll() {
        return dao.getAll().stream().map(
                u -> new UserDto(u.getName(), u.getLastname())
        ).collect(Collectors.toList());
    }

    @Override
    public UserDto get(int id) {
        User user = dao.get(id);
        return new UserDto(user.getName(), user.getLastname());
    }

    @Override
    public void save(User user) {
        //user.setPassword();
        user.setPassword(PasswordUtil.encrypt(user.getPassword()));
        dao.save(user);
    }
}
