package com.POS.demo.Controllers;

import com.POS.demo.PayLoad.Dto.BranchDto;
import com.POS.demo.PayLoad.Response.ApiResponse;
import com.POS.demo.Service.BranchService;
import com.POS.demo.modal.Branch;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) throws Exception {
        BranchDto createdBranch = branchService.createBranch(branchDto , null);

        return ResponseEntity.ok(createdBranch);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BranchDto> updateBranch(@RequestBody BranchDto branchDto , @PathVariable Long id) throws Exception {
        BranchDto updatedBranch = branchService.updateBranch(id , branchDto , null);

        return ResponseEntity.ok(updatedBranch);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> getBranchById(@PathVariable Long id) throws Exception {
        BranchDto branchDto = branchService.getBranchById(id);
        return ResponseEntity.ok(branchDto);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<BranchDto>> getAllBranchesById (@PathVariable Long storeId) throws Exception {
        List<BranchDto> branchDto = branchService.getAllBranchesByStore(storeId);
        return ResponseEntity.ok(branchDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id) throws Exception {
        branchService.deleteBranch(id);
        ApiResponse response = new ApiResponse();
        response.setMessage("Branch deleted successfully");
        return ResponseEntity.ok(response);
    }




}
