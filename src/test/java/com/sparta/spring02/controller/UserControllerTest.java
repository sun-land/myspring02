package com.sparta.spring02.controller;

import com.sparta.spring02.dto.SignupRequestDto;
import com.sparta.spring02.service.KakaoUserService;
import com.sparta.spring02.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @Mock
    KakaoUserService kakaoUserService;

    @Mock
    Model model;

    @Mock
    Errors errors;
//
//    @Test
//    @DisplayName("망했어 이거 아니야ㅠㅠㅠㅠㅠ")
//    void registerNormal() {
//        // given
//        String username = "sparta";
//        String password = "1234";
//        String repassword = "1234";
//
//        SignupRequestDto signupRequestDto = new SignupRequestDto(username,password,repassword);
//
//        UserController userController = new UserController(userService, kakaoUserService);
//        when(userService.idPasswordCheck(signupRequestDto))
//                .thenReturn("ok");
//
//        // when
//        userController.registerUser(signupRequestDto, errors, model);
//
//
//        // then
//        assertFalse(errors.hasErrors());
//    }
//


}