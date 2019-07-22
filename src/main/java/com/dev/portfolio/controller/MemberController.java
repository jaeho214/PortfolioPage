package com.dev.portfolio.controller;

import com.dev.portfolio.model.dto.MemberDto;
import com.dev.portfolio.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio/sign")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @ApiOperation("회원가입")
    @PostMapping("/signup")
    public String signUp(@RequestBody MemberDto memberDto){
        return memberService.signUp(memberDto);
    }

    @ApiOperation("로그인")
    @PostMapping
    public String signIn(@RequestBody MemberDto memberDto){
        return memberService.signIn(memberDto);
    }

}
