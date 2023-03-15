package com.together.trading.post.domain.service;

import com.together.trading.post.domain.PostRepository;
import com.together.trading.post.domain.dto.PostDto;
import com.together.trading.post.domain.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;


    public Long save(PostDto postDto) {
        return postRepository.save(postDto.toEntity()).getId();
    }

    public PostDto findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다"));

        return new PostDto(post);
    }

    @Transactional
    public Long update(Long id, PostDto postDto) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다"));

        post.update(postDto.getTitle(), postDto.getContent());

        return id;
    }
}
