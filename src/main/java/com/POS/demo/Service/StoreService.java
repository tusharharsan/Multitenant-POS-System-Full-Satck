package com.POS.demo.Service;

import com.POS.demo.Domain.StoreStatus;
import com.POS.demo.Exceptions.UserException;
import com.POS.demo.PayLoad.Dto.StoreDto;
import com.POS.demo.modal.Store;
import com.POS.demo.modal.User;

import java.util.List;

public interface StoreService {
    StoreDto createStore(StoreDto storeDto , User user);
    StoreDto getStoreById(Long id) throws Exception;
    List<StoreDto> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDto updateStore(Long id,StoreDto storeDto) throws UserException;
    void deleteStore(Long id) throws UserException;
    StoreDto getStoreByEmployee() throws UserException;
    StoreDto moderateStore(Long id , StoreStatus status) throws Exception;


}
