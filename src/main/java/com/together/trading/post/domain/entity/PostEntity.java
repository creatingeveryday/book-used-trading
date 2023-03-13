package com.together.trading.post.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "POST")
@Entity
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Long writerId;

    @Builder
    public PostEntity(String title, String content, Long writerId) {
        this.title = title;
        this.content = content;
        this.writerId = writerId;
    }
}
