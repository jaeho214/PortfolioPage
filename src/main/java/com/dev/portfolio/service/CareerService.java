package com.dev.portfolio.service;
/*
경력사항을 처리하는 서비스
경력 삽입/ 경력 수정/ 경력 삭제/ 경력 불러와서 출력
 */

import com.dev.portfolio.model.dto.CareerDto;
import com.dev.portfolio.model.entity.Career;
import com.dev.portfolio.model.entity.Member;
import com.dev.portfolio.repository.CareerRepository;
import com.dev.portfolio.security.JwtProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CareerService {

    private CareerRepository careerRepository;
    private JwtProvider jwtProvider;

    public CareerService(CareerRepository careerRepository, JwtProvider jwtProvider){
        this.careerRepository = careerRepository;
        this.jwtProvider = jwtProvider;
    }

    //모든 경력사항의 모든 내용을 가져오는 메소드
    public List<CareerDto> getCareers(String token){
        String userId = jwtProvider.getUserIdByToken(token);

        List<CareerDto> careerDtoList = new ArrayList<>();
        List<Career> careers = careerRepository.findAllByMember_Id(userId);
        careers.forEach((career) -> careerDtoList.add(
                CareerDto.builder()
                        .careerNo(career.getCareerNo())
                        .company(career.getCompany())
                        .department(career.getDepartment())
                        .details(career.getDetails())
                        .reason(career.getReason())
                        .term(career.getTerm())
                        .build()
        ));
        return careerDtoList;
    }

    //하나의 경력을 추가하는 메소드
    public void saveCareer(String token, CareerDto careerDto) {
        String userId = jwtProvider.getUserIdByToken(token);
        Member member = careerDto.getMember();
        member.setId(userId);
        careerDto.setMember(member);
        careerRepository.save(careerDto.toEntity());
    }

    //경력을 수정하는 메소드
    public void updateCareer(String token, CareerDto careerDto){
        String userId = jwtProvider.getUserIdByToken(token);
        Career career = careerRepository.findCareerByCareerNoAndMember_Id(careerDto.getCareerNo(), userId);
        career.updateCareer(careerDto);
        careerRepository.save(career);
    }

    //경력을 삭제하는 메소드
    public void deleteCareer(String token, Long careerNo){
        String userId = jwtProvider.getUserIdByToken(token);
        Career career = careerRepository.findCareerByCareerNoAndMember_Id(careerNo, userId);
        careerRepository.delete(career);
    }
}
