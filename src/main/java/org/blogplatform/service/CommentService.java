package org.blogplatform.service;

import org.blogplatform.model.Blog;
import org.blogplatform.model.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public ResponseEntity<String> saveComment(Comment comment);

    public ResponseEntity<String> deleteComment(Integer id);

    public ResponseEntity<String> getComment(Integer id);

    public ResponseEntity<String> updateComment(Integer id,String body);
}
