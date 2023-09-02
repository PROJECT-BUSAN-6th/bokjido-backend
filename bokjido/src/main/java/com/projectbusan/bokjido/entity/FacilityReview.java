package com.projectbusan.bokjido.entity;

import com.projectbusan.bokjido.dto.FacilityReviewDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "facility_review")
public class FacilityReview extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id")
    private Facility facility;

    @ManyToOne(fetch = FetchType.LAZY)
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

//    @Builder
//    public FacilityReview(Long id, Long userId, Long facilityId, Long benefitId, int clean, int kindness, int parking, String content) {
//        this.id = id;
//        this.userId = userId;
//        this.facilityId = facilityId;
//        this.benefitId = benefitId;
//        this.clean = clean;
//        this.kindness = kindness;
//        this.parking = parking;
//        this.content = content;
//    }

}
