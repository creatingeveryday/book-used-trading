package com.together.trading.controller;

import com.together.trading.post.domain.dto.PostDto;
import com.together.trading.post.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/save")
    public Long save(@RequestBody PostDto postDto) {
        return postService.save(postDto);
    }

    @GetMapping("/{id}")
    public PostDto showPostById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostDto postDto) {
        return postService.update(id, postDto);
    }


}
