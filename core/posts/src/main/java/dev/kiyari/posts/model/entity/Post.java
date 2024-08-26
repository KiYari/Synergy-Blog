package dev.kiyari.posts.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "posts")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_seq")
    @SequenceGenerator(name = "post_id_seq", sequenceName = "post_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "title", nullable = false)
    @Setter
    private String title;

    @Column(name = "content")
    @Setter
    private String content;

    @Column(name = "authorId", nullable = false)
    private Long authorId;

    @Setter
    @Column(name = "isHidden")
    private Boolean isHidden;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(content, post.content) && Objects.equals(authorId, post.authorId) && Objects.equals(isHidden, post.isHidden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, authorId, isHidden);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", authorId=" + authorId +
                ", isHidden=" + isHidden +
                '}';
    }
}
