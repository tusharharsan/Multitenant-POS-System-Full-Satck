package com.POS.demo.Controllers;

import com.POS.demo.Domain.StoreStatus;
import com.POS.demo.Exceptions.UserException;
import com.POS.demo.PayLoad.Dto.StoreDto;
import com.POS.demo.PayLoad.Response.ApiResponse;
import com.POS.demo.Service.StoreService;
import com.POS.demo.Service.UserService;
import com.POS.demo.mapper.StoreMapper;
import com.POS.demo.modal.Store;
import com.POS.demo.modal.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {
    private final StoreService storeService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto,
                                                @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(storeService.createStore(storeDto ,user));

    }



    @GetMapping()
    public ResponseEntity<List<StoreDto>> getAllStore(
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(storeService.getAllStores());

    }

    @GetMapping("/admin")
    public ResponseEntity<StoreDto> getStoreByAdmin(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(StoreMapper.toDto(storeService.getStoreByAdmin()));

    }

    @GetMapping("/employee")
    public ResponseEntity<StoreDto> getStoreByEmployee(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(storeService.getStoreByEmployee());

    }

    @PutMapping("/{id}")
    public  ResponseEntity<StoreDto> updateStore(@PathVariable Long id,
                                                 @RequestBody StoreDto storeDto) throws Exception{
        return ResponseEntity.ok(storeService.updateStore(id,storeDto));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id) throws Exception{
        storeService.deleteStore(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(("Successfully deleted store with id: " + id));
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}/moderate")
    public  ResponseEntity<StoreDto> moderateStore(@PathVariable Long id,
    @RequestParam StoreStatus status) throws Exception{

        return ResponseEntity.ok(storeService.moderateStore(id,status));
    }


    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable Long id,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(storeService.getStoreById(id));

    }







}
