package com.projectbusan.bokjido;

import com.projectbusan.bokjido.entity.User;
import com.projectbusan.bokjido.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void 회원_가입() {
        //given
        userRepository.save(User.builder()
                .userid("user_id1")
                .password("password1")
                .username("user_name")
                .birth(new Date())
                .gender("남")
                .create_date(LocalDateTime.now())
                .build()
        );

        //when
        List<User> userList = userRepository.findAll();

        //then
        User user = userList.get(0);
        Assertions.assertEquals("user_id1", user.getUserid());
        System.out.println("user = " + user);
    }
}
