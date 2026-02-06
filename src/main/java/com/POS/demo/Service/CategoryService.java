package com.POS.demo.Service;

import com.POS.demo.Exceptions.UserException;
import com.POS.demo.PayLoad.Dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto) throws Exception;

    List<CategoryDto> getAllCategoriesByStore(Long  storeId);

    CategoryDto  updateCategory(Long categoryId , CategoryDto categoryDto) throws Exception;

    void deleteCategory(Long categoryId) throws Exception;
}
