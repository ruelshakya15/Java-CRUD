package org.blogplatform.repository;

import org.blogplatform.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepo extends JpaRepository<Blog, Integer> {

}
