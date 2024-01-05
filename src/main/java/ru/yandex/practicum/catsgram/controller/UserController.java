package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.InvalidEmailException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.model.UserAlreadyExistException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestMapping("/users")
@RestController
public class UserController {
    private final Set<User> users = new HashSet<>();

    @GetMapping()
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @PostMapping()
    public User create(@RequestBody User user) throws InvalidEmailException, UserAlreadyExistException {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new InvalidEmailException("У пользователя должен быть заполнен email");
        }
        if (users.contains(user)) {
            throw new UserAlreadyExistException("Пользователь с таким email уже существует");
        }
        users.add(user);
        return user;
    }

    @PutMapping()
    public User update(@RequestBody User user) throws InvalidEmailException {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new InvalidEmailException("У пользователя должен быть заполнен email");
        }
        users.add(user);
        return user;
    }
}
