package com.projectbusan.bokjido.service;

import com.projectbusan.bokjido.dto.AuthDTO;
import com.projectbusan.bokjido.entity.User;
import com.projectbusan.bokjido.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private UserRepository userRepository;



    // <<-- 회원가입 -->>
    @Transactional
    public void register(AuthDTO.SignupDto signupDto) {
        validateDuplicateUser(signupDto.getUserid()); // 중복 회원 검증

        User user = User.register(signupDto);

        if (signupDto.getInterestTopicCategory() != null) {
            user.setInterestTopicCategory(Arrays.toString(signupDto.getInterestTopicCategory()));
        }

        userRepository.save(user);
    }

    // 중복 회원 조회
    @Transactional
    private void validateDuplicateUser(String userid){
        Optional<User> userModel = userRepository.findByUserid(userid);
        if (userModel.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // <<-- 전체 회원 조회 -->>
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<String> findInterestTopic(Long id) {
        Optional<User> user = userRepository.findById(id);
        String interestTopic = user.get().getInterestTopicCategory();
        if (interestTopic != null) {
            String[] interestArray = interestTopic.substring(1, interestTopic.length() -1).split(", ");
            return Arrays.asList(interestArray);
        }
        return Collections.emptyList();
    }
}
