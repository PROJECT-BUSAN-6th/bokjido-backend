package com.projectbusan.bokjido.dto;

import com.projectbusan.bokjido.role.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Role DTO")
public class RoleDTO {
    @Schema(description = "권한")
    private Role role;
}
