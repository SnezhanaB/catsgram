package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final List<Post> posts = new ArrayList<>();

    private static Integer globalId = 0;
    private final UserService userService;

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    private static Integer getNextId(){
        return globalId++;
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post create(Post post) throws UserNotFoundException {
        if (userService.findUserByEmail(post.getAuthor()).isEmpty()) {
            throw new UserNotFoundException(String.format(
                "Пользователь %s не найден",
                post.getAuthor()
            ));
        }
        post.setId(getNextId());
        posts.add(post);
        return post;
    }

    public Optional<Post> findById(int id) {
        return posts.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
}