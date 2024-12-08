package org.blogplatform.service;

import org.blogplatform.model.Blog;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface BlogService {
    public ResponseEntity<String> saveBlog(Blog blog);

    public ResponseEntity<String> deleteBlog(Integer id);

    public ResponseEntity<String> getBlog(Integer id);

    ResponseEntity<String> saveBlog(Blog blog, MultipartFile image);

    public ResponseEntity<String> updateBlog(Integer id, String body);
}
