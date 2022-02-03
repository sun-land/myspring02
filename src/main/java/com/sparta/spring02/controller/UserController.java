package com.sparta.spring02.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.spring02.dto.SignupRequestDto;
import com.sparta.spring02.service.KakaoUserService;
import com.sparta.spring02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto, Model model) {

        // 아이디, 비밀번호 체크
        String check = userService.idPasswordCheck(requestDto);

        // 문제 있으면 메세지와 함께 회원가입 페이지로, 문제 없으면 저장
        if (!check.equals("ok")) {
            model.addAttribute("errorMessage", check);
            return "signup";
        } else {
            userService.registerUser(requestDto);
            return "redirect:/user/login";
        }


    }

    // 카카오로그인
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}
