package com.projectbusan.bokjido.controller;

import com.projectbusan.bokjido.auth.UserDetailsImpl;
import com.projectbusan.bokjido.dto.CommentCreateRequestDTO;
import com.projectbusan.bokjido.dto.CommentResponseDTO;
import com.projectbusan.bokjido.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "댓글 페이지", description = "질의응답의 댓글 관련 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "댓글 DB에 저장")
    @PostMapping("user/qna/{qnaId}/comment/create")
    public ResponseEntity<Long> createComment(@PathVariable Long qnaId, @Valid @RequestBody CommentCreateRequestDTO commentCreateRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(commentService.createComment(qnaId, userDetails.getUser(), commentCreateRequestDTO));
    }

    @Operation(summary = "qnaId에 해당하는 질의응답 게시물의 댓글 조회")
    @GetMapping("/qna/{qnaId}/comment")
    public ResponseEntity<Page<CommentResponseDTO>> getComment(@PathVariable Long qnaId, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(commentService.getComment(qnaId, pageable));
    }

    @Operation(summary = "질의응답 게시물의 로그인한 사용자가 작성한 댓글 조회")
    @GetMapping("/user/comment")
    public ResponseEntity<Page<CommentResponseDTO>> getCommentByUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(commentService.getCommentByUser(userDetails.getUser(), pageable));
    }

    @Operation(summary = "로그인한 사용자가 작성한 댓글 삭제")
    @DeleteMapping("/comment/{commentId}/delete")
    public ResponseEntity<Long> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(commentService.deleteComment(commentId, userDetails.getUser()));
    }
}

