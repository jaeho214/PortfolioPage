package com.dev.portfolio.service;
/*
자소서 항목들을 채우는 서비스
자소서 삽입/ 자소서 수정/ 자소서 삭제/ 불러와서 출력
 */

import com.dev.portfolio.model.dto.ContentsDto;
import com.dev.portfolio.model.entity.ContentsEntity;
import com.dev.portfolio.repository.ContentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ContentsService {
    private ContentsRepository contentsRepository;


    public List<ContentsDto> getContents(){
        List<ContentsDto> contentsDtoList = new ArrayList<>();
        List<ContentsEntity> contents = contentsRepository.findAll();
        contents.forEach((content) -> {
            contentsDtoList.add(
                    ContentsDto.builder()
                            .category(content.getCategory())
                            .content(content.getContent())
                            .build()
            );
        });
        return contentsDtoList;
    }

    //하나의 자기소개서를 추가하는 메소드
    public void saveContents(ContentsDto contentsDto){
        contentsRepository.save(contentsDto.toEntity());
    }

    //자기소개서를 수정하는 메소드
    public void updateContents(ContentsDto contentsDto){
        ContentsEntity contentsEntity = contentsRepository.findContentsEntityByContentNo(contentsDto.getContentNo());
        contentsEntity.updateContents(contentsDto);
        contentsRepository.save(contentsEntity);
    }

    //자기소개서를 삭제하는 메소드
    public void deleteContents(Long contentsNo){
        ContentsEntity contentsEntity = contentsRepository.findContentsEntityByContentNo(contentsNo);
        contentsRepository.delete(contentsEntity);
    }
}
