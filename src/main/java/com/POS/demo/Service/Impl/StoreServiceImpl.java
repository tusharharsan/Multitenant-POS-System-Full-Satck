package com.POS.demo.Service.Impl;

import com.POS.demo.Domain.StoreStatus;
import com.POS.demo.Exceptions.UserException;
import com.POS.demo.PayLoad.Dto.StoreDto;
import com.POS.demo.Repositories.StoreRepository;
import com.POS.demo.Service.StoreService;
import com.POS.demo.Service.UserService;
import com.POS.demo.mapper.StoreMapper;
import com.POS.demo.modal.Store;
import com.POS.demo.modal.StoreContact;
import com.POS.demo.modal.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserService userService;

    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {
        Store store = StoreMapper.toEntity(storeDto,user);
        return StoreMapper.toDto(storeRepository.save(store));

    }

    @Override
    public StoreDto getStoreById(Long id) throws Exception {
        Store store = storeRepository.findByStoreAdmin_Id(id);
        if(store==null){
            throw new Exception("Store not found");
        }
        return StoreMapper.toDto(store);
    }

    @Override
    public List<StoreDto> getAllStores() {
        List<Store> dtos = storeRepository.findAll();
        return dtos.stream().map(StoreMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        User admin = userService.GetCurrentUser();
        return storeRepository.findByStoreAdmin_Id(admin.getId());
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) throws UserException {
        User user = userService.GetCurrentUser();
        Store existing = storeRepository.findByStoreAdmin_Id(user.getId());
        if(existing==null){
            throw new UserException("Store not found");
        }
        existing.setBrandName(storeDto.getBrandName());
        existing.setDescription(storeDto.getDescription());

        if(storeDto.getStoreType() != null){
            existing.setStoreType(storeDto.getStoreType());
        }

        if(storeDto.getContact() != null){
            StoreContact contact = StoreContact.builder()
                    .address(storeDto.getContact().getAddress())
                    .phone(storeDto.getContact().getPhone())
                    .email(storeDto.getContact().getEmail())
                    .build();
            existing.setContact(contact);
        }
        Store updatedStore = storeRepository.save(existing);
        return StoreMapper.toDto(updatedStore);

    }

    @Override
    public void deleteStore(Long id) throws UserException {
        Store store = getStoreByAdmin();
        if(store!=null){
            storeRepository.delete(store);
        }else {
            throw new UserException("Store not found");
        }

    }

    @Override
    public StoreDto getStoreByEmployee() throws UserException {
        User currentUser = userService.GetCurrentUser();

        if(currentUser == null ){
            throw new UserException("User not found");
        }

        return StoreMapper.toDto(currentUser.getStore());
    }

    @Override
    public StoreDto moderateStore(Long id, StoreStatus status) throws Exception {
        Store store = storeRepository.findById(id).orElse(null);
        if(store==null){
            throw new Exception("Store not found");
        }
        store.setStatus(status);
        Store updatedStore = storeRepository.save(store);
        return StoreMapper.toDto(updatedStore);

    }
}
