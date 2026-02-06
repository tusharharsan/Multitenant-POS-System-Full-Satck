package com.POS.demo.PayLoad.Dto;

import com.POS.demo.modal.Store;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;

    private String name;

//    private Store store;

    private Long StoreId;
}
