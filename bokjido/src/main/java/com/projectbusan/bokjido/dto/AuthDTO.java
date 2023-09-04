package com.projectbusan.bokjido.dto;

import com.projectbusan.bokjido.enums.HouseholdSituationCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

public class AuthDTO {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LoginDto {
        @Schema(description = "아이디", example = "userid")
        private String userid;

        @Schema(description = "password", example = "password")
        private String password;

        @Builder
        public LoginDto(String userid, String password){
            this.userid = userid;
            this.password = password;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "Signup DTO")
    public static class SignupDto{
        @Schema(description = "아이디", example = "userid")
        private String userid;

        @Schema(description = "password", example = "password")
        private String password;

        @Schema(description = "이름", example = "username")
        private String username;

        @Schema(description = "이메일", example = "email@naver.com")
        private String email;

        @Schema(description = "연락처", example = "010-1234-5678")
        private String phone;

        @Schema(description = "생년월일", example = "2000-01-01")
        private LocalDate birth;

        @Schema(description = "성별", example = "남")
        private String gender;

        @Schema(description = "주소", example = "서울특별시 중구 세종대로 110 (태평로1가)")
        private String address;

        @Schema(description = "가구상황", example = "LOW_INCOME")
        private HouseholdSituationCategory householdSituationCategory;

        @Schema(description = "관심주제", example = "PHYSICAL_HEALTH")
        private String[] interestTopicCategory;

        @Builder
        public SignupDto(String userid, String password, String username, String email, String phone, LocalDate birth, String gender, String address, HouseholdSituationCategory householdSituationCategory, String[] interestTopicCategory) {
            this.userid = userid;
            this.password = password;
            this.username = username;
            this.email = email;
            this.phone = phone;
            this.birth = birth;
            this.gender = gender;
            this.address = address;
            this.householdSituationCategory = householdSituationCategory;
            this.interestTopicCategory = interestTopicCategory;
        }

        public static SignupDto encodePassword(SignupDto signupDto, String encodedPassword) {
            SignupDto newSignupDto = new SignupDto();
            newSignupDto.userid = signupDto.getUserid();
            newSignupDto.password = encodedPassword;
            newSignupDto.username = signupDto.getUsername();
            newSignupDto.email = signupDto.getEmail();
            newSignupDto.phone = signupDto.getPhone();
            newSignupDto.birth = signupDto.getBirth();
            newSignupDto.gender = signupDto.getGender();
            newSignupDto.address = signupDto.getAddress();
            newSignupDto.householdSituationCategory = signupDto.getHouseholdSituationCategory();
            newSignupDto.interestTopicCategory = signupDto.getInterestTopicCategory();
            return newSignupDto;
        }
    }

    @Getter
    @NoArgsConstructor
    @Schema(description = "Token DTO")
    public static class TokenDto{
        @Schema(description = "access Token")
        private String accessToken;

        @Schema(description = "Refresh Token")
        private String refreshToken;

        @Schema(description = "GrantType")
        private String grantType;

        @Schema(description = "expiresIn")
        private Long expiresIn;

        public TokenDto(String accessToken, String refreshToken, String grantType, Long expiresIn) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.grantType = grantType;
            this.expiresIn = expiresIn;
        }
    }

}
