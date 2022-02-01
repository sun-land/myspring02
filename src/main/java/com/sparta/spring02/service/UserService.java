package com.sparta.spring02.service;

import com.sparta.spring02.dto.SignupRequestDto;
import com.sparta.spring02.model.User;
import com.sparta.spring02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    // 회원가입하기
    public void registerUser(SignupRequestDto requestDto) {

        String username = requestDto.getUsername();
        String pwd = requestDto.getPassword();
        String repwd = requestDto.getRepassword();

        // 1. 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) { // 아이디 찾아봤는데 디비에 있으면
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

//        // 2. 비밀번호 재입력 일치하는지
//        if (!pwd.equals(repwd)) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }

        // 3. 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());


        User user = new User(username, password);
        userRepository.save(user);
    }
}
