package com.projectbusan.bokjido.controller;

import com.projectbusan.bokjido.dto.FacilityDTO;
import com.projectbusan.bokjido.entity.Facility;
import com.projectbusan.bokjido.service.FacilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "3. 복지 시설 페이지", description = "시설 관련 API")
@RestController
@RequestMapping("/api/facility")
@RequiredArgsConstructor
public class FacilityController {
    private final FacilityService facilityService;

    // <<-- 복지 시설을 DB에 create 처리 -->>
    @Operation(summary = "복지 시설 DB에 생성")
    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid FacilityDTO.FacilityDto facilityDto) {
        facilityService.create(facilityDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // <<-- 복지 시설 단일 조회 By 아이디 -->>
    @Operation(summary = "시설 아이디를 통해 단일 조회")
    @GetMapping("/")
    public @ResponseBody ResponseEntity searchById(@RequestParam(value = "id") Long id, Model model) {
        Optional<Facility> facilityList;

        try {
            facilityList = facilityService.searchById(id);
        } catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } return new ResponseEntity(facilityList, HttpStatus.OK);
    }

    // <<-- DB에 있는 전체 복지 시설 조회 -->>
    @Operation(summary = "DB의 전체 복지 시설 조회")
    @GetMapping("/loadall")
    public @ResponseBody ResponseEntity allload() {
        List<Facility> facilityList;

        try {
            facilityList = facilityService.allload();
        } catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } return new ResponseEntity(facilityList, HttpStatus.OK);
    }
}
