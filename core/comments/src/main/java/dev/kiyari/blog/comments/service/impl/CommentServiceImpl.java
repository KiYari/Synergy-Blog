package dev.kiyari.blog.comments.service.impl;

import dev.kiyari.blog.comments.model.entity.Comment;
import dev.kiyari.blog.comments.repository.CommentRepository;
import dev.kiyari.blog.comments.repository.PostRepository;
import dev.kiyari.blog.comments.security.repository.UserRepository;
import dev.kiyari.blog.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    @Override
    public Comment get(Long id) {
        if (!isIdValid(id)) {
            throw new IllegalArgumentException("Invalid comment id");
        }

        if (!commentRepository.existsById(id)) {
            throw new IllegalArgumentException("There is no comment with id " + id);
        }

        return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot create post due to unexpected behaviour"));

    }

    @Override
    public Comment save(Comment comment) {
        if (!postRepository.existsById(comment.getPostId())) {
            throw new IllegalArgumentException("There is no post with id " + comment.getPostId());
        }

        if (comment == null || comment.equals(new Comment())) {
            throw new IllegalArgumentException("Invalid comment");
        }

        if (!isIdValid(comment.getAuthorId()) || !userRepository.existsById(comment.getAuthorId())) {
            throw new IllegalArgumentException("Illegal comment author");
        }

        if (comment.getId() != null && commentRepository.existsById(comment.getId())) {
            Comment postInDb = get(comment.getId());
            if (postInDb.equals(comment)) {
                throw new IllegalArgumentException("Comment already exists");
            }
        }

        return commentRepository.save(comment);
    }

    @Override
    public Set<Comment> getCommentsByPostId(Long postId) {
        if (!isIdValid(postId)) {
            throw new IllegalArgumentException("Invalid post id");
        }

        return commentRepository.getAllByPostId(postId);
    }

    private boolean isIdValid(Long id) {
        return id != null && id > 0;
    }
}
