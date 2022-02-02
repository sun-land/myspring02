package com.sparta.spring02.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignupRequestDto {


    @Pattern(regexp="^[0-9a-zA-Z]*$", message = "아이디를 숫자와 영문자만 사용하여 공백없이 입력해주세요")
    @Size(min = 3, message = "아이디를 3자 이상 입력해주세요")
    @NotBlank(message = "아이디를 입력해주세요")
    private String username;

    @Pattern(regexp="^[\\S]*$", message = "비밀번호를 공백없이 입력해주세요")
    @Size(min = 4, message = "비밀번호를 4자 이상 입력해주세요")
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해주세요")
    private String repassword;
}
