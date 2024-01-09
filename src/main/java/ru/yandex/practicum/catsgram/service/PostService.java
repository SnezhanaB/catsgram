package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Post> findAll(Integer size, Integer from, String sort) {
        return posts.stream().sorted((p0, p1) -> {
            int comp = p0.getCreationDate().compareTo(p1.getCreationDate()); //прямой порядок сортировки
            if(sort.equals("desc")){
                comp = -1 * comp; //обратный порядок сортировки
            }
            return comp;
        }).skip(from).limit(size).collect(Collectors.toList());
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