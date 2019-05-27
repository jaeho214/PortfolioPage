package com.dev.portfolio.service;
/*
학력사항을 처리하는 서비스
학력 삽입/ 학력 수정/ 학력 삭제/ 학력 불러와서 출력
 */

import com.dev.portfolio.model.dto.EducationDto;
import com.dev.portfolio.model.entity.EducationEntity;
import com.dev.portfolio.repository.EducationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class EducationService {

    private EducationRepository educationRepository;
    //모든 학력의 모든 내용을 가져오는 메소드
    public List<EducationDto> getEducations(){
        List<EducationDto> educationDtoList = new ArrayList<>();
        List<EducationEntity> educations = educationRepository.findAll();
        educations.forEach((education) -> {
            educationDtoList.add(
                    EducationDto.builder()
                            .grade(education.getGrade())
                            .major(education.getMajor())
                            .note(education.getNote())
                            .organ(education.getOrgan())
                            .term(education.getTerm())
                            .build()
            );
        });
        return educationDtoList;
    }

    //하나의 학력을 추가하는 메소드
    public void saveEducation(EducationDto educationDto){
        educationRepository.save(educationDto.toEntity());
    }

    //학력을 수정하는 메소드
    public void updateEducation(EducationDto educationDto){
        EducationEntity educationEntity = educationRepository.findEducationEntityByEno(educationDto.getEno());
        educationEntity.updateEducation(educationDto);
        educationRepository.save(educationEntity);
    }

    public void deleteEducation(Long eno){
        EducationEntity educationEntity = educationRepository.findEducationEntityByEno(eno);
        educationRepository.delete(educationEntity);
    }
}
