package com.POS.demo.PayLoad.Dto;

import com.POS.demo.modal.Store;
import com.POS.demo.modal.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {
    private Long id;

    @JsonProperty("branchName")
    private String branchName;

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

    @JsonProperty("manager")
    private UserDto manager;

}
