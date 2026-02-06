package com.POS.demo.Service;

import com.POS.demo.PayLoad.Dto.BranchDto;
import com.POS.demo.modal.User;

import java.util.List;

public interface BranchService {

    BranchDto createBranch(BranchDto branchDto , User user) throws Exception;
    BranchDto updateBranch(Long id , BranchDto branchDto , User user) throws Exception;
    void deleteBranch(Long id) throws Exception;
    List<BranchDto> getAllBranchesByStore(Long storeId);
    BranchDto getBranchById(Long id) throws Exception;




}
