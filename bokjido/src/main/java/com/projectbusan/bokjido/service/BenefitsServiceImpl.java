package com.projectbusan.bokjido.service;

import com.projectbusan.bokjido.dto.*;
import com.projectbusan.bokjido.entity.User;
import com.projectbusan.bokjido.entity.Benefit;
import com.projectbusan.bokjido.repository.BenefitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BenefitsServiceImpl implements BenefitsService {

    private final BenefitsRepository benefitsRepository;


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
        return null;
    }

    @Override
    public BenefitDetailsResponseDTO getServiceById(Long serviceId) {
        return null;
    }

    @Override
    public Page<BenefitMainResponseDTO> getServiceByUser(User user, Pageable pageable) {
        return null;
    }

    @Override
    public Long createReview(User user, BenefitReviewRequestDTO benefitReviewRequestDTO) {
        return null;
    }

    @Override
    public Page<BenefitReviewResponseDTO> getReview(Long serviceId, Pageable pageable) {
        return null;
    }
}
