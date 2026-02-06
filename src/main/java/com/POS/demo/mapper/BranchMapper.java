package com.POS.demo.mapper;

import com.POS.demo.PayLoad.Dto.BranchDto;
import com.POS.demo.modal.Branch;
import com.POS.demo.modal.Store;

import java.time.LocalDateTime;

public class BranchMapper {

    public static BranchDto toDto(Branch branch){
        return BranchDto.builder()
                .id(branch.getId())
                .BranchName(branch.getBranchName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .email(branch.getEmail())
                .closingTime(branch.getClosingTime())
                .openingTime(branch.getOpeningTime())
                .workingDays(branch.getWorkingDays())
                .storeId(branch.getStore() != null ? branch.getStore().getId() : null)
                .createdAt(branch.getCreatedAt())
                .updatedAt(branch.getUpdatedAt())
                .build();
    }

    public static Branch toEntity(BranchDto branchDto , Store store){
        return Branch.builder()
                .BranchName(branchDto.getBranchName())
                .address(branchDto.getAddress())
                .phone(branchDto.getPhone())
                .email(branchDto.getEmail())
                .closingTime(branchDto.getClosingTime())
                .store(store)
                .openingTime(branchDto.getOpeningTime())
                .workingDays(branchDto.getWorkingDays())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
