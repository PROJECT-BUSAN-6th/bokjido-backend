package com.projectbusan.bokjido.controller;

import com.projectbusan.bokjido.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "1. 유저 페이지", description = "유저 관련 API")
@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @Operation(summary = "User 회원가입", description = "User의 회원가입 기능입니다. <br>여기서 회원가입을 하면 Role이 User인 회원이 생성됩니다.")
    @PostMapping("/signup")
    public String registerUser(){
        return "registerUser";
    }
}
