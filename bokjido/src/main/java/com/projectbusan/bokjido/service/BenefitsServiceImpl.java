package com.projectbusan.bokjido.service;

import com.projectbusan.bokjido.dto.*;
import com.projectbusan.bokjido.entity.BenefitReview;
import com.projectbusan.bokjido.entity.Facility;
import com.projectbusan.bokjido.entity.User;
import com.projectbusan.bokjido.entity.Benefit;
import com.projectbusan.bokjido.exception.CustomException;
import com.projectbusan.bokjido.exception.ErrorCode;
import com.projectbusan.bokjido.repository.BenefitReviewRepository;
import com.projectbusan.bokjido.repository.BenefitsRepository;
import com.projectbusan.bokjido.repository.FacilityRepository;
import com.projectbusan.bokjido.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BenefitsServiceImpl implements BenefitsService {

    private final BenefitsRepository benefitsRepository;
    private final BenefitReviewRepository benefitReviewRepository;
    private final UserRepository userRepository;
    private final FacilityRepository facilityRepository;


    @Override
    public Long createService(User user, BenefitCreateRequestDTO benefitCreateRequestDTO) {
        Benefit benefit = Benefit.builder()
                .user(user)
                .name(benefitCreateRequestDTO.getName())
                .welfareBenefitsCategory(benefitCreateRequestDTO.getWelfareBenefitsCategory())
                .lifeCycleCategory(benefitCreateRequestDTO.getLifeCycleCategory())
                .householdSituationCategory(benefitCreateRequestDTO.getHouseholdSituationCategory())
                .interestTopicCategory(benefitCreateRequestDTO.getInterestTopicCategory())
                .inquiryStation(benefitCreateRequestDTO.getInquiryStation())
                .applyCycle(benefitCreateRequestDTO.getApplyCycle())
                .provideType(benefitCreateRequestDTO.getProvideType())
                .manageSituation(benefitCreateRequestDTO.getManageSituation())
                .applyTarget(benefitCreateRequestDTO.getApplyTarget())
                .serviceContent(benefitCreateRequestDTO.getServiceContent())
                .howToApply(benefitCreateRequestDTO.getHowToApply())
                .addiInfo(benefitCreateRequestDTO.getAddiInfo())
                .summary(benefitCreateRequestDTO.getSummary())
                .build();

        benefitsRepository.save(benefit);
        return benefit.getId();
    }

    @Override
    public Page<BenefitMainResponseDTO> getService(BenefitRequestDTO requestDto, Pageable pageable) {
        List<Benefit> benefits = benefitsRepository.findDistinctByConditions(
                requestDto.getLocality(),
                requestDto.getAge(),
                requestDto.getKeyword()
        );

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Benefit> subList;
        if (benefits.size() < startItem) {
            subList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, benefits.size());
            subList = benefits.subList(startItem, toIndex);
        }

        List<BenefitMainResponseDTO> responseDTOs = subList.stream()
                .map(BenefitMainResponseDTO::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(responseDTOs, pageable, benefits.size());
    }

    @Override
    public BenefitDetailsResponseDTO getServiceById(Long serviceId) {
        Benefit benefit = findBenefits(serviceId);
        return BenefitDetailsResponseDTO.toDto(benefit);
    }

    @Override
    public Page<BenefitMainResponseDTO> getServiceByUser(User user, Pageable pageable) {
        return null;
    }

    @Override
    public Long createReview(User user, BenefitReviewRequestDTO benefitReviewRequestDTO) {
        Benefit benefit = findBenefits(benefitReviewRequestDTO.getServiceId());

        BenefitReview benefitReview = BenefitReview.builder()
                .user(user)
                .benefit(benefit)
                .facility(findFacility(benefitReviewRequestDTO.getFacilityId()))
                .content(benefitReviewRequestDTO.getContent())
                .build();

        benefitReviewRepository.save(benefitReview);
        return benefitReview.getId();
    }

    @Override
    public Page<BenefitReviewResponseDTO> getReview(Long serviceId, Pageable pageable) {
        Page<BenefitReview> benefitReviews = benefitReviewRepository.findByWelfareBenefitId(serviceId, pageable);
        return benefitReviews.map(BenefitReviewResponseDTO::toDto);
    }

    private User findUser(Long id){
        return userRepository.findById(id).orElseThrow(()
                -> new CustomException(ErrorCode.USER_NOT_FOUND_ERROR, "해당 사용자는 존재하지 않습니다."));
    }

    private Benefit findBenefits(Long id){
        return benefitsRepository.findById(id).orElseThrow(()
                -> new CustomException(ErrorCode.BENEFITS_NOT_FOUND_ERROR, "해당 복지 서비스는 존재하지 않습니다."));
    }

    private Facility findFacility(Long id){
        return facilityRepository.findById(id).orElseThrow(()
                -> new CustomException(ErrorCode.BENEFITS_NOT_FOUND_ERROR, "해당 복지 시설은 존재하지 않습니다."));
    }


    private BenefitReview findBenefitReview(Long id){
        return benefitReviewRepository.findById(id).orElseThrow(()
                -> new CustomException(ErrorCode.BENEFIT_REVIEW_NOT_FOUND_ERROR, "해당 복지 서비스의 리뷰는 존재하지 않습니다."));
    }

}
