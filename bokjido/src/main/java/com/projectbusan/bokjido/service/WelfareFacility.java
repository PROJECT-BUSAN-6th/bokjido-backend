package com.projectbusan.bokjido.service;

import com.projectbusan.bokjido.dto.FacilityDTO;
import com.projectbusan.bokjido.dto.FacilityReviewDTO;
import com.projectbusan.bokjido.entity.Benefit;
import com.projectbusan.bokjido.entity.Facility;
import com.projectbusan.bokjido.entity.FacilityReview;
import com.projectbusan.bokjido.entity.User;
import com.projectbusan.bokjido.exception.CustomException;
import com.projectbusan.bokjido.exception.ErrorCode;
import com.projectbusan.bokjido.repository.BenefitsRepository;
import com.projectbusan.bokjido.repository.FacilityRepository;
import com.projectbusan.bokjido.repository.FacilityReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WelfareFacility {
    @Autowired
    private final FacilityRepository facilityRepository;

    @Autowired
    private final FacilityReviewRepository facilityReviewRepository;

    @Autowired
    private final BenefitsRepository benefitsRepository;

    // <<-- 복지 건물 생성 -->>
    @Transactional
    public void create(FacilityDTO.FacilityDto facilityDto) {
        Facility facility = Facility.create(facilityDto);
        facilityRepository.save(facility);
    }

    // <<-- 복지 건물 단일 조회 By ID -->>
    @Transactional
    public Optional<Facility> searchById(Long id) { return facilityRepository.findById(id); }

    // <<-- 복지 건물 조회 By 주소 -->>
    public List<Facility> searchByLocation(String searchTerm) {
        return facilityRepository.findFacilitiesByLocationContaining(searchTerm);
    }

    public List<Facility> searchByCategory(String searchTerm) {
        return facilityRepository.findFacilitiesByCategoryContaining(searchTerm);
    }

    // <<-- 전체 복지 건물 조회 -->>
    public List<Facility> loadAll() { return facilityRepository.findAll(); }

    // <<-- 복지 시설 리뷰 생성 -->>
    public void createReview(User user, FacilityReviewDTO.FacilityReviewRequestDTO facilityReviewRequestDTO) {
        FacilityReview facilityReview = FacilityReview.builder()
                .user(user)
                .facility(findFacility(facilityReviewRequestDTO.getFacilityId()))
                .benefit(findBenefits(facilityReviewRequestDTO.getBenefitId()))
                .clean(facilityReviewRequestDTO.getClean())
                .kindness(facilityReviewRequestDTO.getKindness())
                .parking(facilityReviewRequestDTO.getParking())
                .content(facilityReviewRequestDTO.getContent())
                .build();

        facilityReviewRepository.save(facilityReview);

    }

    // <<-- 복지 시설 리뷰 조회 -->>
    public List<FacilityReview> getReview(Long facilityId) {
        return facilityReviewRepository.findByFacilityId(facilityId);
    }

    private Benefit findBenefits(Long id){
        return benefitsRepository.findById(id).orElseThrow(()
                -> new CustomException(ErrorCode.BENEFITS_NOT_FOUND_ERROR, "해당 복지 서비스는 존재하지 않습니다."));
    }

    private Facility findFacility(Long id){
        return facilityRepository.findById(id).orElseThrow(()
                -> new CustomException(ErrorCode.FACILITY_NOT_FOUND_ERROR, "해당 복지 시설은 존재하지 않습니다."));
    }
}
