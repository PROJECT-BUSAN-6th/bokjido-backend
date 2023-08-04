package com.projectbusan.bokjido.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String userid;

    private String username;

    private String password;

    private Date birth;

    private String gender;

//    private Role role;

    private LocalDateTime create_date;

    private LocalDateTime modify_date;

    @Builder
    public User(Long id, String userid, String username, String password, Date birth, String gender, LocalDateTime create_date, LocalDateTime modify_date) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
        this.create_date = create_date;
        this.modify_date = modify_date;
    }
}
