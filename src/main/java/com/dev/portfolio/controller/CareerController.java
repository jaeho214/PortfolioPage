package com.dev.portfolio.controller;

import com.dev.portfolio.model.dto.CareerDto;
import com.dev.portfolio.service.CareerService;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolio/main/career")
public class CareerController {
    private CareerService careerService;

    public CareerController(CareerService careerService){
        this.careerService = careerService;
    }

    //경력 사항
    @ApiOperation("경력 조회")
    @GetMapping
    public List<CareerDto> getCareers(@RequestHeader(name = "Authorization") String token){
        return careerService.getCareers(token);
    }

    @ApiOperation("경력 추가")
    @PostMapping
    public void saveCareer(@RequestHeader(name = "Authorization") String token, @RequestBody CareerDto careerDto){
        careerService.saveCareer(token, careerDto);
    }

    @ApiOperation("경력 수정")
    @PutMapping
    public void updateCareer(@RequestHeader(name = "Authorization") String token, @RequestBody CareerDto careerDto){
        careerService.updateCareer(token, careerDto);
    }

    @ApiOperation("경력 삭제")
    @DeleteMapping("/{careerNo}")
    public void deleteCareer(@RequestHeader(name = "Authorization") String token, @PathVariable Long careerNo) {
        careerService.deleteCareer(token, careerNo);
    }

}
