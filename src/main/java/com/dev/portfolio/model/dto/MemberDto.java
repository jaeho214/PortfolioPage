package com.dev.portfolio.model.dto;

import com.dev.portfolio.model.entity.Member;
import com.dev.portfolio.model.entity.MemberRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberDto {
    private String id;
    private String pw;
    private String koName;
    private String enName;
    private String phoneNum;
    private String socialNum; // 주민등록번호
    private String email;
    private String sex;
    private String uri;
    private List<MemberRole> roles;

    public MemberDto(){}

    @Builder
    public MemberDto(String id, String pw, String koName, String enName,
                     String phoneNum, String socialNum, String email,String sex, String uri, List<MemberRole> roles){
        this.id = id;
        this.pw = pw;
        this.koName = koName;
        this.enName = enName;
        this.phoneNum = phoneNum;
        this.socialNum = socialNum;
        this.email = email;
        this.sex = sex;
        this.uri = uri;
        this.roles = roles;
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .pw(pw)
                .koName(koName)
                .enName(enName)
                .phoneNum(phoneNum)
                .socialNum(socialNum)
                .email(email)
                .sex(sex)
                .uri(uri)
                .build();
    }

}
