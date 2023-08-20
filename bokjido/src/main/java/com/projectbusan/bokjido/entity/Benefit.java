package com.projectbusan.bokjido.entity;

import com.projectbusan.bokjido.enums.HouseholdSituationCategory;
import com.projectbusan.bokjido.enums.InterestTopicCategory;
import com.projectbusan.bokjido.enums.LifeCycleCategory;
import com.projectbusan.bokjido.enums.WelfareBenefitsCategory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "welfare_benefits")
public class Benefit extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @Enumerated(EnumType.STRING)
    private WelfareBenefitsCategory welfareBenefitsCategory;

    @Enumerated(EnumType.STRING)
    private LifeCycleCategory lifeCycleCategory;

    @Enumerated(EnumType.STRING)
    private HouseholdSituationCategory householdSituationCategory;

    @Enumerated(EnumType.STRING)
    private InterestTopicCategory interestTopicCategory;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String inquiryStation;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String applyCycle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String provideType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String manageSituation;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String applyTarget;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String serviceContent;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String howToApply;

    @Column(columnDefinition = "TEXT")
    private String addiInfo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String summary;
}
