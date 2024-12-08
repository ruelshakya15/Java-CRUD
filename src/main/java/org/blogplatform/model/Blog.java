package org.blogplatform.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 5000)
    private String content;

    @Column(name = "thumbnail_url", nullable = false, length = 2048)
    private String thumbnailUrl; // Stores the URL or path to the thumbnail image

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", user=" + user +
                '}';
    }
}
