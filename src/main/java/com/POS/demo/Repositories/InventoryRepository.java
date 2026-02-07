package com.POS.demo.Repositories;

import com.POS.demo.modal.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory ,Long> {
    Inventory findByProductIdAndBranchId(Long productId , Long branchId);
    List<Inventory> findByBranchId(Long branchId);


}
