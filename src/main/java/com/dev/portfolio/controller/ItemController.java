package com.dev.portfolio.controller;

//자기소개서(Contents)들을 여러개 모아서 Item과 Join

import com.dev.portfolio.model.dto.ContentsDto;
import com.dev.portfolio.model.dto.ContentsInItemDto;
import com.dev.portfolio.model.dto.ItemDto;
import com.dev.portfolio.model.dto.ItemWrapperDto;
import com.dev.portfolio.security.JwtProvider;
import com.dev.portfolio.service.ItemService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Log
@RestController
@RequestMapping("/portfolio/main/introduction")
public class ItemController {

    private final ItemService itemService;
    private JwtProvider jwtProvider;

    public ItemController(ItemService itemService, JwtProvider jwtProvider) {
        this.itemService = itemService;
        this.jwtProvider = jwtProvider;
    }

    @ApiOperation("자기소개서 모음 조회")
    @GetMapping
    public List<ItemDto> getItems(@RequestHeader(name = "Authorization") String token){
        String userId = jwtProvider.getUserIdByToken(token);
        log.info("============= " + userId + "의 자기소개서 목록 출력 =============");
        return itemService.getItems(userId);
    }

    @ApiOperation("자기소개서 제목을 눌렀을 때 나오는 항목들 출력")
    @GetMapping("/{item_no}")
    public List<ContentsInItemDto> getContentsInItem(@RequestHeader(name = "Authorization") String token, @PathVariable Long item_no){
        String userId = jwtProvider.getUserIdByToken(token);
        log.info("============= " + userId+"의 자기소개서 " + item_no + "번 출력 =============");
        return itemService.getContentsInItem(userId,item_no);
    }

    @ApiOperation("자기소개서 제목을 눌렀을 때 나오는 항목 제거")
    @DeleteMapping("/{item_no}/{contents_no}")
    public void deleteContentsInItem(@RequestHeader(name = "Authorization") String token, @PathVariable Long item_no, @PathVariable Long contents_no){
        String userId = jwtProvider.getUserIdByToken(token);
        log.info("============= " + userId+"의 자기소개서 " + item_no + "번의" +contents_no +"번 삭제 =============");
        itemService.deleteContentsInItem(userId, item_no, contents_no);
    }

    @ApiOperation("자기소개서 제목을 눌렀을 때 나오는 항목 내용만 수정")
    @PutMapping("/{item_no}/{contents_no}")
    public ContentsInItemDto updateContentsInItem(@RequestHeader(name = "Authorization") String token, @PathVariable Long item_no, @PathVariable Long contents_no, @RequestBody ContentsInItemDto contentsInItemDto){
        String userId = jwtProvider.getUserIdByToken(token);
        log.info("============= " + userId+"의 자기소개서 " + item_no + "번의" +contents_no +"번 수정 =============");
        return itemService.updateContentsInItem(userId, item_no, contents_no,contentsInItemDto);
    }


    @ApiOperation("자기소개서의 제목 수정")
    @PutMapping("/{item_no}")
    public String updateItemTitle(@RequestHeader(name = "Authorization") String token, @PathVariable Long item_no, @RequestBody String title){
        String userId = jwtProvider.getUserIdByToken(token);
        return itemService.updateItemTitle(userId,item_no, title);
    }

    @ApiOperation("자기소개서 삭제")
    @DeleteMapping("/{item_no}")
    public void deleteItem(@RequestHeader(name = "Authorization") String token, @PathVariable Long item_no){
        String userId = jwtProvider.getUserIdByToken(token);
        itemService.deleteItem(userId,item_no);
    }


    @ApiOperation("자기소개서 추가")
    @PostMapping
    public void makeItem(@RequestHeader(name = "Authorization") String token, @RequestBody ItemWrapperDto itemWrapperDto){
        String userId = jwtProvider.getUserIdByToken(token);
        itemService.makeItem(userId,itemWrapperDto.getItemDto(),itemWrapperDto.getContentsDto());
    }


}
