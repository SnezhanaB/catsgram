package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;

import java.util.*;

@Service
public class UserService {
    private final Set<User> users = new HashSet<>();

    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    public User create(User user) throws InvalidEmailException, UserAlreadyExistException {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new InvalidEmailException("У пользователя должен быть заполнен email");
        }
        if (users.contains(user)) {
            throw new UserAlreadyExistException("Пользователь с таким email уже существует");
        }
        users.add(user);
        return user;
    }

    public User update(User user) throws InvalidEmailException {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new InvalidEmailException("У пользователя должен быть заполнен email");
        }
        users.add(user);
        return user;
    }

    public Optional<User> findUserByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }
}
