package com.dev.portfolio.model.entity;
/*
회원정보
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "tbl_member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long mno;


    private String id;

    private String pw;
    private String KoName;
    private String EnName;
    private String phoneNum;
    private String socialNum; // 주민등록번호
    private String email;
    private String sex;
    private String uri;

}
