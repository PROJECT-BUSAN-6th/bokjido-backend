package com.projectbusan.bokjido.repository;

import com.projectbusan.bokjido.entity.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BenefitsRepository extends JpaRepository<Benefit, Long> {
    Optional<Benefit> findById(Long id);

    @Query("SELECT DISTINCT b FROM Benefit b " +
            "WHERE (:locality IS NULL OR b.inquiryStation LIKE CONCAT('%', :locality, '%')) " +
            "AND (:age IS NULL OR " +
            "(:age >= 0 AND :age <= 6 AND b.lifeCycleCategory = 'INFANT') OR " +
            "(:age >= 7 AND :age <= 12 AND b.lifeCycleCategory = 'CHILD') OR " +
            "(:age >= 13 AND :age <= 18 AND b.lifeCycleCategory = 'TEENAGER') OR " +
            "(:age >= 19 AND :age <= 34 AND b.lifeCycleCategory = 'YOUTH') OR " +
            "(:age >= 35 AND :age <= 64 AND b.lifeCycleCategory = 'MIDDLE_AGED') OR " +
            "(:age >= 65 AND b.lifeCycleCategory = 'OLD_AGED')) " +
            "AND (:keyword IS NULL OR " +
            "b.name LIKE CONCAT('%', :keyword, '%') OR " +
            "b.serviceContent LIKE CONCAT('%', :keyword, '%') OR " +
            "b.addiInfo LIKE CONCAT('%', :keyword, '%'))")
    List<Benefit> findDistinctByConditions(
            @Param("locality") String locality,
            @Param("age") Integer age,
            @Param("keyword") String keyword
    );
}
