package com.together.trading.domain;

import com.together.trading.post.domain.PostRepository;
import com.together.trading.post.domain.entity.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PostRepoTest {

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    public void clean() {
        postRepository.deleteAll();
    }

    // TODO: 제목없는 게시글은 불가능

    @Test
    public void save() {
        //given
        String title = "제목";
        String content = "내용";
        Long WriterId = 2L;

        Post post = Post.builder()
                .title(title)
                .content(content)
                .writerId(WriterId)
                .build();

        postRepository.save(post);

        //when
        List<Post> postList = postRepository.findAll();

        //then
        Post savedPost = postList.get(0);
        assertThat(savedPost.getId()).isNotEqualTo(0L);
        assertThat(savedPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(savedPost.getContent()).isEqualTo(post.getContent());
        assertThat(savedPost.getWriterId()).isEqualTo(post.getWriterId());
    }

    @Test
    public void update() {

        //given
        String title = "제목";
        String content = "내용";
        Long WriterId = 2L;

        Post post = Post.builder()
                .title(title)
                .content(content)
                .writerId(WriterId)
                .build();

        Post savedPost = postRepository.save(post);

        String newTitle = "제목수정";
        String newContent = "내용수정";

        //when
        post.update(newTitle, newContent);

        //then
        Post target = postRepository.findById(savedPost.getId()).orElseThrow(() ->
                new IllegalArgumentException("게시글이 존재하지 않습니다")
        );

        assertThat(target.getTitle()).isEqualTo(newTitle);
        assertThat(target.getContent()).isEqualTo(newContent);
    }

    @Test
    public void delete() {
        //given
        String title = "제목";
        String content = "내용";
        Long WriterId = 2L;

        Post post = Post.builder()
                .title(title)
                .content(content)
                .writerId(WriterId)
                .build();

        Post savedPost = postRepository.save(post);

        //when
        postRepository.deleteById(savedPost.getId());

        //then
        boolean isDeleted = postRepository.findById(savedPost.getId()).isEmpty();
        assertThat(isDeleted).isEqualTo(true);
    }



}
