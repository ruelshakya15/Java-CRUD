package org.blogplatform.service;

import jakarta.persistence.EntityExistsException;
import org.blogplatform.exception.EntityNotFoundException;
import org.blogplatform.model.Comment;
import org.blogplatform.repository.CommentRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;

    public CommentServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public ResponseEntity<String> saveComment(Comment comment) {
        if(commentRepo.existsById(comment.getId())) throw new EntityExistsException("Comment already exists");
        commentRepo.save(comment);
        return ResponseEntity.ok("Comment saved");
    }

    @Override
    public ResponseEntity<String> deleteComment(Integer id) {
        if (!commentRepo.existsById(id)) throw new EntityNotFoundException("Comment does not exist.");
        commentRepo.deleteById(id);
        return ResponseEntity.ok("Comment deleted");
    }

    @Override
    public ResponseEntity<String> getComment(Integer id) {
        if (!commentRepo.existsById(id)) throw new EntityNotFoundException("Comment does not exist.");
        Comment comment = commentRepo.findById(id).get();
        return ResponseEntity.ok(comment.toString());
    }

    @Override
    public ResponseEntity<String> updateComment(Integer id, String body) {
        if (!commentRepo.existsById(id)) throw new EntityNotFoundException("Comment does not exist.");

        Comment comment = commentRepo.findById(id).get();
        comment.setContent(body);
        commentRepo.save(comment);
        return ResponseEntity.ok("Comment updated");
    }
}
