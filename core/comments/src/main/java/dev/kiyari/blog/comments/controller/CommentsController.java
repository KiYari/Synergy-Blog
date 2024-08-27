package dev.kiyari.blog.comments.controller;

import dev.kiyari.blog.comments.model.entity.Comment;
import dev.kiyari.blog.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentsController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<Comment> getCommentById(@RequestParam Long id) {
        return ResponseEntity.ok(commentService.get(id));
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.save(comment));
    }

    @GetMapping("/byPost")
    public ResponseEntity<Set<Comment>> getCommentsByPostId(@RequestParam Long id) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(id));
    }
}
