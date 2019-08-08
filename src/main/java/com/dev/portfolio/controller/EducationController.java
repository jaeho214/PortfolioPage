package com.dev.portfolio.controller;

import com.dev.portfolio.model.dto.EducationDto;
import com.dev.portfolio.service.EducationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/portfolio/main/education")
public class EducationController {
    private EducationService educationService;

    public EducationController(EducationService educationService){
        this.educationService = educationService;
    }

    //학력
    @ApiOperation("학력 조회")
    @GetMapping
    public List<EducationDto> getEducations(){
        return educationService.getEducations();
    }

    @ApiOperation("학력 추가")
    @PostMapping
    public void saveEducation(@RequestBody EducationDto educationDto){
        educationService.saveEducation(educationDto);
    }

    @ApiOperation("학력 수정")
    @PutMapping
    public void updateEducation(@RequestBody EducationDto educationDto){
        educationService.updateEducation(educationDto);
    }

    @ApiOperation("학력 삭제")
    @DeleteMapping("/{educationNo}")
    public void deleteEducation(@PathVariable Long educationNo) {
        educationService.deleteEducation(educationNo);
    }
}
