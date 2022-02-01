package com.sparta.spring02.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class SignupRequestDto {

//    @NotBlank(message = "아이디를 입력해주세요")
    private String username;

//    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

//    @NotBlank(message = "비밀번호 확인을 입력해주세요")
    private String repassword;
}
