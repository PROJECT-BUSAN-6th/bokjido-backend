package com.projectbusan.bokjido.controller;

import com.projectbusan.bokjido.auth.UserDetailsImpl;
import com.projectbusan.bokjido.dto.*;
import com.projectbusan.bokjido.service.QnaService;
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

@Tag(name = "질의응답 페이지", description = "질의응답 관련 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "http://localhost:8000") // CORS 정책으로 인해 localhost:8000으로 요청은 받는다는 코드
public class QnaController {
    private final QnaService qnaService;

    @Operation(summary = "질의응답 생성")
    @PostMapping("user/qna/create")
    public ResponseEntity<Long> createQna(@Valid @RequestBody QnaCreateRequestDTO qnaCreateRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(qnaService.createQna(userDetails.getUser(), qnaCreateRequestDTO));
    }

    @Operation(summary = "질의응답 글 전체 조회")
    @GetMapping("/qna")
    public ResponseEntity<Page<QnaDetailsResponseDTO>> getQnaAll(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(qnaService.getQnaAll(pageable));
    }

    @Operation(summary = "질의응답 글 단일 조회")
    @GetMapping("/qna/{qnaId}")
    public ResponseEntity<QnaDetailsResponseDTO> getQnaById(@PathVariable Long qnaId){
        return ResponseEntity.ok(qnaService.getQnaById(qnaId));
    }
}
