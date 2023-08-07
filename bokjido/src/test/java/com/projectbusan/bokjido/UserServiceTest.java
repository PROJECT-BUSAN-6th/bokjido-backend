package com.projectbusan.bokjido;


import com.projectbusan.bokjido.entity.User;
import com.projectbusan.bokjido.repository.UserRepository;
import com.projectbusan.bokjido.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void 회원가입(){
        //given
        User user = new User();
        user.setUserid("userid");
        user.setPassword("password");
        user.setUsername("user_name");
        user.setBirth(new Date());
        user.setGender("남1");
        user.setCreate_date(LocalDateTime.now());

        //when
        userService.join(user);

        //then
        User findUser = userService.findOne("userid").get();
        System.out.println("findUser = " + findUser);
        Assertions.assertEquals(user.getUserid(), findUser.getUserid());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        User user = new User();
        user.setUserid("userid");
        user.setPassword("password");
        user.setUsername("user_name");
        user.setBirth(new Date());
        user.setGender("남1");
        user.setCreate_date(LocalDateTime.now());

        User user1 = new User();
        user1.setUserid("userid1");
        user1.setPassword("password");
        user1.setUsername("user_name");
        user1.setBirth(new Date());
        user1.setGender("남1");
        user1.setCreate_date(LocalDateTime.now());

        userService.join(user);
        //예외 발생해야 통과
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class,
                () -> userService.join(user1));

        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
