package com.projectbusan.bokjido.dto;

import com.projectbusan.bokjido.role.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User DTO")
public class UserDTO {
    @Schema(description = "userid", example = "userid")
    private String userid;

    @Schema(description = "password", example = "password")
    private String password;

    @Schema(description = "username", example = "홍길동")
    private String username;

    @Schema(description = "birth", example = "2000.01.01")
    private LocalDate birth;

    @Schema(description = "gender", example = "남")
    private String gender;

    @Schema(description = "권한")
    private Role role;

}
