package com.POS.demo.mapper;

import com.POS.demo.PayLoad.Dto.StoreDto;
import com.POS.demo.modal.Store;
import com.POS.demo.modal.User;

public class StoreMapper {

    public static StoreDto toDto(Store store){
        StoreDto storeDto = new StoreDto();
        storeDto.setId(store.getId());
        storeDto.setBrandName(store.getBrandName());
        storeDto.setDescription(store.getDescription());
        if(store.getStoreAdmin() != null) {
            storeDto.setStoreAdmin(UserMapper.toDto(store.getStoreAdmin()));
        }
        storeDto.setStoreType(store.getStoreType());
        storeDto.setContact(store.getContact());
        storeDto.setStatus(store.getStatus());
        storeDto.setCreatedAt(store.getCreatedAt());
        storeDto.setUpdatedAt(store.getUpdatedAt());
        return storeDto;

    }

    public static Store toEntity(StoreDto storeDto , User user){
        Store store = new Store();
        store.setId(storeDto.getId());
        store.setBrandName(storeDto.getBrandName());
        store.setDescription(storeDto.getDescription());
        store.setStoreAdmin(user);  // Set the authenticated user as StoreAdmin
        store.setStoreType(storeDto.getStoreType());
        store.setContact(storeDto.getContact());
        store.setStatus(storeDto.getStatus());
        store.setCreatedAt(storeDto.getCreatedAt());
        store.setUpdatedAt(storeDto.getUpdatedAt());

        return store;
    }
}
