package org.blogplatform.service;

import jakarta.persistence.EntityExistsException;
import org.blogplatform.exception.EntityNotFoundException;
import org.blogplatform.model.Blog;
import org.blogplatform.repository.BlogRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepo blogRepo;

    public BlogServiceImpl(BlogRepo blogRepo) {
        this.blogRepo = blogRepo;
    }

    @Override
    public ResponseEntity<String> saveBlog(Blog blog) {
        if(blogRepo.existsById(blog.getId())) throw new EntityExistsException("Blog already exists");
        blogRepo.save(blog);
        return ResponseEntity.status(HttpStatus.OK).body("Blog has been posted.");
    }

    @Override
    public ResponseEntity<String> deleteBlog(Integer id) {
        if (!blogRepo.existsById(id)) throw new EntityNotFoundException("Blog does not exist.");
        blogRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Blog has been deleted.");
    }

    @Override
    public ResponseEntity<String> getBlog(Integer id) {
        Blog blog = blogRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Blog not found"));
        return ResponseEntity.status(HttpStatus.OK).body("Blog has been deleted. "+ blog.toString());
    }

    @Override
    public ResponseEntity<String> updateBlog(Integer id, String body) {
        Blog blog = blogRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Blog not found"));
        blog.setContent(body);
        blogRepo.save(blog);
        return ResponseEntity.status(HttpStatus.OK).body("Blog has been updated.");
    }
}
