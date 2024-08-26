package dev.kiyari.posts.service;

import dev.kiyari.posts.model.entity.Post;

import java.util.Set;

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
    public Post save(Post post);

    /**
     * Создание нового поста
     *
     * @param post - новый вид поста
     * @param id - id поста, который обновляем
     * @return Выдает обновленный пост
     */
    public Post update(Long id, Post post);

    /**
     * Создание нового поста
     *
     * @param id - id пост
     * @return Выдает удаленный пост
     */
    public Post delete(Long id);

    /**
     * Получить все посты одного пользователя
     *
     * @param authorId - id пользователя
     * @return Выдает Set постов пользователя
     * */
    public Set<Post> getAuthorPosts(Long authorId);
}
