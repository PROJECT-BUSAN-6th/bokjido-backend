package com.projectbusan.bokjido.service;

import com.projectbusan.bokjido.dto.FacilityDTO;
import com.projectbusan.bokjido.entity.Facility;
import com.projectbusan.bokjido.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FacilityService {
    @Autowired
    private FacilityRepository facilityRepository;

    // <<-- 복지 건물 생성 -->>
    @Transactional
    public void create(FacilityDTO.FacilityDto facilityDto) {
        Facility facility = Facility.create(facilityDto);
        facilityRepository.save(facility);
    }

    // <<-- 복지 건물 단일 조회 By ID -->>
    @Transactional
    public Optional<Facility> searchById(Long id) { return facilityRepository.findById(id); }

    // <<-- 전체 복지 건물 조회 -->>
    public List<Facility> allload() { return facilityRepository.findAll(); }
}
