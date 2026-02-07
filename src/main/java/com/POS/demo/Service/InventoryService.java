package com.POS.demo.Service;

import com.POS.demo.PayLoad.Dto.InventoryDto;
import com.POS.demo.modal.Inventory;

import java.util.List;

public interface InventoryService {
    InventoryDto createInventory(InventoryDto inventoryDto) throws Exception;
    InventoryDto updateInventory(Long id , InventoryDto inventoryDto) throws Exception;
    void deleteInventory(Long id) throws Exception;
    InventoryDto getInventoryById(Long id) throws Exception;
    InventoryDto getInventoryByProductIdAndBranchId(Long productId , Long branchId);
    List<InventoryDto> getAllInventoriesByBranchId(Long branchId);

}
