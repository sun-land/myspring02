package com.sparta.spring02.service;

import com.sparta.spring02.dto.SignupRequestDto;
import com.sparta.spring02.model.User;
import com.sparta.spring02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 스프링 시큐리티에서 제공하는 패스워드인코더

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입 시, 유효성 체크
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    // 회원가입 시, 아이디 패스워드 조건 체크
    public String idPasswordCheck(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String pwd = signupRequestDto.getPassword();
        String repwd = signupRequestDto.getRepassword();
        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()) {
            return "이미 가입된 아이디입니다";
        } else if (!pwd.equals(repwd)) {
            return "비밀번호가 일치하지 않습니다";
        } else if (pwd.contains(username)) {
            return "비밀번호에는 아이디를 포함할 수 없습니다";
        } else {
            return "ok";
        }
    }


    // 회원가입하기
    public void registerUser(SignupRequestDto requestDto) {

        String username = requestDto.getUsername();

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password);
        userRepository.save(user);
    }
}
