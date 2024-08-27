package dev.kiyari.blog.comments.service;

import dev.kiyari.blog.comments.model.entity.Comment;

import java.util.Set;

public interface CommentService {
    /**
     * Получение поста по id
     *
     * @param id       id комментария
     * @return Выдает комментарий по id
     */
    public Comment get(Long id);

    /**
     * Создание нового поста
     *
     * @param comment - создаваемый комментарий
     * @return Выдает созданный комментарий
     */
    public Comment save(Comment comment);

    /**
     *
     * @param postId - id поста
     * @return Выдает сет, состоящий из комментариев поста
     * */
    public Set<Comment> getCommentsByPostId(Long postId);
}
