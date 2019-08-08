package com.dev.portfolio.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInDto {
    private String id;
    private String pw;

    public SignInDto(){}

    @Builder
    SignInDto(String id, String pw){
        this.id = id;
        this.pw = pw;
    }

}
