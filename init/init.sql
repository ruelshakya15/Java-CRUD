CREATE DATABASE IF NOT EXISTS blog_platform;
USE blog_platform;

CREATE TABLE users (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE blog_posts (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            title VARCHAR(255) NOT NULL,
                            content TEXT NOT NULL,
                            user_id INT NOT NULL,
                            thumbnail_url VARCHAR(255),
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE comments (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          blog_post_id INT NOT NULL,
                          user_id INT NOT NULL,
                          content TEXT NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (blog_post_id) REFERENCES blog_posts(id),
                          FOREIGN KEY (user_id) REFERENCES users(id)
);