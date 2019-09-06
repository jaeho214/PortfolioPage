package com.dev.portfolio.service;
/*
자소서 항목들을 채우는 서비스
자소서 삽입/ 자소서 수정/ 자소서 삭제/ 불러와서 출력
 */

import com.dev.portfolio.model.dto.ContentsDto;
import com.dev.portfolio.model.dto.ContentsInItemDto;
import com.dev.portfolio.model.entity.Contents;
import com.dev.portfolio.model.entity.Member;
import com.dev.portfolio.repository.ContentsRepository;
import com.dev.portfolio.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentsService {
    private ContentsRepository contentsRepository;
    private JwtProvider jwtProvider;

    public ContentsService(ContentsRepository contentsRepository, JwtProvider jwtProvider){
        this.contentsRepository = contentsRepository;
        this.jwtProvider = jwtProvider;
    }


    public List<ContentsDto> getContents(String token){
        String userId = jwtProvider.getUserIdByToken(token);
        List<ContentsDto> contentsDtoList = new ArrayList<>();
        List<Contents> contents = contentsRepository.findAllByMember_Id(userId);
        contents.forEach((content) -> contentsDtoList.add(
                ContentsDto.builder()
                        .contentNo(content.getContentNo())
                        .category(content.getCategory())
                        .content(content.getContent())
                        .build()
        ));
        return contentsDtoList;
    }

    //하나의 자기소개서를 추가하는 메소드
    public void saveContents(String token, ContentsDto contentsDto){
        String userId = jwtProvider.getUserIdByToken(token);
        Member member = contentsDto.getMember();
        member.setId(userId);
        contentsDto.setMember(member);
        contentsRepository.save(contentsDto.toEntity());
    }

    //자기소개서를 수정하는 메소드
    public void updateContents(String token, ContentsDto contentsDto){
        String userId = jwtProvider.getUserIdByToken(token);
        Contents contents = contentsRepository.findContentsByContentNoAndMember_Id(contentsDto.getContentNo(), userId);
        contents.updateContents(contentsDto);
        contentsRepository.save(contents);
    }

    //자기소개서를 삭제하는 메소드
    public void deleteContents(String token, Long contentsNo){
        String userId = jwtProvider.getUserIdByToken(token);
        Contents contents = contentsRepository.findContentsByContentNoAndMember_Id(contentsNo, userId);
        contentsRepository.delete(contents);
    }
}
