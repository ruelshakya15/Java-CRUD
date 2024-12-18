package org.blogplatform.controller;

import org.blogplatform.model.Blog;
import org.blogplatform.model.Comment;
import org.blogplatform.service.BlogServiceImpl;
import org.blogplatform.service.CommentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.blogplatform.model.Role;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<String> getCommentById(@PathVariable("id") int id) {
        System.out.println("in comment controller:" + id);
        return commentService.getComment(id);
    }

    @PostMapping("/createComment")
    public ResponseEntity<String> saveComment(@RequestBody Comment comment) {
        return commentService.saveComment(comment);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/updateComment/{id}")
    public ResponseEntity<String> updateComment(@PathVariable("id") int id, @RequestBody String comment) {
        return commentService.updateComment(id, comment);
    }

    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") int id) {
        return commentService.deleteComment(id);
    }
}
