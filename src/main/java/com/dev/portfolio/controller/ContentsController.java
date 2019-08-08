package com.dev.portfolio.controller;

import com.dev.portfolio.model.dto.ContentsDto;
import com.dev.portfolio.service.ContentsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/portfolio/main/contents")
public class ContentsController {
    private ContentsService contentsService;

    public ContentsController(ContentsService contentsService){
        this.contentsService = contentsService;
    }

    //자기소개서
    @ApiOperation("자기소개서 항목들 조회")
    @GetMapping
    public List<ContentsDto> getContents(){
        return contentsService.getContents();
    }

    @ApiOperation("자기소개서 항목 추가")
    @PostMapping
    public void saveContents(@RequestBody ContentsDto contentsDto){
        contentsService.saveContents(contentsDto);
    }

    @ApiOperation("자기소개서 항목 변경")
    @PutMapping
    public void updateContents(@RequestBody ContentsDto contentsDto){
        contentsService.updateContents(contentsDto);
    }

    @ApiOperation("자기소개서 항목 삭제")
    @DeleteMapping
    public void deleteContents(@RequestBody ContentsDto contentsDto) { contentsService.deleteContents(contentsDto.getContentNo());}


}
