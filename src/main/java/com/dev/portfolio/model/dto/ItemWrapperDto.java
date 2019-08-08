package com.dev.portfolio.model.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ItemWrapperDto {
    ItemDto itemDto;
    List<ContentsDto> contentsDto;
}
