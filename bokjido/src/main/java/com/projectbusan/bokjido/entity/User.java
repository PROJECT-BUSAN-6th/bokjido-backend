package com.projectbusan.bokjido.entity;

import com.projectbusan.bokjido.dto.AuthDTO;
import com.projectbusan.bokjido.role.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userid;

    private String username;

    private String password;

    private LocalDate birth;

    private String gender;

    private Role role;

    private LocalDateTime create_date;

    private LocalDateTime modify_date;

    @Builder
    public User(Long id, String userid, String username, String password, LocalDate birth, String gender, Role role, LocalDateTime create_date, LocalDateTime modify_date) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
        this.role = role;
        this.create_date = create_date;
        this.modify_date = modify_date;
    }

    public static User register(AuthDTO.SignupDto signupDto) {
        return User.builder()
                .userid(signupDto.getUserid())
                .password(signupDto.getPassword())
                .username(signupDto.getUsername())
                .birth(signupDto.getBirth())
                .gender(signupDto.getGender())
                .role(Role.USER)
                .create_date(LocalDateTime.now())
                .build();
    }
}
