package com.dev.portfolio.controller;

import com.dev.portfolio.model.dto.MemberDto;
import com.dev.portfolio.model.dto.SignInDto;
import com.dev.portfolio.security.JwtProvider;
import com.dev.portfolio.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/portfolio/sign")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @ApiOperation("관리자 회원가입")
    @PostMapping("/admin")
    public String signUpAdmin(@RequestBody MemberDto memberDto){
        return memberService.signUp(memberDto, "ADMIN");
    }

    @ApiOperation("일반 회원 회원가입")
    @PostMapping("/user")
    public String signUpUser(@RequestBody MemberDto memberDto){
        return memberService.signUp(memberDto, "USER");
    }

    @ApiOperation("로그인")
    @PostMapping
    public String signIn(@RequestBody SignInDto signInDto){
        memberService.signIn(signInDto);
        return "로그인";
    }

    @ApiOperation("회원 정보 수정")
    @PutMapping
    public void update(@RequestBody MemberDto memberDto){
        memberService.update(memberDto);
    }

    @ApiOperation("회원 탈퇴")
    @DeleteMapping
    public void delete(@RequestBody SignInDto signInDto){
        memberService.delete(signInDto);
    }

    @ApiOperation("회원 정보 조회")
    @GetMapping("/info")
    public MemberDto getMember(@RequestBody MemberDto memberDto){ // 토큰 방식을로 바꿔야 한다~
        return memberService.getMemberInfo(memberDto);
    }

    @ApiOperation("전체 회원 정보 조회")
    @GetMapping("/manageMember")
    public List<MemberDto> getAllMembers(){
        return memberService.getAllMembersInfo();
    }

}
