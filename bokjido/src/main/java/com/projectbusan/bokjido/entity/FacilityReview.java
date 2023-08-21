package com.projectbusan.bokjido.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "facility_review")
public class FacilityReview extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "benefit_id")
    private Benefit benefit;

    @Column(nullable = false, columnDefinition = "INT")
    private int clean;

    @Column(nullable = false, columnDefinition = "INT")
    private int kindness;

    @Column(nullable = false, columnDefinition = "INT")
    private int parking;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;



}
