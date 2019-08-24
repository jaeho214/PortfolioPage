package com.dev.portfolio.controller;

import com.dev.portfolio.model.dto.MemberDto;
import com.dev.portfolio.model.dto.SignInDto;
import com.dev.portfolio.security.JwtProvider;
import com.dev.portfolio.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolio/sign")
@Slf4j
public class MemberController {

    private MemberService memberService;
    private JwtProvider jwtProvider;

    public MemberController(MemberService memberService, JwtProvider jwtProvider){
        this.memberService = memberService;
        this.jwtProvider = jwtProvider;
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
        return memberService.signIn(signInDto);

    }

    @ApiOperation("회원 정보 수정")
    @PutMapping
    public void update(@RequestHeader(name = "Authorization") String token, @RequestBody MemberDto memberDto){
        String userId = jwtProvider.getUserIdByToken(token);
        memberService.update(userId, memberDto);
    }

    @ApiOperation("회원 탈퇴")
    @DeleteMapping
    public void delete(@RequestHeader(name = "Authorization") String token){
        String userId = jwtProvider.getUserIdByToken(token);
        memberService.delete(userId);
    }

    @ApiOperation("회원 정보 조회")
    @GetMapping("/info")
    public MemberDto getMember(@RequestHeader(name = "Authorization") String token){
        System.out.println(token);
        String userId = jwtProvider.getUserIdByToken(token);
        log.info("=============================");
        log.info(userId + "의 회원 정보 조회");
        log.info("=============================");
        return memberService.getMemberInfo(userId);
    }

    @ApiOperation("전체 회원 정보 조회")
    @GetMapping("/manageMember")
    public List<MemberDto> getAllMembers(){
        return memberService.getAllMembersInfo();
    }

}
