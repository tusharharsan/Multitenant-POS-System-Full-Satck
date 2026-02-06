package com.POS.demo.PayLoad.Dto;

import com.POS.demo.modal.Category;
import com.POS.demo.modal.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;


    private String name;


    private String sku;

    private String description;

    private Double mrp;

    private Double sellingprice;

    private String brand;

    private String image;

    private CategoryDto categoryDto;

    private Long categoryId;

    private Long storeId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
