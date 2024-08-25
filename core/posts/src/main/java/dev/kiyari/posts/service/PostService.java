package dev.kiyari.posts.service;

import dev.kiyari.posts.model.entity.Post;

import java.util.Optional;

public interface PostService {
    /**
     * Получение поста по id
     *
     * @param id       id Поста
     * @return Выдает пост по id
     */
    public Post get(Long id);

    /**
     * Создание нового поста
     *
     * @param post - создаваемый пост
     * @return Выдает созданный пост
     */
    public Post create(Post post);
}
