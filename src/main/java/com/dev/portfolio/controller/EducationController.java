package com.dev.portfolio.controller;

import com.dev.portfolio.model.dto.EducationDto;
import com.dev.portfolio.service.EducationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolio/main/education")
public class EducationController {
    private EducationService educationService;

    public EducationController(EducationService educationService){
        this.educationService = educationService;
    }

    //학력
    @ApiOperation("학력 조회")
    @GetMapping
    public List<EducationDto> getEducations(@RequestHeader(name = "Authorization") String token){
        return educationService.getEducations(token);
    }

    @ApiOperation("학력 추가")
    @PostMapping
    public void saveEducation(@RequestHeader(name = "Authorization") String token, @RequestBody EducationDto educationDto){
        educationService.saveEducation(token, educationDto);
    }

    @ApiOperation("학력 수정")
    @PutMapping
    public void updateEducation(@RequestHeader(name = "Authorization") String token, @RequestBody EducationDto educationDto){
        educationService.updateEducation(token, educationDto);
    }

    @ApiOperation("학력 삭제")
    @DeleteMapping("/{educationNo}")
    public void deleteEducation(@RequestHeader(name = "Authorization") String token, @PathVariable Long educationNo) {
        educationService.deleteEducation(token, educationNo);
    }
}
