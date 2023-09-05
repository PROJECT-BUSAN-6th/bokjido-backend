package com.projectbusan.bokjido.service;

import com.projectbusan.bokjido.dto.*;
import com.projectbusan.bokjido.entity.BenefitReview;
import com.projectbusan.bokjido.entity.Facility;
import com.projectbusan.bokjido.entity.User;
import com.projectbusan.bokjido.entity.Benefit;
import com.projectbusan.bokjido.enums.HouseholdSituationCategory;
import com.projectbusan.bokjido.enums.LifeCycleCategory;
import com.projectbusan.bokjido.exception.CustomException;
import com.projectbusan.bokjido.exception.ErrorCode;
import com.projectbusan.bokjido.repository.BenefitReviewRepository;
import com.projectbusan.bokjido.repository.BenefitsRepository;
import com.projectbusan.bokjido.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BenefitsServiceImpl implements BenefitsService {

    private final BenefitsRepository benefitsRepository;
    private final BenefitReviewRepository benefitReviewRepository;
    private final FacilityRepository facilityRepository;


    @Override
    public Long createService(User user, BenefitCreateRequestDTO benefitCreateRequestDTO) {
        Benefit benefit = Benefit.builder()
                .user(user)
                .name(benefitCreateRequestDTO.getName())
                .benefitRoleCategory(benefitCreateRequestDTO.getBenefitRolesCategory())
                .benefitsCategory(benefitCreateRequestDTO.getBenefitsCategory())
                .lifeCycleCategory(benefitCreateRequestDTO.getLifeCycleCategory())
                .householdSituationCategory(benefitCreateRequestDTO.getHouseholdSituationCategory())
                .interestTopicCategory(benefitCreateRequestDTO.getInterestTopicCategory())
                .inquiryStation(benefitCreateRequestDTO.getInquiryStation())
                .applyCycle(benefitCreateRequestDTO.getApplyCycle())
                .provideType(benefitCreateRequestDTO.getProvideType())
                .manageSituation(benefitCreateRequestDTO.getManageSituation())
                .applyTarget(benefitCreateRequestDTO.getApplyTarget())
                .serviceContent(benefitCreateRequestDTO.getServiceContent())
                .howToApply(benefitCreateRequestDTO.getHowToApply())
                .addiInfo(benefitCreateRequestDTO.getAddiInfo())
                .summary(benefitCreateRequestDTO.getSummary())
                .build();

        benefitsRepository.save(benefit);
        return benefit.getId();
    }

    @Override
    public Page<BenefitMainResponseDTO> getService(BenefitRequestDTO requestDto, Pageable pageable) {
        List<Benefit> benefits = benefitsRepository.findDistinctByConditions(
                requestDto.getLocality(),
                requestDto.getAge(),
                requestDto.getKeyword()
        );

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Benefit> subList;
        if (benefits.size() < startItem) {
            subList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, benefits.size());
            subList = benefits.subList(startItem, toIndex);
        }

        List<BenefitMainResponseDTO> responseDTOs = subList.stream()
                .map(BenefitMainResponseDTO::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(responseDTOs, pageable, benefits.size());
    }

    @Override
    public BenefitDetailsResponseDTO getServiceById(Long serviceId) {
        Benefit benefit = findBenefits(serviceId);
        return BenefitDetailsResponseDTO.toDto(benefit);
    }

    @Override
    public Page<BenefitMainResponseDTO> getServiceByRandom(Pageable pageable) {
        List<Benefit> benefitList = benefitsRepository.findAll();
        if (benefitList.isEmpty()) {
            return Page.empty();
        }

        Collections.shuffle(benefitList, new Random());

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Benefit> pageList;

        if (benefitList.size() < startItem) {
            pageList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, benefitList.size());
            pageList = benefitList.subList(startItem, toIndex);
        }

        List<BenefitMainResponseDTO> benefitDtoList = new ArrayList<>();
        for (Benefit benefit : pageList) {
            BenefitMainResponseDTO dto = BenefitMainResponseDTO.builder()
                    .id(benefit.getId())
                    .name(benefit.getName())
                    .applyTarget(benefit.getApplyTarget())
                    .summary(benefit.getSummary())
                    .build();

            benefitDtoList.add(dto);
        }

        return new PageImpl<>(benefitDtoList, pageable, benefitList.size());
    }

    @Override
    public Page<BenefitMainResponseDTO> getServiceByUser(User user, Pageable pageable) {
        List<Benefit> allBenefits = benefitsRepository.findAll();
        if (allBenefits.isEmpty()) {
            return null;
        }

        List<Benefit> recommendedBenefits = allBenefits.stream()
                .filter(benefit -> isBenefitRecommendedForUser(user, benefit))
                .sorted(Comparator.comparingInt(benefit -> countMatchingConditions(user, (Benefit) benefit)).reversed())
                .collect(Collectors.toList());

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        int endItem = Math.min(startItem + pageSize, recommendedBenefits.size());

        List<Benefit> subList = recommendedBenefits.subList(startItem, endItem);

        List<BenefitMainResponseDTO> resultBenefits = subList.stream()
                .map(BenefitMainResponseDTO::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(resultBenefits, pageable, recommendedBenefits.size());
    }

    private int countMatchingConditions(User user, Benefit benefit) {
        int matchingConditions = 0;

        if (isUserAgeMatching(user, benefit)) {
            matchingConditions++;
        }

        if (isUserGenderMatching(user, benefit)) {
            matchingConditions++;
        }

        if (isUserHouseholdSituationMatching(user, benefit)) {
            matchingConditions++;
        }

        if (isUserAddressMatching(user, benefit)) {
            matchingConditions++;
        }

        return matchingConditions;
    }

    private boolean isBenefitRecommendedForUser(User user, Benefit benefit) {
        LocalDate userBirth = user.getBirth();
        if (userBirth == null) {
            return false;
        }

        return isUserAgeMatching(user, benefit) ||
                isUserGenderMatching(user, benefit) ||
                isUserHouseholdSituationMatching(user, benefit) ||
                isUserAddressMatching(user, benefit);
    }

    private boolean isUserAgeMatching(User user, Benefit benefit) {
        LocalDate userBirth = user.getBirth();
        if (userBirth == null) {
            return false;
        }

        int userAge = calculateUserAge(userBirth);
        LifeCycleCategory benefitLifeCycleCategory = benefit.getLifeCycleCategory();
        if (benefitLifeCycleCategory == LifeCycleCategory.INFANT && userAge >= 0 && userAge <= 6) {
            return true;
        } else if (benefitLifeCycleCategory == LifeCycleCategory.CHILD && userAge >= 7 && userAge <= 12) {
            return true;
        } else if (benefitLifeCycleCategory == LifeCycleCategory.TEENAGER && userAge >= 13 && userAge <= 18) {
            return true;
        } else if (benefitLifeCycleCategory == LifeCycleCategory.YOUTH && userAge >= 19 && userAge <= 34) {
            return true;
        } else if (benefitLifeCycleCategory == LifeCycleCategory.MIDDLE_AGED && userAge >= 35 && userAge <= 64) {
            return true;
        } else return benefitLifeCycleCategory == LifeCycleCategory.OLD_AGED && userAge >= 65;
    }

    private boolean isUserGenderMatching(User user, Benefit benefit) {
        String userGender = user.getGender();
        String applyTarget = benefit.getApplyTarget();
        return userGender != null && !userGender.isBlank() && applyTarget.contains(userGender);
    }

    private boolean isUserHouseholdSituationMatching(User user, Benefit benefit) {
        HouseholdSituationCategory userHouseholdSituation = user.getHouseholdSituationCategory();
        return userHouseholdSituation != null && userHouseholdSituation == benefit.getHouseholdSituationCategory();
    }

    private boolean isUserAddressMatching(User user, Benefit benefit) {
        String userAddress = user.getAddress();
        String inquiryStation = benefit.getInquiryStation();

        if (userAddress != null && !userAddress.isBlank()) {
            List<String> userCities = extractCitiesFromAddress(userAddress);
            for (String city : userCities) {
                if (inquiryStation.contains(city)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int calculateUserAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    private List<String> extractCitiesFromAddress(String address) {
        List<String> cities = new ArrayList<>();
        Pattern pattern = Pattern.compile("(서울|부산|대구|인천|광주|대전|울산|세종|경기도|강원도|충청북도|충청남도|전라북도|전라남도|경상북도|경상남도|제주특별자치도)\\s*");
        Matcher matcher = pattern.matcher(address);
        while (matcher.find()) {
            cities.add(matcher.group());
        }
        return cities;
    }
    @Override
    public Long createReview(User user, BenefitReviewRequestDTO benefitReviewRequestDTO) {
        Benefit benefit = findBenefits(benefitReviewRequestDTO.getServiceId());
        BenefitReview benefitReview = BenefitReview.builder()
                .user(user)
                .benefit(benefit)
                .facility(benefitReviewRequestDTO.getFacilityId() == null ? null : findFacility(benefitReviewRequestDTO.getFacilityId()))
                .content(benefitReviewRequestDTO.getContent())
                .build();

        benefitReviewRepository.save(benefitReview);
        return benefitReview.getId();
    }

    @Override
    public Page<BenefitReviewResponseDTO> getReview(Long serviceId, Pageable pageable) {
        Page<BenefitReview> benefitReviews = benefitReviewRepository.findByWelfareBenefitId(serviceId, pageable);
        return benefitReviews.map(BenefitReviewResponseDTO::toDto);
    }

    private Benefit findBenefits(Long id){
        return benefitsRepository.findById(id).orElseThrow(()
                -> new CustomException(ErrorCode.BENEFITS_NOT_FOUND_ERROR, "해당 복지 서비스는 존재하지 않습니다."));
    }

    private Facility findFacility(Long id){
        return facilityRepository.findById(id).orElseThrow(()
                -> new CustomException(ErrorCode.BENEFITS_NOT_FOUND_ERROR, "해당 복지 시설은 존재하지 않습니다."));
    }
}
