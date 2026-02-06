package com.POS.demo.PayLoad.Dto;

import com.POS.demo.Domain.StoreStatus;
import com.POS.demo.modal.StoreContact;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class StoreDto {


    private Long id;


    private String BrandName;


    private UserDto StoreAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String StoreType;

    private StoreStatus status;

    private StoreContact contact;
}
