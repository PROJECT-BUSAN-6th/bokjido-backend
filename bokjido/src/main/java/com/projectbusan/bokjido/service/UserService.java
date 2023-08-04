package com.projectbusan.bokjido.service;

import com.projectbusan.bokjido.entity.User;
//import com.projectbusan.bokjido.repository.MemoryUserRepository;
import com.projectbusan.bokjido.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;



    // <<-- 회원가입 -->>
    public void join(User user) {
//        validateDuplicateUser(user); // 중복 회원 검증
        userRepository.save(user);
    }

    private void validateDuplicateUser(User user){
        userRepository.findById(user.getUserid())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // <<-- 전체 회원 조회 -->>
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findOne(String userid) {
        return userRepository.findByUserid(userid);
    }
}
