package com.projectbusan.bokjido.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "benefit_review")
public class BenefitReview extends BaseTimeEntity{
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

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
}
