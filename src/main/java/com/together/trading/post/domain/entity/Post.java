package com.together.trading.post.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "POST")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", length = 300, nullable = false)
    private String title;

    @Column(name = "CONTENT", columnDefinition = "TEXT")
    private String content;

    @Column(name = "WRITER_ID")
    private Long writerId;

    @Builder
    public Post(String title, String content, Long writerId) {
        this.title = title;
        this.content = content;
        this.writerId = writerId;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
