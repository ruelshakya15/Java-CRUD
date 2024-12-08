package org.blogplatform.controller;

import org.blogplatform.model.Blog;
import org.blogplatform.service.BlogServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogServiceImpl blogService;

    public BlogController(BlogServiceImpl blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<String> getBlogById(@PathVariable("id") int id) {
        return blogService.getBlog(id);
    }

    @PostMapping("/createBlog")
    public ResponseEntity<String> saveBlog(@RequestBody Blog blog) {
        return blogService.saveBlog(blog);
    }

    @PutMapping("/updateBlog/{id}")
    public ResponseEntity<String> updateBlog(@PathVariable("id") int id, @RequestBody String content) {
        return blogService.updateBlog(id,content);
    }

    @DeleteMapping("/deleteBlog/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable("id") int id) {
        return blogService.deleteBlog(id);
    }
}
