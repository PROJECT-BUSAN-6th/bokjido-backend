package com.projectbusan.bokjido.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
        @Schema(description = "아이디")
        private String userid;

        @Schema(description = "password")
        private String password;

        @Schema(description = "이름")
        private String name;

        @Schema(description = "생년월일", example = "2000-01-01")
        private Date birth;

        @Schema(description = "성별", example = "남 or 여")
        private String gender;

        @Builder
        public SignupDto(String userid, String password, String name, Date birth, String gender) {
            this.userid = userid;
            this.password = password;
            this.name = name;
            this.birth = birth;
            this.gender = gender;
        }
    }

}
