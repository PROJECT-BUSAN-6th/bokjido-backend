package com.projectbusan.bokjido.controller;

import com.projectbusan.bokjido.dto.PostDTO;
import com.projectbusan.bokjido.entity.Post;
import com.projectbusan.bokjido.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "2. 게시글 페이지", description = "게시글 관련 API")
@RestController
@RequestMapping("/api/service")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "http://localhost:8000") // CORS 정책으로 인해 localhost:8000으로 요청은 받는다는 코드
public class PostController {
    private final PostService postService;


    // <<-- 게시글 작성 요청 처리 -->>
    @Operation(summary = "게시글 작성")
    @PostMapping("/post")
    public ResponseEntity<Void> post(@RequestBody @Valid PostDTO.PostDto postDto) {
        postService.post(postDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // <<-- 전체 게시글 조회 -->>
    @Operation(summary = "전체 게시글 조회")
    @GetMapping("/loadall")
    public @ResponseBody ResponseEntity loadAll() {
        List<Post> postList;

        try{
            postList = postService.loadAll();
        } catch(IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } return new ResponseEntity(postList, HttpStatus.OK);
    }
}
