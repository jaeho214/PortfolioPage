package com.dev.portfolio.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_member_roles")
@ToString
public class MemberRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleNo;

    private String roleName;

    public MemberRole(){}

    public MemberRole(String roleName){
        this.roleName = roleName;
    }
}
