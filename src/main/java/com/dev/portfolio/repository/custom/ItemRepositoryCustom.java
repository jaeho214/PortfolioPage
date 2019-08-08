package com.dev.portfolio.repository.custom;

import com.dev.portfolio.model.dto.ContentsInItemDto;
import com.dev.portfolio.model.dto.ItemDto;

import java.util.List;

public interface ItemRepositoryCustom {
    List<ItemDto> getItems(String user_id);
    List<ContentsInItemDto> getContentsInItem(String user_id, Long item_no);
    void deleteContentsInItem(String user_id, Long item_no, Long contents_no);
    String updateItemTitle(String user_id, Long item_no,String title);
    ContentsInItemDto updateContentsInItem(String user_id, Long item_no, Long contents_no, ContentsInItemDto contentsInItemDto);
    void deleteItem(String user_id, Long item_no);
}
