package com.dev.portfolio.controller;

import com.dev.portfolio.model.dto.CareerDto;
import com.dev.portfolio.service.CareerService;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/portfolio/main/career")
public class CareerController {
    private CareerService careerService;

    public CareerController(CareerService careerService){
        this.careerService = careerService;
    }

    //경력 사항
    @ApiOperation("경력 조회")
    @GetMapping
    public List<CareerDto> getCareers(){
        return careerService.getCareers();
    }

    @ApiOperation("경력 추가")
    @PostMapping
    public void saveCareer(@RequestBody CareerDto careerDto){
        careerService.saveCareer(careerDto);
    }

    @ApiOperation("경력 수정")
    @PutMapping
    public void updateCareer(@RequestBody CareerDto careerDto){
        careerService.updateCareer(careerDto);
    }

    @ApiOperation("경력 삭제")
    @DeleteMapping("/{careerNo}")
    public void deleteCareer(@PathVariable Long careerNo) { careerService.deleteCareer(careerNo);}

}
