package com.projectbusan.bokjido.repository;

import com.projectbusan.bokjido.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
//    @Query("SELECT f FROM facility f WHERE f.location LIKE %:searchTerm%")
    List<Facility> findFacilitiesByLocationContaining(String searchTerm);

    List<Facility> findFacilitiesByCategoryContaining(String searchTerm);

    List<Facility> findFacilitiesByNameContaining(String searchTerm);
}
