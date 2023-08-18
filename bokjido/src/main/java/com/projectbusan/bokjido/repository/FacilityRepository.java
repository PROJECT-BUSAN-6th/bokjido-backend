package com.projectbusan.bokjido.repository;

import com.projectbusan.bokjido.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

}
