package com.dev.portfolio.service;
/*
자소서 항목들 모아서 하나의 자소서 생성
자소서 생성/ 자소서 삭제
 */


import com.dev.portfolio.model.dto.ContentsDto;
import com.dev.portfolio.model.dto.ContentsInItemDto;
import com.dev.portfolio.model.dto.ItemDto;
import com.dev.portfolio.model.entity.Item;
import com.dev.portfolio.model.entity.Member;
import com.dev.portfolio.repository.ContentsInItemRepository;
import com.dev.portfolio.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ContentsInItemRepository contentsInItemRepository;

    public ItemService(ItemRepository itemRepository, ContentsInItemRepository contentsInItemRepository){
        this.itemRepository = itemRepository;
        this.contentsInItemRepository = contentsInItemRepository;
    }

    public List<ItemDto> getItems(String user_id){
        return itemRepository.getItems(user_id);
    }

    public List<ContentsInItemDto> getContentsInItem(String user_id, Long item_no) {
        return itemRepository.getContentsInItem(user_id,item_no);
    }

    public void deleteContentsInItem(String user_id, Long item_no, Long contents_no) {
        itemRepository.deleteContentsInItem(user_id,item_no,contents_no);
    }

    public String updateItemTitle(String user_id, Long item_no, String title) {
        return itemRepository.updateItemTitle(user_id, item_no, title);
    }

    public ContentsInItemDto updateContentsInItem(String user_id, Long item_no, Long contents_no, ContentsInItemDto contentsInItemDto) {
        return itemRepository.updateContentsInItem(user_id,item_no,contents_no,contentsInItemDto);
    }

    public void deleteItem(String user_id, Long item_no) {
        itemRepository.deleteItem(user_id,item_no);
    }

    public void makeItem(String user_id, ItemDto itemDto, List<ContentsDto> contentsDto) {
        List<ContentsInItemDto> contentsInItemList = new ArrayList<>();
        Member member = new Member();
        member.setId(user_id);
        Item item = itemDto.toEntity(); // 다른 객체가 리턴되어서 자꾸 다른 Item으로 들어갔던 것임
        contentsDto.forEach((contents) -> {
            contentsInItemList.add(
                    ContentsInItemDto.builder()
                            .category(contents.getCategory())
                            .content(contents.getContent())
                            .member(member)
                            .item(item)
                            .build()
            );
        });
        itemRepository.save(item);

        contentsInItemList.forEach((contents) -> {
            contentsInItemRepository.save(contents.toEntity());
        });
    }

//    public void makeItem(String user_id, List<ContentsInItemDto> contentsInItemDtos, String title) {
//        itemRepository.makeItem(user_id,contentsInItemDtos,title);
//    }
}
