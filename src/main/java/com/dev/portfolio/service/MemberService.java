package com.dev.portfolio.service;
/*
회원정보를 관리하는 서비스
회원가입/ 정보수정/ 회원탈퇴/ 불러와서 출력
 */

import com.dev.portfolio.exception.UserDefineException;
import com.dev.portfolio.model.dto.MemberDto;
import com.dev.portfolio.model.entity.MemberEntity;
import com.dev.portfolio.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public String signUp(MemberDto memberDto){ //회원 가입
        if(memberRepository.findById(memberDto.getId()).isPresent())
            throw new UserDefineException("아이디가 중복됩니다.");

        MemberEntity member = memberDto.toEntity();
        memberRepository.save(member);

        return member.getId();
    }



}
