package com.sparta.spring02.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoUserInfoDto {
    private Long Id;
    private String nickname;
    private String email;
}
