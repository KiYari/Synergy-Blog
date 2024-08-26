package dev.kiyari.posts.service.impl;

import dev.kiyari.posts.model.entity.Post;
import dev.kiyari.posts.repository.PostRepository;
import dev.kiyari.posts.security.repository.UserRepository;
import dev.kiyari.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public Post get(Long id) {
        if (!isIdValid(id)) {
            throw new IllegalArgumentException("Invalid post id");
        }

        if (!postRepository.existsById(id)) {
            throw new IllegalArgumentException("There is no post with id " + id);
        }

        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot create post due to unexpected behaviour"));

    }

    @Override
    public Post save(Post post) {
        if (post == null || post.equals(new Post())) {
            throw new IllegalArgumentException("Invalid post");
        }

        if (!isIdValid(post.getAuthorId()) || !userRepository.existsById(post.getAuthorId())) {
            throw new IllegalArgumentException("Illegal post author");
        }

        if (post.getId() != null && postRepository.existsById(post.getId())) {
            Post postInDb = get(post.getId());
            if (postInDb.equals(post)) {
                throw new IllegalArgumentException("Post already exists");
            }
        }

        if (post.getIsHidden() == null) {
            post.setIsHidden(false);
        }

        return postRepository.save(post);
    }

    @Override
    public Post update(Long id, Post post) {
        Post postToSave = new Post(id, post.getTitle(), post.getContent(), post.getAuthorId(), post.getIsHidden());
        Post postInDb = get(id);

        if (!postToSave.getId().equals(postInDb.getId()) || !postToSave.getAuthorId().equals(postInDb.getAuthorId())) {
            throw new IllegalArgumentException("Post id or author id is corrupted.");
        }

        return save(postToSave);
    }

    @Override
    public Post delete(Long id) {
        Post postToDelete = get(id);
        postRepository.delete(postToDelete);
        return postToDelete;
    }

    @Override
    public Set<Post> getAuthorPosts(Long authorId) {
        if (!isIdValid(authorId)) {
            throw new IllegalArgumentException("Illegal author id");
        }

        return postRepository.findAllByAuthorId(authorId).stream()
                .filter(post -> post.getIsHidden() == null || !post.getIsHidden())
                .collect(Collectors.toSet());
    }

    private boolean isIdValid(Long id) {
        return id != null && id > 0;
    }
}
