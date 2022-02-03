package com.sparta.spring02.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor // 테스트 수행을 위해 생성
@NoArgsConstructor
public class SignupRequestDto {

    private String username;
    private String password;
    private String repassword;
}
