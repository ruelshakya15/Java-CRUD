package org.blogplatform.service;

import jakarta.persistence.EntityExistsException;
import org.blogplatform.exception.EntityNotFoundException;
import org.blogplatform.model.Blog;
import org.blogplatform.model.User;
import org.blogplatform.repository.BlogRepo;
import org.blogplatform.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepo blogRepo;
    private final UserRepository userRepo;

    public BlogServiceImpl(BlogRepo blogRepo, UserRepository userRepo) {
        this.blogRepo = blogRepo;
        this.userRepo = userRepo;
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
        return ResponseEntity.status(HttpStatus.OK).body("Blog retrieved "+ blog.toString());
    }

    @Override
    public ResponseEntity<String> saveBlog(Blog blog, MultipartFile image) {
        if (blog.getUser() == null || blog.getUser().getId() == null) {
            throw new IllegalArgumentException("User ID must be provided");
        }

        // Ensure the user exists
        User user = userRepo.findById(blog.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Associate the blog with the user
        blog.setUser(user);

        // Handle the image if present
        if (image != null && !image.isEmpty()) {
            try {
                String uploadsDir = "uploads/";
                Path imagePath = Paths.get(uploadsDir + image.getOriginalFilename());

                // Ensure the uploads directory exists
                Files.createDirectories(imagePath.getParent());

                // Save the image to the filesystem
                Files.write(imagePath, image.getBytes());

                // Set the image path in the blog entity
                blog.setThumbnailUrl(imagePath.toString());
            } catch (IOException e) {
                throw new RuntimeException("Failed to save image", e);
            }
        }

        // Save the blog
        blogRepo.save(blog);

        return ResponseEntity.status(201).body("Blog created successfully.");
    }

    @Override
    public ResponseEntity<String> updateBlog(Integer id, String body) {
        Blog blog = blogRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Blog not found"));
        blog.setContent(body);
        blogRepo.save(blog);
        return ResponseEntity.status(HttpStatus.OK).body("Blog has been updated.");
    }
}
