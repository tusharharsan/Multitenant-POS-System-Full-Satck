package com.POS.demo.mapper;

import com.POS.demo.PayLoad.Dto.InventoryDto;
import com.POS.demo.modal.Branch;
import com.POS.demo.modal.Inventory;
import com.POS.demo.modal.Product;

public class InventoryMapper {
    public static InventoryDto toDto(Inventory inventory) {
        if (inventory == null) {
            return null;
        }

        return InventoryDto.builder()
                .id(inventory.getId())
                .branchId(inventory.getBranch().getId())
                .productId(inventory.getProduct().getId())
                .product(ProductMapper.toDto(inventory.getProduct()))
                .quantity(inventory.getQuantity())
                .build();
    }

    public static Inventory toEntity(InventoryDto inventoryDto , Branch branch , Product product) {
        if (inventoryDto == null) {
            return null;
        }

        return Inventory.builder()
                .id(inventoryDto.getId())
                .branch(branch)
                .product(product)
                .quantity(inventoryDto.getQuantity())
                .build();
    }
}
