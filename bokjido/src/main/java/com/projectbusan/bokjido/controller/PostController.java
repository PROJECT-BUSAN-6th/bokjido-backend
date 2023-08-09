package com.projectbusan.bokjido.controller;

import com.projectbusan.bokjido.dto.PostDTO;
import com.projectbusan.bokjido.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "2. 게시글 페이지", description = "게시글 관련 API")
@Controller
@RequestMapping("/api/service")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    // <<-- 게시글 작성 요청 처리 -->>
    @Operation(summary = "게시글 작성")
    @PostMapping("/post")
    public ResponseEntity<Void> post(@RequestBody @Valid PostDTO.PostDto postDto) {
        postService.post(postDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
