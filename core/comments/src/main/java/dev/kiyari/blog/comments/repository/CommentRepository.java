package dev.kiyari.blog.comments.repository;

import dev.kiyari.blog.comments.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Set<Comment> getAllByPostId(Long postId);
}
