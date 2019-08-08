package com.dev.portfolio.controller;

//자기소개서(Contents)들을 여러개 모아서 Item과 Join

import com.dev.portfolio.model.dto.ContentsDto;
import com.dev.portfolio.model.dto.ContentsInItemDto;
import com.dev.portfolio.model.dto.ItemDto;
import com.dev.portfolio.model.dto.ItemWrapperDto;
import com.dev.portfolio.service.ItemService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Log
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/portfolio/main/introduction")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @ApiOperation("자기소개서 모음 조회")
    @GetMapping("/{user_id}")
    public List<ItemDto> getItems(@PathVariable String user_id){
        log.info(user_id + "의 자기소개서 목록 출력");
        return itemService.getItems(user_id);
    }

    @ApiOperation("자기소개서 제목을 눌렀을 때 나오는 항목들 출력")
    @GetMapping("/{user_id}/{item_no}")
    public List<ContentsInItemDto> getContentsInItem(@PathVariable String user_id, @PathVariable Long item_no){
        log.info(user_id+"의 자기소개서 " + item_no + "번 출력");
        return itemService.getContentsInItem(user_id,item_no);
    }

    @ApiOperation("자기소개서 제목을 눌렀을 때 나오는 항목 제거")
    @DeleteMapping("/{user_id}/{item_no}/{contents_no}")
    public void deleteContentsInItem(@PathVariable String user_id, @PathVariable Long item_no, @PathVariable Long contents_no){
        log.info(user_id+"의 자기소개서 " + item_no + "번의" +contents_no +"번 삭제");
        itemService.deleteContentsInItem(user_id, item_no, contents_no);
    }

    @ApiOperation("자기소개서 제목을 눌렀을 때 나오는 항목 수정")
    @PutMapping("/{user_id}/{item_no}/{contents_no}")
    public ContentsInItemDto updateContentsInItem(@PathVariable String user_id, @PathVariable Long item_no, @PathVariable Long contents_no, @RequestBody ContentsInItemDto contentsInItemDto){
        log.info(user_id+"의 자기소개서 " + item_no + "번의" +contents_no +"번 수정");
        return itemService.updateContentsInItem(user_id, item_no, contents_no,contentsInItemDto);
    }


    @ApiOperation("자기소개서의 제목 수정")
    @PutMapping("/{user_id}/{item_no}")
    public String updateItemTitle(@PathVariable String user_id, @PathVariable Long item_no, @RequestBody String title){
        return itemService.updateItemTitle(user_id,item_no, title);
    }

    @ApiOperation("자기소개서 삭제")
    @DeleteMapping("/{user_id}/{item_no}")
    public void deleteItem(@PathVariable String user_id, @PathVariable Long item_no){
        itemService.deleteItem(user_id,item_no);
    }

//    @ApiOperation("자기소개서 추가")
//    @PostMapping("/{user_id}/{title}")
//    public void makeItem(@PathVariable String user_id, @RequestParam List<ContentsInItemDto> contentsInItemDtos, @PathVariable String title){
//        itemService.makeItem(user_id,contentsInItemDtos,title);
//    }
    @ApiOperation("자기소개서 추가")
    @PostMapping("/{user_id}")
    public void makeItem(@RequestBody ItemWrapperDto itemWrapperDto, @PathVariable String user_id){
        System.out.println(itemWrapperDto.getContentsDto());
        System.out.println(itemWrapperDto.getItemDto());
        itemService.makeItem(user_id,itemWrapperDto.getItemDto(),itemWrapperDto.getContentsDto());
    }


}
