package com.projectbusan.bokjido.controller;

import com.projectbusan.bokjido.dto.AuthDTO;
import com.projectbusan.bokjido.service.AuthService;
import com.projectbusan.bokjido.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "1. 유저 페이지", description = "유저 관련 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private final BCryptPasswordEncoder encoder;

    // 회원 가입 요청 처리
    @Operation(summary = "User 회원가입", description = "User의 회원가입 기능입니다. <br>여기서 회원가입을 하면 Role이 User인 회원이 생성됩니다.")
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(AuthDTO.SignupDto signupDto){
        String encodedPassword = encoder.encode(signupDto.getPassword());
        AuthDTO.SignupDto newSignupDto = AuthDTO.SignupDto.encodePassword(signupDto, encodedPassword);
        userService.register(newSignupDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

/*    // 모든 회원 조회
    @Operation(summary = "모든 회원 조회")
    @GetMapping("/allusers")
    public List<User> userList() {

    }*/
}
