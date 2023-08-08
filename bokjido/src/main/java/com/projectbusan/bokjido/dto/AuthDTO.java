package com.projectbusan.bokjido.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

public class AuthDTO {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LoginDto {
        @Schema(description = "아이디")
        private String userid;

        @Schema(description = "password")
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

        @Schema(description = "생년월일", example = "2000-01-01")
        private LocalDate birth;

        @Schema(description = "성별", example = "남")
        private String gender;

        @Builder
        public SignupDto(String userid, String password, String username, LocalDate birth, String gender) {
            this.userid = userid;
            this.password = password;
            this.username = username;
            this.birth = birth;
            this.gender = gender;
        }

        public static SignupDto encodePassword(SignupDto signupDto, String encodedPassword) {
            SignupDto newSignupDto = new SignupDto();
            newSignupDto.userid = signupDto.getUserid();
            newSignupDto.password = encodedPassword;
            newSignupDto.username = signupDto.getUsername();
            newSignupDto.birth = signupDto.getBirth();
            newSignupDto.gender = signupDto.getGender();
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
