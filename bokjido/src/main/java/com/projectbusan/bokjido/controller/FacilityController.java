package com.projectbusan.bokjido.controller;

import com.projectbusan.bokjido.auth.UserDetailsImpl;
import com.projectbusan.bokjido.dto.FacilityDTO;
import com.projectbusan.bokjido.dto.FacilityReviewDTO;
import com.projectbusan.bokjido.entity.Facility;
import com.projectbusan.bokjido.entity.FacilityReview;
import com.projectbusan.bokjido.service.WelfareFacility;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Tag(name = "3. 복지 시설 페이지", description = "시설 관련 API")
@RestController
@RequestMapping("/api/facility")
@RequiredArgsConstructor
public class FacilityController {
    private final WelfareFacility welfareFacility;

    // <<-- 복지 시설을 DB에 create 처리 -->>
    @Operation(summary = "복지 시설 DB에 생성")
    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid FacilityDTO.FacilityDto facilityDto) {
        welfareFacility.create(facilityDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // <<-- 복지 시설 단일 조회 By 아이디 -->>
    @Operation(summary = "시설 아이디를 통해 단일 조회")
    @GetMapping("/")
    public @ResponseBody ResponseEntity searchById(@RequestParam(value = "id") Long id, Model model) {
        Optional<Facility> facilityList;

        try {
            facilityList = welfareFacility.searchById(id);
        } catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } return new ResponseEntity(facilityList, HttpStatus.OK);
    }

    // <<-- 복지 시설 단일 조회 By 주소 -->>
    @Operation(summary = "해당 주소를 포함하는 시설 전체 조회")
    @GetMapping("/search/{location}")
    public @ResponseBody ResponseEntity searchByLocation(@RequestParam(value = "location") String location) {
        List<Facility> facilityList = welfareFacility.searchByLocation(location);

        return ResponseEntity.ok(facilityList);
    }

    // <<-- DB에 있는 전체 복지 시설 조회 -->>
    @Operation(summary = "DB의 전체 복지 시설 조회")
    @GetMapping("/loadall")
    public @ResponseBody ResponseEntity loadAll() {
        List<Facility> facilityList;
        try {
            facilityList = welfareFacility.loadAll();
        } catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } return new ResponseEntity(facilityList, HttpStatus.OK);
    }

    // <<-- 복지 시설 리뷰 작성 -->>
    @Operation(summary = "복지 시설 리뷰 작성")
    @PostMapping("/review/create")
    public ResponseEntity<Void> reviewCreate(@Valid @RequestBody FacilityReviewDTO.FacilityReviewRequestDTO facilityReviewRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        welfareFacility.createReview(userDetails.getUser(), facilityReviewRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // <<-- 복지 시설 리뷰 조회 -->>
    @Operation(summary = "복지 시설 리뷰 조회")
    @GetMapping("/review/")
    public Stream<FacilityReviewDTO.FacilityReviewResponseDTO> getReview(@RequestParam(value = "facilityId") Long facilityId) {
        List <FacilityReview> facilityReviews;
        facilityReviews = welfareFacility.getReview(facilityId);

        return facilityReviews.stream().map(FacilityReviewDTO.FacilityReviewResponseDTO::toDto);
    }
}
