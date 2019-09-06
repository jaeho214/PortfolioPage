package com.dev.portfolio.service;
/*
학력사항을 처리하는 서비스
학력 삽입/ 학력 수정/ 학력 삭제/ 학력 불러와서 출력
 */

import com.dev.portfolio.model.dto.EducationDto;
import com.dev.portfolio.model.entity.Education;
import com.dev.portfolio.model.entity.Member;
import com.dev.portfolio.repository.EducationRepository;
import com.dev.portfolio.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EducationService {

    private EducationRepository educationRepository;
    private JwtProvider jwtProvider;

    public EducationService(EducationRepository educationRepository, JwtProvider jwtProvider){
        this.educationRepository = educationRepository;
        this.jwtProvider = jwtProvider;
    }


    //모든 학력의 모든 내용을 가져오는 메소드
    public List<EducationDto> getEducations(String token){
        String userId = jwtProvider.getUserIdByToken(token);
        List<EducationDto> educationDtoList = new ArrayList<>();
        List<Education> educations = educationRepository.findAllByMember_Id(userId);
        educations.forEach((education) -> educationDtoList.add(
                EducationDto.builder()
                        .eno(education.getEno())
                        .grade(education.getGrade())
                        .major(education.getMajor())
                        .note(education.getNote())
                        .organ(education.getOrgan())
                        .term(education.getTerm())
                        .build()
        ));
        return educationDtoList;
    }

    //하나의 학력을 추가하는 메소드
    public void saveEducation(String token, EducationDto educationDto){
        String userId = jwtProvider.getUserIdByToken(token);
        Member member = new Member();
        member.setId(userId);
        educationDto.setMember(member);
        educationRepository.save(educationDto.toEntity());
    }

    //학력을 수정하는 메소드
    public void updateEducation(String token, EducationDto educationDto){
        String userId = jwtProvider.getUserIdByToken(token);
        Education education = educationRepository.findEducationByEnoAndMember_Id(educationDto.getEno(), userId);
        education.updateEducation(educationDto);
        educationRepository.save(education);
    }

    public void deleteEducation(String token, Long eno){
        String userId = jwtProvider.getUserIdByToken(token);
        Education education = educationRepository.findEducationByEnoAndMember_Id(eno, userId);
        educationRepository.delete(education);
    }
}
