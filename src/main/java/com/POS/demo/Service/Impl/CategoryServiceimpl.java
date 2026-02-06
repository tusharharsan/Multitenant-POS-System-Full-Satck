package com.POS.demo.Service.Impl;

import com.POS.demo.Domain.UserRole;
import com.POS.demo.Exceptions.UserException;
import com.POS.demo.PayLoad.Dto.CategoryDto;
import com.POS.demo.Repositories.CategoryRepository;
import com.POS.demo.Repositories.StoreRepository;
import com.POS.demo.Service.CategoryService;
import com.POS.demo.Service.UserService;
import com.POS.demo.mapper.CategoryMapper;
import com.POS.demo.modal.Category;
import com.POS.demo.modal.Store;
import com.POS.demo.modal.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceimpl implements CategoryService {

    private final UserService  userService;
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws Exception {
        User user = userService.GetCurrentUser();

        Store store = storeRepository.findById(categoryDto.getStoreId()).orElseThrow(
                () -> new UserException("Store not found")
        );
        Category category = Category.builder()
                .store(store)
                .name(categoryDto.getName())
                .build();

        checkAuthority(user , category.getStore());
        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getAllCategoriesByStore(Long storeId) {
        List<Category> categories = categoryRepository.findByStoreId(storeId);
        return categories.stream().map(CategoryMapper::toDto).toList();
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) throws Exception {
        Category  category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new UserException("Category not found")
        );
        User user  = userService.GetCurrentUser();
        category.setName(categoryDto.getName());
        checkAuthority(user , category.getStore());
        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long categoryId) throws Exception {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new RuntimeException("Category not found")
        );

        User user = userService.GetCurrentUser();
        checkAuthority(user,category.getStore());
        categoryRepository.delete(category);
    }

    private void checkAuthority(User user , Store store) throws Exception {
        boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean  isManager  = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore = user.equals(store.getStoreAdmin());

        if(!(isAdmin &&  isSameStore) && !isManager) {
            throw new Exception("You don't have permission to perform this action");
        }

    }
}
