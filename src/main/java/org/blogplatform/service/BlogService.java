package org.blogplatform.service;

import org.blogplatform.model.Blog;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BlogService {
    public ResponseEntity<String> saveBlog(Blog blog);

    public ResponseEntity<String> deleteBlog(Integer id);

    public ResponseEntity<String> getBlog(Integer id);

    public ResponseEntity<String> updateBlog(Integer id,String body);
}
