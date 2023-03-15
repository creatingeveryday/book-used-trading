package com.together.trading.post.domain.dto;

import com.together.trading.post.domain.entity.Post;
import lombok.*;

@NoArgsConstructor
@Getter
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private Long writerId;

    @Builder
    public PostDto(String title, String content, Long writerId) {
        this.title = title;
        this.content = content;
        this.writerId = writerId;
    }

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writerId = post.getWriterId();
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .writerId(writerId)
                .build();
    }


}
