package com.projectbusan.bokjido.controller;

import com.projectbusan.bokjido.auth.UserDetailsImpl;
import com.projectbusan.bokjido.dto.CommentCreateRequestDTO;
import com.projectbusan.bokjido.dto.CommentResponseDTO;
import com.projectbusan.bokjido.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("user/qna/{qnaId}/comment/create")
    public ResponseEntity<Long> createComment(@PathVariable Long qnaId, @Valid @RequestBody CommentCreateRequestDTO commentCreateRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(commentService.createComment(qnaId, userDetails.getUser(), commentCreateRequestDTO));
    }

    @GetMapping("/qna/{qnaId}/comment")
    public ResponseEntity<Page<CommentResponseDTO>> getComment(@PathVariable Long qnaId, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(commentService.getComment(qnaId, pageable));
    }

    @GetMapping("/user/comment")
    public ResponseEntity<Page<CommentResponseDTO>> getCommentByUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(commentService.getCommentByUser(userDetails.getUser(), pageable));
    }

    @DeleteMapping("/comment/{commentId}/delete")
    public ResponseEntity<Long> deleteComment(@PathVariable Long commentId){
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }
}

