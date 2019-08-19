package com.dev.portfolio.controller;

import com.dev.portfolio.model.dto.ContentsDto;
import com.dev.portfolio.service.ContentsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolio/main/contents")
public class ContentsController {
    private ContentsService contentsService;

    public ContentsController(ContentsService contentsService){
        this.contentsService = contentsService;
    }

    //자기소개서
    @ApiOperation("자기소개서 항목들 조회")
    @GetMapping
    public List<ContentsDto> getContents(@RequestHeader(name = "Authorization") String token){
        return contentsService.getContents(token);
    }

    @ApiOperation("자기소개서 항목 추가")
    @PostMapping
    public void saveContents(@RequestHeader(name = "Authorization") String token, @RequestBody ContentsDto contentsDto){
        contentsService.saveContents(token, contentsDto);
    }

    @ApiOperation("자기소개서 항목 변경")
    @PutMapping
    public void updateContents(@RequestHeader(name = "Authorization") String token, @RequestBody ContentsDto contentsDto){
        contentsService.updateContents(token, contentsDto);
    }

    @ApiOperation("자기소개서 항목 삭제")
    @DeleteMapping
    public void deleteContents(@RequestHeader(name = "Authorization") String token, @RequestBody ContentsDto contentsDto) {
        contentsService.deleteContents(token, contentsDto.getContentNo());
    }


}
