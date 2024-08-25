package dev.kiyari.posts.controller;

import dev.kiyari.posts.model.entity.Post;
import dev.kiyari.posts.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
@Tag(name = "Посты")
public class PostController {
    public final PostService postService;

    @GetMapping
    @Operation(summary = "Получить пост")
    public ResponseEntity<Post> getPost(@RequestParam Long id) {
        return ResponseEntity.ok(postService.get(id));
    }

    @PostMapping
    @Operation(summary = "Создать новый пост")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.save(post));
    }

    @DeleteMapping
    @Operation(summary = "Удалить пост")
    public ResponseEntity<Post> deletePost(@RequestParam Long id) {
        return ResponseEntity.ok(postService.delete(id));
    }

    @PutMapping
    @Operation(summary = "Редактировать пост")
    public ResponseEntity<Post> updatePost(@RequestParam Long id, @RequestBody Post post) {
        return ResponseEntity.ok(postService.update(id, post));
    }
}
