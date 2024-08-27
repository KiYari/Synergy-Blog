package dev.kiyari.blog.comments.repository;

import dev.kiyari.posts.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
