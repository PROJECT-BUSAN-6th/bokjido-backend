package com.projectbusan.bokjido.dto;

import com.projectbusan.bokjido.enums.HouseholdSituationCategory;
import com.projectbusan.bokjido.enums.InterestTopicCategory;
import com.projectbusan.bokjido.enums.LifeCycleCategory;
import com.projectbusan.bokjido.enums.WelfareBenefitsCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BenefitCreateRequestDTO {
    @NotBlank(message = "복지 서비스의 이름은 필요합니다.")
    private String name;

    @NotBlank(message = "복지 서비스에 해당하는 카테고리가 필요합니다.")
    private WelfareBenefitsCategory welfareBenefitsCategory;

    @NotBlank(message = "복지 서비스에 해당하는 생애주기가 필요합니다.")
    private LifeCycleCategory lifeCycleCategory;

    @NotBlank(message = "복지 서비스에 해당하는 가구상황이 필요합니다.")
    private HouseholdSituationCategory householdSituationCategory;

    @NotBlank(message = "복지 서비스와 관련된 관심주제가 필요합니다.")
    private InterestTopicCategory interestTopicCategory;

    @NotBlank(message = "복지 서비스의 문의처가 필요합니다.")
    private String inquiryStation;

    @NotBlank(message = "복지 서비스의 지원주기가 필요합니다.")
    private String applyCycle;

    @NotBlank(message = "복지 서비스의 제공유형이 필요합니다.")
    private String provideType;

    @NotBlank(message = "복지 서비스의 담당부처는 필요합니다.")
    private String manageSituation;

    @NotBlank(message = "복지 서비스의 지원대상은 필요합니다.")
    private String applyTarget;

    @NotBlank(message = "복지 서비스의 내용은 필요합니다.")
    private String serviceContent;

    @NotBlank(message = "복지 서비스의 신청방법은 필요합니다.")
    private String howToApply;

    @NotBlank(message = "복지 서비스의 추가정보가 필요합니다.")
    private String addiInfo;

    @NotBlank(message = "복지 서비스의 요약이 필요합니다.")
    private String summary;
}