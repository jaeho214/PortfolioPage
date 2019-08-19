package com.dev.portfolio.service;
/*
회원정보를 관리하는 서비스
회원가입/ 정보수정/ 회원탈퇴/ 불러와서 출력
 */

import com.dev.portfolio.exception.UserDefineException;
import com.dev.portfolio.model.dto.MemberDto;
import com.dev.portfolio.model.dto.SignInDto;
import com.dev.portfolio.model.entity.Member;
import com.dev.portfolio.model.entity.MemberRole;
import com.dev.portfolio.repository.MemberRepository;
import com.dev.portfolio.security.JwtProvider;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class MemberService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public String signUp(MemberDto memberDto, String role){ //회원 가입
        log.info(memberDto.getId() + "님의 회원가입 시도");
        if(memberRepository.findById(memberDto.getId()).isPresent())
            throw new UserDefineException("아이디가 중복됩니다.");

        Member member = memberDto.toEntity();
        member.setPw(passwordEncoder.encode(member.getPw()));

        if(role.equals("ADMIN")){
            List<MemberRole> list = new ArrayList<>();
            MemberRole admin = new MemberRole("ADMIN");
            MemberRole user = new MemberRole("USER");
            list.add(admin);
            list.add(user);
            member.setRoles(list);
        }else{
            List<MemberRole> list = new ArrayList<>();
            MemberRole user = new MemberRole("USER");
            list.add(user);
            member.setRoles(list);
        }

        memberRepository.save(member);

        return jwtProvider.createToken(member.getId(),member.getRoles());
    }

    public String signIn(SignInDto signInDto){
        log.info("----login---- " + signInDto.getId() + " " + signInDto.getPw());

        Member member = memberRepository.findById(signInDto.getId()).orElseThrow(() -> new UserDefineException("아이디를 잘못 입력하였습니다."));

        if(!passwordEncoder.matches(signInDto.getPw(),member.getPw())) {
            throw new UserDefineException("비밀번호를 잘못 입력하셨습니다.");
        }

        return jwtProvider.createToken(member.getId(), member.getRoles());
    }

    public void update(MemberDto memberDto){
        memberRepository.save(memberDto.toEntity());
    }

    public void delete(SignInDto signInDto){
        //비밀번호 확인 후 같으면 삭제
        memberRepository.deleteById(signInDto.getId());
    }

    public MemberDto getMemberInfo(MemberDto memberDto){
        Member member = memberRepository.findById(memberDto.getId()).orElseThrow(() -> new UserDefineException("조회할 수 없음"));

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
        List<Member> membersList = memberRepository.findAll();
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
