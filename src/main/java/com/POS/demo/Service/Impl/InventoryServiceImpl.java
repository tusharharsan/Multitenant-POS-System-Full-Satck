package com.POS.demo.Service.Impl;

import com.POS.demo.PayLoad.Dto.InventoryDto;
import com.POS.demo.Repositories.BranchRepository;
import com.POS.demo.Repositories.InventoryRepository;
import com.POS.demo.Repositories.ProductRepository;
import com.POS.demo.Service.InventoryService;
import com.POS.demo.mapper.InventoryMapper;
import com.POS.demo.modal.Branch;
import com.POS.demo.modal.Inventory;
import com.POS.demo.modal.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final BranchRepository  branchRepository;
    private final ProductRepository productRepository;

    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) throws Exception {
        Branch branch = branchRepository.findById(inventoryDto.getBranchId()).orElseThrow(
                ()-> new Exception("branch not exist...")
        );

        Product product = productRepository.findById(inventoryDto.getProductId()).orElseThrow(
                ()-> new Exception("product not exist...")
        );
        Inventory inventory = InventoryMapper.toEntity(inventoryDto,branch , product);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryMapper.toDto(savedInventory);
    }

    @Override
    public InventoryDto updateInventory(Long id,InventoryDto inventoryDto) throws Exception {
        Inventory  inventory = inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("inventory not exist...")
        );
        inventory.setQuantity(inventoryDto.getQuantity());
        Inventory updatedInventory = inventoryRepository.save(inventory);
        return InventoryMapper.toDto(updatedInventory);

    }

    @Override
    public void deleteInventory(Long id) throws Exception {
        Inventory  inventory = inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("inventory not exist...")
        );
        inventoryRepository.delete(inventory);
    }

    @Override
    public InventoryDto getInventoryById(Long id) throws Exception {
        Inventory  inventory = inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("inventory not exist...")
        );
        return null;
    }

    @Override
    public InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId,branchId);

        return InventoryMapper.toDto(inventory);
    }

    @Override
    public List<InventoryDto> getAllInventoriesByBranchId(Long branchId) {
        List<Inventory> invetories = inventoryRepository.findByBranchId(branchId);
        return invetories.stream().map(
                InventoryMapper::toDto
        ).collect(Collectors.toList());
    }
}
