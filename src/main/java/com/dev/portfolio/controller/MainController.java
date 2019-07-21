package com.dev.portfolio.controller;

import com.dev.portfolio.model.dto.CareerDto;
import com.dev.portfolio.model.dto.CertificateDto;
import com.dev.portfolio.model.dto.ContentsDto;
import com.dev.portfolio.model.dto.EducationDto;
import com.dev.portfolio.service.CareerService;
import com.dev.portfolio.service.CertificateService;
import com.dev.portfolio.service.ContentsService;
import com.dev.portfolio.service.EducationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolio/main")
@AllArgsConstructor
public class MainController {

    private CareerService careerService;
    private CertificateService certificateService;
    private EducationService educationService;
    private ContentsService contentsService;

//    public MainController(CareerService career, CertificateService cert, EducationService edu){
//        this.careerService = career;
//        this.certificateService = cert;
//        this.educationService = edu;
//    }


    //경력 사항
    @GetMapping("/career")
    public List<CareerDto> getCareers(){
        return careerService.getCareers();
    }

    @PutMapping("/careerInsert")
    public void saveCareer(@RequestBody CareerDto careerDto){
        careerService.saveCareer(careerDto);
    }

    @PostMapping("/careerUpdate")
    public void updateCareer(@RequestBody CareerDto careerDto){
        careerService.updateCareer(careerDto);
    }

    @DeleteMapping("/careerDelete")
    public void deleteCareer(@RequestBody CareerDto careerDto) { careerService.deleteCareer(careerDto.getCareerNo());}


    // 자격 사항
    @GetMapping("/certificate")
    public List<CertificateDto> getCertificates(){
        return certificateService.getCertificates();
    }

    @PutMapping("/certificateInsert")
    public void saveCertificate(@RequestBody CertificateDto certificateDto){
        certificateService.saveCertificate(certificateDto);
    }

    @PostMapping("/certificateUpdate")
    public void updateCertificate(@RequestBody CertificateDto certificateDto){
        certificateService.updateCertificate(certificateDto);
    }

    @DeleteMapping("/certificateDelete")
    public void deleteCertificate(@RequestBody CertificateDto certificateDto){
        certificateService.deleteCertificate(certificateDto.getCertiNo());
    }


    //학력
    @GetMapping("/education")
    public List<EducationDto> getEducations(){
        return educationService.getEducations();
    }

    @PutMapping("/educationInsert")
    public void saveEducation(@RequestBody EducationDto educationDto){
        educationService.saveEducation(educationDto);
    }

    @PostMapping("/educationUpdate")
    public void updateEducation(@RequestBody EducationDto educationDto){
        educationService.updateEducation(educationDto);
    }

    @DeleteMapping("/educationDelete")
    public void deleteEducation(@RequestBody EducationDto educationDto) {
        educationService.deleteEducation(educationDto.getEno());
    }

    //자기소개서
    @GetMapping("/contents")
    public List<ContentsDto> getContents(){
        return contentsService.getContents();
    }

    @PutMapping("/contentsInsert")
    public void saveContents(@RequestBody ContentsDto contentsDto){
        contentsService.saveContents(contentsDto);
    }

    @PostMapping("/contentsUpdate")
    public void updateContents(@RequestBody ContentsDto contentsDto){
        contentsService.updateContents(contentsDto);
    }

    @DeleteMapping("/contentsDelete")
    public void deleteContents(@RequestBody ContentsDto contentsDto) { contentsService.deleteContents(contentsDto.getContentNo());}


}
