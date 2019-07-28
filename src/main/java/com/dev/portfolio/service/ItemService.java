package com.dev.portfolio.service;
/*
자소서 항목들 모아서 하나의 자소서 생성
자소서 생성/ 자소서 삭제
 */


import com.dev.portfolio.model.dto.ItemDto;
import com.dev.portfolio.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public List<ItemDto> getItems(String user_id){
        return itemRepository.getItems(user_id);
    }
}
