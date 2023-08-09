package com.projectbusan.bokjido.service;

import com.projectbusan.bokjido.dto.PostDTO;
import com.projectbusan.bokjido.entity.Post;
import com.projectbusan.bokjido.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    @Autowired
    private PostRepository postRepository;

    // <<-- 게시글 작성 -->>
    @Transactional
    public void post(PostDTO.PostDto postDto) {
        Post post = Post.register(postDto);
        postRepository.save(post);
    }

    // <<-- 전체 게시글 조회 -->>

    public List<Post> allload() {
        return postRepository.findAll();
    }
}
