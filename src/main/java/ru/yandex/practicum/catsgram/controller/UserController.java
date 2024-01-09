package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.service.UserService;
import java.util.List;
import java.util.Optional;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping()
    public User create(@RequestBody User user) throws InvalidEmailException, UserAlreadyExistException {
        return userService.create(user);
    }

    @PutMapping()
    public User update(@RequestBody User user) throws InvalidEmailException {
        return userService.update(user);
    }

    @GetMapping("/users/{userEmail}")
    public Optional<User> findUserByEmail(@PathVariable String userEmail) {
        return userService.findUserByEmail(userEmail);
    }
}
