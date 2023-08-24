package com.projectbusan.bokjido.controller;

import com.projectbusan.bokjido.auth.UserDetailsImpl;
import com.projectbusan.bokjido.dto.*;
import com.projectbusan.bokjido.service.QnaService;
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
public class QnaController {
    private final QnaService qnaService;

    @PostMapping("user/qna/create")
    public ResponseEntity<Long> createQna(@Valid @RequestBody QnaCreateRequestDTO qnaCreateRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(qnaService.createQna(userDetails.getUser(), qnaCreateRequestDTO));
    }

    @GetMapping("/qna")
    public ResponseEntity<Page<QnaDetailsResponseDTO>> getQnaAll(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(qnaService.getQnaAll(pageable));
    }

    @GetMapping("/qna/{qnaId}")
    public ResponseEntity<QnaDetailsResponseDTO> getQnaById(@PathVariable Long qnaId){
        return ResponseEntity.ok(qnaService.getQnaById(qnaId));
    }
}
