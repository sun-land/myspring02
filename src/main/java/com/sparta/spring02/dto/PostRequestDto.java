package com.sparta.spring02.dto;

import com.sparta.spring02.model.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostRequestDto {

    private String username;
    private String title;
    private String contents;


}
