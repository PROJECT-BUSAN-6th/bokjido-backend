package com.projectbusan.bokjido.repository;

import com.projectbusan.bokjido.dto.FacilityReviewDTO;
import com.projectbusan.bokjido.entity.FacilityReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacilityReviewRepository extends JpaRepository<FacilityReview, Long> {
//    @Query("SELECT m FROM FacilityReview m WHERE m.facility.id = :facilityId")
    List<FacilityReview> findByFacilityId(Long facilityId);
}
