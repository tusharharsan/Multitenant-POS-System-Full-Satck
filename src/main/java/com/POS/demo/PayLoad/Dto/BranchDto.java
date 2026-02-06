package com.POS.demo.PayLoad.Dto;

import com.POS.demo.modal.Store;
import com.POS.demo.modal.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class BranchDto {
    private Long id;

    private String BranchName;

    private String address;

    private String phone;

    private String email;


    private List<String> workingDays;

    private LocalTime openingTime;
    private LocalTime closingTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private StoreDto store;

    private Long storeId;

    private UserDto Manager;

}
