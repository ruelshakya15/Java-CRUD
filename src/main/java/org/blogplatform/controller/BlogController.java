package org.blogplatform.controller;

import org.blogplatform.model.Blog;
import org.blogplatform.model.User;
import org.blogplatform.repository.UserRepository;
import org.blogplatform.service.BlogServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogServiceImpl blogService;
    private final UserRepository userRepo;

    public BlogController(BlogServiceImpl blogService, UserRepository userRepository, UserRepository userRepo) {
        this.blogService = blogService;
        this.userRepo = userRepo;
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<String> getBlogById(@PathVariable("id") int id) {
        return blogService.getBlog(id);
    }

    @PostMapping(value = "/createBlog", consumes = "multipart/form-data")
    public ResponseEntity<String> saveBlog(
            @RequestParam("content") String content,
            @RequestParam("user_id") Integer userId,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        // Create a Blog object
        Blog blog = new Blog();
        blog.setContent(content);

        // Set the user
        User user = new User();
        user.setId(userId);
        blog.setUser(user);

        // Call the service
        return blogService.saveBlog(blog, image);
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
