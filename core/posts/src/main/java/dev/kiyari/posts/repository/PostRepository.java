package dev.kiyari.posts.repository;

import dev.kiyari.posts.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Set<Post> findAllByAuthorId(Long authorId);
    boolean existsByAuthorId(Long authorId);
}
