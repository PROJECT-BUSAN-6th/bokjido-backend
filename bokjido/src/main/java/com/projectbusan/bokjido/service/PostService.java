package com.projectbusan.bokjido.service;

import com.projectbusan.bokjido.dto.PostDTO;
import com.projectbusan.bokjido.entity.Post;
import com.projectbusan.bokjido.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
