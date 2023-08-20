package com.projectbusan.bokjido.controller;

import com.projectbusan.bokjido.auth.UserDetailsImpl;
import com.projectbusan.bokjido.dto.*;
import com.projectbusan.bokjido.service.BenefitsService;
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

@Tag(name = "복지 서비스 페이지", description = "복지 서비스 관련 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BenefitController {
    private final BenefitsService benefitsService;

    @Operation(summary = "복지 서비스 생성")
    @PostMapping("/user/service/create")
    public ResponseEntity<Long> createService(@Valid @RequestBody BenefitCreateRequestDTO benefitCreateRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(benefitsService.createService(userDetails.getUser(), benefitCreateRequestDTO));
    }

    @Operation(summary = "복지 서비스 전체 조회 - 지역, 나이, 키워드로 필터링")
    @GetMapping("/service")
    public ResponseEntity<Page<BenefitMainResponseDTO>> getService(@Valid @RequestBody BenefitRequestDTO benefitRequestDTO, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(benefitsService.getService(benefitRequestDTO, pageable));
    }

    @Operation(summary = "복지 서비스 단일 조회")
    @GetMapping("/service/{serviceId}")
    public ResponseEntity<BenefitDetailsResponseDTO> getServiceById(@PathVariable Long serviceId){
        return ResponseEntity.ok(benefitsService.getServiceById(serviceId));
    }


    @Operation(summary = "복지 서비스 사용자 맞춤 조회")
    @GetMapping("/user/service")
    public ResponseEntity<Page<BenefitMainResponseDTO>> getServiceByUser(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(benefitsService.getServiceByUser(userDetails.getUser(), pageable));
    }


    @Operation(summary = "복지 서비스 리뷰 생성")
    @PostMapping("/user/review/service/create")
    public ResponseEntity<Long> createReview(@Valid @RequestBody BenefitReviewRequestDTO benefitReviewRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(benefitsService.createReview(userDetails.getUser(), benefitReviewRequestDTO));
    }


    @Operation(summary = "복지 서비스 리뷰 전체 조회")
    @GetMapping("/user/review/service/{serviceId}")
    public ResponseEntity<Page<BenefitReviewResponseDTO>> getReview(@PathVariable Long serviceId, @PageableDefault(sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(benefitsService.getReview(serviceId, pageable));
    }
}
