package com.projectbusan.bokjido.repository;

import com.projectbusan.bokjido.entity.BenefitReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefitReviewRepository extends JpaRepository<BenefitReview, Long> {
    @Query("SELECT br FROM BenefitReview br WHERE br.benefit.id = :benefitId")
    Page<BenefitReview> findByWelfareBenefitId(Long benefitId, Pageable pageable);
}
