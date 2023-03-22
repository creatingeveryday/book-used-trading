package com.together.trading.controller;

import com.together.trading.post.domain.PostRepository;
import com.together.trading.post.domain.dto.PostDto;
import com.together.trading.post.domain.entity.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    public void clearData() {
        postRepository.deleteAll();
    }


    @Test
    public void savePost() {
        //given
        String title = "제목";
        String content = "내용";
        Long WriterId = 2L;

        PostDto postDto = PostDto.builder()
                .title(title)
                .content(content)
                .writerId(WriterId)
                .build();

        String url = "http://localhost:" + port + "/post/save";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, postDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Post> list = postRepository.findAll();
        assertThat(list.get(0).getTitle()).isEqualTo(title);
        assertThat(list.get(0).getContent()).isEqualTo(content);
        assertThat(list.get(0).getWriterId()).isEqualTo(WriterId);

    }

    @Test
    public void updatePost() {
        //given
        String title = "제목";
        String content = "내용";
        Long WriterId = 2L;

        PostDto postDto = PostDto.builder()
                .title(title)
                .content(content)
                .writerId(WriterId)
                .build();

        Post post = postRepository.save(postDto.toEntity());

        Long updateId = post.getId();

        String updatedTitle = "titleChanged";
        String updatedContent = "contentChanged";

        PostDto updatedPostDto = PostDto.builder()
                .title(updatedTitle)
                .content(updatedContent)
                .build();

        String url = "http://localhost:" + port + "/post/" + updateId;

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(updatedPostDto),
                Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        Post target = postRepository.findById(updateId).orElseThrow(() ->
                new IllegalArgumentException("게시글이 존재하지 않습니다")
        );
        assertThat(target.getTitle()).isEqualTo(updatedTitle);
        assertThat(target.getContent()).isEqualTo(updatedContent);

    }




}
