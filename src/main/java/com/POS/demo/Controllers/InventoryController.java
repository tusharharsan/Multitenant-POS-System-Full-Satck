package com.POS.demo.Controllers;

import com.POS.demo.PayLoad.Dto.InventoryDto;
import com.POS.demo.PayLoad.Response.ApiResponse;
import com.POS.demo.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventories")
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping()
    public ResponseEntity<InventoryDto> create(
            @RequestBody InventoryDto inventoryDto
    ) throws Exception {
        return ResponseEntity.ok(
                inventoryService.createInventory(inventoryDto)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<InventoryDto> Update(
            @RequestBody InventoryDto inventoryDto,
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(
                inventoryService.updateInventory(id,inventoryDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> Delete(
            @PathVariable Long id
    ) throws Exception {
        inventoryService.deleteInventory(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Inventory deleted successfully");

        return ResponseEntity.ok(
            apiResponse
        );
    }

    @GetMapping("/branch/{branchId}/product/{productId}")
    public ResponseEntity<InventoryDto> getInventoryByProductAndBranchId(
            @PathVariable Long productId,
            @PathVariable Long branchId
    ) throws Exception {
        return ResponseEntity.ok(
                inventoryService.getInventoryByProductIdAndBranchId(productId,branchId)
        );
    }


    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<InventoryDto>> getInventoryByBranch(
            @PathVariable Long branchId
    ) throws Exception {
        return ResponseEntity.ok(
                inventoryService.getAllInventoriesByBranchId(branchId)
        );
    }
}
