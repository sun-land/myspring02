package com.sparta.spring02.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.spring02.dto.KakaoUserInfoDto;
import com.sparta.spring02.dto.SignupRequestDto;
import com.sparta.spring02.service.KakaoUserService;
import com.sparta.spring02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

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
    public String registerUser(@Valid SignupRequestDto requestDto, Errors errors, Model model) {
        // 아이디와 패스워드의 유효성 검사
        if (errors.hasErrors()) {
            // 회원가입 실패시, 입력 데이터를 유지
            // model.addAttribute("requestDto", requestDto);

            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "signup";
        }

        // 아이디와 비밀번호 조건 체크
        String check = userService.idPasswordCheck(requestDto);
        if (!check.equals("ok")) {
            model.addAttribute("errorMessage",check);
            return "signup";
        } else {
            // 문제 없으면 회원정보 저장
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
