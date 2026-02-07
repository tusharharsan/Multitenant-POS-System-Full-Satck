package com.POS.demo.PayLoad.Dto;

import com.POS.demo.modal.Branch;
import com.POS.demo.modal.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
    private Long id;

    private BranchDto branch;

    private Long branchId;

    private Long productId;

    private ProductDto product;


    private Integer quantity;

    private LocalDateTime lastUpdated;}
