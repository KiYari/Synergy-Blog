package dev.kiyari.posts.service.impl;

import dev.kiyari.posts.model.entity.Post;
import dev.kiyari.posts.repository.PostRepository;
import dev.kiyari.posts.security.repository.UserRepository;
import dev.kiyari.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public Post get(Long id) {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("Invalid post id");
        }

        if (!postRepository.existsById(id)) {
            throw new IllegalArgumentException("There is no post with id " + id);
        }

        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot create post due to unexpected behaviour"));

    }

    @Override
    public Post create(Post post) {
        if (post == null || post.equals(new Post())) {
            throw new IllegalArgumentException("Invalid post");
        }

        if (post.getAuthorId() == null || post.getAuthorId() < 1 || !userRepository.existsById(post.getAuthorId())) {
            throw new IllegalArgumentException("Illegal post author");
        }

        if (post.getId() != null && postRepository.existsById(post.getId())) {
            throw new IllegalArgumentException("Post already exists");
        }

        return postRepository.save(post);
    }
}
