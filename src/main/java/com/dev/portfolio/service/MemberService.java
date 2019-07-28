package com.dev.portfolio.service;
/*
회원정보를 관리하는 서비스
회원가입/ 정보수정/ 회원탈퇴/ 불러와서 출력
 */

import com.dev.portfolio.exception.UserDefineException;
import com.dev.portfolio.model.dto.MemberDto;
import com.dev.portfolio.model.dto.SignInDto;
import com.dev.portfolio.model.entity.MemberEntity;
import com.dev.portfolio.model.entity.MemberRole;
import com.dev.portfolio.repository.MemberRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class MemberService {//implements UserDetailsService {
    private final MemberRepository memberRepository;
    //private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository){//, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        //this.passwordEncoder = passwordEncoder;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        MemberEntity member = memberRepository.findById(username)
//                .orElseThrow(()-> new UserDefineException(username));
//        return new User(member.getId(), member.getPw(), makeGrantedAuthority(member.getRoles()));
//    }
//
//    private List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles){
//        List<GrantedAuthority> list = new ArrayList<>();
//        roles.forEach(
//                role -> list.add(
//                        new SimpleGrantedAuthority("ROLE_" + role.getRoleName())
//                )
//        );
//        return list;
//    }

    public void signUp(MemberDto memberDto){ //회원 가입
        if(memberRepository.findById(memberDto.getId()).isPresent())
            throw new UserDefineException("아이디가 중복됩니다.");

        MemberEntity member = memberDto.toEntity();
//        member.setPw(passwordEncoder.encode(member.getPw()));

        memberRepository.save(member);

    }

    public void signIn(SignInDto signInDto){
        log.info("----login---- " + signInDto.getId() + " " + signInDto.getPw());

        MemberEntity member = memberRepository.findById(signInDto.getId()).orElseThrow(() -> new UserDefineException("아이디를 잘못 입력하였습니다."));

//        if(!passwordEncoder.matches(memberDto.getPw(),member.getPw())) {
//            throw new UserDefineException("비밀번호를 잘못 입력하셨습니다.");
//        }
    }

    public void update(MemberDto memberDto){
        memberRepository.save(memberDto.toEntity());
    }

    public void delete(SignInDto signInDto){
        //비밀번호 확인 후 같으면 삭제
        memberRepository.deleteById(signInDto.getId());
    }

    public MemberDto getMemberInfo(MemberDto memberDto){
        MemberEntity member = memberRepository.findById(memberDto.getId()).orElseThrow(() -> new UserDefineException("조회할 수 없음"));

        return MemberDto.builder()
                .koName(member.getKoName())
                .enName(member.getEnName())
                .phoneNum(member.getPhoneNum())
                .uri(member.getUri())
                .sex(member.getSex())
                .email(member.getEmail())
                .build();
    }


    public List<MemberDto> getAllMembersInfo() {
        List<MemberEntity> membersList = memberRepository.findAll();
        List<MemberDto> memberDtos = new ArrayList<>();

        membersList.forEach(member -> {
            memberDtos.add(
                    MemberDto.builder()
                    .id(member.getId())
                    .koName(member.getKoName())
                    .phoneNum(member.getPhoneNum())
                    .sex(member.getSex())
                    .email(member.getEmail())
                    .build()
            );
        });
        return memberDtos;
    }
}
