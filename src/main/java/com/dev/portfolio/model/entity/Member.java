package com.dev.portfolio.model.entity;
/*
회원정보
 */
import com.dev.portfolio.model.dto.MemberDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity @Setter @Getter
@NoArgsConstructor
@Table(name = "tbl_member")
public class Member {
    @Id
    private String id;

    private String pw;
    private String koName;
    private String enName;
    private String phoneNum;
    private String socialNum; // 주민등록번호
    private String email;
    private String sex;
    private String uri;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name="member")
//    private List<MemberRole> roles;

    @Builder
    public Member(String id, String pw, String koName, String enName,
                  String phoneNum, String socialNum, String email, String sex, String uri){
        this.id = id;
        this.pw = pw;
        this.koName = koName;
        this.enName = enName;
        this.phoneNum = phoneNum;
        this.socialNum = socialNum;
        this.email = email;
        this.sex = sex;
        this.uri = uri;
    }

    public void updateMember(MemberDto memberDto){
        this.pw = memberDto.getPw();
        this.koName = memberDto.getKoName();
        this.enName = memberDto.getEnName();
        this.phoneNum = memberDto.getPhoneNum();
        this.socialNum = memberDto.getSocialNum();
        this.email = memberDto.getEmail();
        this.sex = memberDto.getSex();
        this.uri = memberDto.getUri();
    }
}
