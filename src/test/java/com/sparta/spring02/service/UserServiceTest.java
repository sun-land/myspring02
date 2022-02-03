package com.sparta.spring02.service;

import com.sparta.spring02.dto.SignupRequestDto;
import com.sparta.spring02.model.User;
import com.sparta.spring02.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("아이디 중복 체크1")
    void idDuplication1() {
        // given
        String username = "sparta";
        String password = "1234";
        String repassword = "1234";


        SignupRequestDto signupRequestDto = new SignupRequestDto(username, password, repassword);
        User user = new User(username,password);

        UserService userService = new UserService(userRepository,passwordEncoder);
        when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(user));

        // when
        String result = userService.idPasswordCheck(signupRequestDto);

        // then
        assertEquals("이미 가입된 아이디입니다",result);
    }

    @Test
    @DisplayName("아이디 중복 체크2")
    void idDuplication2() {
        // given
        String username = "1234qwer";
        String password = "12345678";
        String repassword = "12345678";


        SignupRequestDto signupRequestDto = new SignupRequestDto(username, password, repassword);
        User user = new User(username,password);

        UserService userService = new UserService(userRepository,passwordEncoder);
        when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(user));

        // when
        String result = userService.idPasswordCheck(signupRequestDto);

        // then
        assertEquals("이미 가입된 아이디입니다",result);
    }

    @Test
    @DisplayName("비밀번호와 비밀번호 확인 불일치1")
    void passwordEqual1() {
        // given
        String username = "sparta";
        String password = "1234";
        String repassword = "5678";


        SignupRequestDto signupRequestDto = new SignupRequestDto(username, password, repassword);

        UserService userService = new UserService(userRepository,passwordEncoder);
        when(userRepository.findByUsername(username))
                .thenReturn(Optional.empty());

        // when
        String result = userService.idPasswordCheck(signupRequestDto);

        // then
        assertEquals("비밀번호가 일치하지 않습니다",result);
    }


    @Test
    @DisplayName("비밀번호와 비밀번호 확인 불일치2")
    void passwordEqual2() {
        // given
        String username = "sparta";
        String password = "1234";
        String repassword = "12345";


        SignupRequestDto signupRequestDto = new SignupRequestDto(username, password, repassword);

        UserService userService = new UserService(userRepository,passwordEncoder);
        when(userRepository.findByUsername(username))
                .thenReturn(Optional.empty());

        // when
        String result = userService.idPasswordCheck(signupRequestDto);

        // then
        assertEquals("비밀번호가 일치하지 않습니다",result);
    }


    @Test
    @DisplayName("비밀번호에 아이디 포함1")
    void containId1() {
        // given
        String username = "sparta";
        String password = "sparta1234";
        String repassword = "sparta1234";


        SignupRequestDto signupRequestDto = new SignupRequestDto(username, password, repassword);

        UserService userService = new UserService(userRepository,passwordEncoder);
        when(userRepository.findByUsername(username))
                .thenReturn(Optional.empty());

        // when
        String result = userService.idPasswordCheck(signupRequestDto);

        // then
        assertEquals("비밀번호에는 아이디를 포함할 수 없습니다",result);
    }

    @Test
    @DisplayName("비밀번호에 아이디 포함2")
    void containId2() {
        // given
        String username = "cs12";
        String password = "kkcs1234";
        String repassword = "kkcs1234";


        SignupRequestDto signupRequestDto = new SignupRequestDto(username, password, repassword);

        UserService userService = new UserService(userRepository,passwordEncoder);
        when(userRepository.findByUsername(username))
                .thenReturn(Optional.empty());

        // when
        String result = userService.idPasswordCheck(signupRequestDto);

        // then
        assertEquals("비밀번호에는 아이디를 포함할 수 없습니다",result);
    }

}