package com.POS.demo.Repositories;

import com.POS.demo.modal.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category  ,  Long> {
    List<Category> findByStoreId(Long storeId);

}
