package com.projectbusan.bokjido.controller;

import com.projectbusan.bokjido.dto.AuthDTO;
import com.projectbusan.bokjido.service.AuthService;
import com.projectbusan.bokjido.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.*;
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

    private final long COOKIE_EXPIRATION = 86400; // 1일

    // <<-- 회원 가입 요청 처리 -->>
    @Operation(summary = "User 회원가입", description = "User의 회원가입 기능 <br>회원가입을 하면 Role이 User인 회원이 생성")
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Valid AuthDTO.SignupDto signupDto){
        String encodedPassword = encoder.encode(signupDto.getPassword());
        AuthDTO.SignupDto newSignupDto = AuthDTO.SignupDto.encodePassword(signupDto, encodedPassword);
        userService.register(newSignupDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // <<-- 로그인 -> 토큰 발급 -->>
    @Operation(summary = "로그인", description = "User의 로그인 기능 <br>로그인을 하고 토큰을 발급")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDTO.LoginDto loginDto) {
        // User 등록 및 Refresh Token 저장
        AuthDTO.TokenDto tokenDto = authService.login(loginDto);

        // RT 저장
        HttpCookie httpCookie = ResponseCookie.from("refresh-token", tokenDto.getRefreshToken())
                .maxAge(COOKIE_EXPIRATION)
                .httpOnly(true)
                .secure(true)
                .build();
        System.out.println("tokenDto.getAccessToken() = " + tokenDto.getAccessToken());
        System.out.println("tokenDto.getRefreshToken = " + tokenDto.getRefreshToken());
        System.out.println("tokenDto.getGrantType() = " + tokenDto.getGrantType());
        System.out.println("tokenDto.getExpiresIn() = " + tokenDto.getExpiresIn());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, httpCookie.toString())
                // AT 저장
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenDto.getAccessToken())
                .build();
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestHeader("Authorization") String requestAccessToken) {
        if (!authService.validate(requestAccessToken)) {
            return ResponseEntity.status(HttpStatus.OK).build(); // 재발급 필요 X
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 재발급 필요 O
        }
    }

    // 토큰 재발급
    @Operation(summary = "토큰 재발급", description = "토큰을 재발급 합니다.")
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@CookieValue(name = "refresh-token") String requestRefreshToken,
                                     @RequestHeader("Authorization") String requestAccessToken) {
        AuthDTO.TokenDto reissuedTokenDto = authService.reissue(requestAccessToken, requestRefreshToken);

        if (reissuedTokenDto != null) {// 토큰 재발급 성공 시
            System.out.println("재발급 성공");
            // RT 저장
            ResponseCookie responseCookie = ResponseCookie.from("refresh-token", reissuedTokenDto.getRefreshToken())
                    .maxAge(COOKIE_EXPIRATION)
                    .httpOnly(true)
                    .secure(true)
                    .build();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                    // AT 저장
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + reissuedTokenDto.getAccessToken())
                    .build();
        }
        else { // Refresh Token 탈취 가능성
            System.out.println("재발급 실패");
            // Cookie 삭제 후 재로그인 유도
            ResponseCookie responseCookie = ResponseCookie.from("refresh-token", "")
                    .maxAge(0)
                    .path("/")
                    .build();
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                    .build();
        }
    }

    // <<-- 로그아웃 -->>
    @Operation(summary = "로그아웃", description = "User 로그아웃 기능 <br> 토큰은 만료시킵니다.")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String requestAccessToken) {
        authService.logout(requestAccessToken);
        ResponseCookie responseCookie = ResponseCookie.from("refresh-token", "")
                .maxAge(0)
                .path("/")
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .build();
    }

/*    // 모든 회원 조회
    @Operation(summary = "모든 회원 조회")
    @GetMapping("/allusers")
    public List<User> userList() {

    }*/
}
