package com.POS.demo.Service.Impl;

import com.POS.demo.PayLoad.Dto.BranchDto;
import com.POS.demo.Repositories.BranchRepository;
import com.POS.demo.Repositories.StoreRepository;
import com.POS.demo.Repositories.UserRespository;
import com.POS.demo.Service.BranchService;
import com.POS.demo.Service.UserService;
import com.POS.demo.mapper.BranchMapper;
import com.POS.demo.modal.Branch;
import com.POS.demo.modal.Store;
import com.POS.demo.modal.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;
    private final UserRespository userRespository;
    private final UserService userService;


    @Override
    public BranchDto createBranch(BranchDto branchDto, User user) throws Exception {
        User currentuser = userService.GetCurrentUser();
        Store store = storeRepository.findByStoreAdmin_Id(currentuser.getId());

        Branch branch = BranchMapper.toEntity(branchDto ,store);

        Branch savedBranch = branchRepository.save(branch);

        return BranchMapper.toDto(savedBranch);
    }

    @Override
    public BranchDto updateBranch(Long id, BranchDto branchDto, User user) throws Exception {
        Branch existing  = branchRepository.findById(id).orElseThrow(
                () -> new Exception("Branch not found")
        );

        existing.setBranchName(branchDto.getBranchName());
        existing.setWorkingDays(branchDto.getWorkingDays());
        existing.setEmail(branchDto.getEmail());
        existing.setPhone(branchDto.getPhone());
        existing.setAddress(branchDto.getAddress());
        existing.setOpeningTime(branchDto.getOpeningTime());
        existing.setClosingTime(branchDto.getClosingTime());
        existing.setUpdatedAt(LocalDateTime.now());

        Branch updatedBranch = branchRepository.save(existing);
        return BranchMapper.toDto(updatedBranch);

        
        //video left at 5:45 time
    }

    @Override
    public BranchDto deleteBranch(Long id) throws Exception {
        return null;
    }

    @Override
    public List<BranchDto> getAllBranchesByStore(Long storeId) {
        return List.of();
    }

    @Override
    public BranchDto getBranchById(Long id) throws Exception {
        return null;
    }
}
