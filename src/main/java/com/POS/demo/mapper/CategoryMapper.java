package com.POS.demo.mapper;

import com.POS.demo.PayLoad.Dto.CategoryDto;
import com.POS.demo.modal.Category;

public class CategoryMapper {

    public static CategoryDto toDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .StoreId(category.getStore() != null ? category.getStore().getId() : null)
                .build();
    }
}
