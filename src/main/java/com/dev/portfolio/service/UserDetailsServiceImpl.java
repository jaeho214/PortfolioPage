package com.dev.portfolio.service;

import com.dev.portfolio.model.entity.Member;
import com.dev.portfolio.model.entity.MemberRole;
import com.dev.portfolio.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private MemberRepository memberRepository;

    public UserDetailsServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username);

        if(member == null) {
            throw new UsernameNotFoundException(username); // 사용자의 아이디를 받아서 그 아이디를 통해 DB에 저장된 데이터들 중에서 사용자의 정보를 찾고 없으면 예외 발생
        }
        return new User(member.getId(), member.getPw(), makeGrantedAuthority(member.getRoles())); // 있으면 사용자의 아이디, 비밀번호, 권한을 리턴
        //User 클래스가 UserDetails 인터페이스를 구현하는 클래스
    }

    private List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles){
        List<GrantedAuthority> list = new ArrayList<>();
        roles.forEach(
                role -> list.add(
                        new SimpleGrantedAuthority("ROLE_" + role.getRoleName())
                )
        );
        return list;
    }
}
