package com.projectbusan.bokjido.service;

import com.projectbusan.bokjido.dto.*;
import com.projectbusan.bokjido.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BenefitsService {
    Long createService(User user, BenefitCreateRequestDTO benefitCreateRequestDTO);
    Page<BenefitMainResponseDTO> getService(String locality, Integer age, String keyword, Pageable pageable);
    BenefitDetailsResponseDTO getServiceById(Long serviceId);
    Page<BenefitMainResponseDTO> getServiceByRandom(Pageable pageable);
    Page<BenefitMainResponseDTO> getServiceByUser(User user, Pageable pageable);
    Long createReview(User user, BenefitReviewRequestDTO benefitReviewRequestDTO);
    Page<BenefitReviewResponseDTO> getReview(Long serviceId, Pageable pageable);

}
