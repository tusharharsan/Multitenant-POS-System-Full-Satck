package com.POS.demo.Service;

import com.POS.demo.Domain.UserRole;
import com.POS.demo.PayLoad.Dto.UserDto;
import com.POS.demo.modal.User;

import java.util.List;

public interface EmployeeService {
    UserDto createStoreEmployee(UserDto  employee  , Long  storeId)throws Exception;
    UserDto createBranchEmployee(UserDto  employee  , Long  branchId)throws Exception;
    User updateEmployee(Long employeeId , UserDto employeeDetails) throws Exception;
    void deleteEmployee(Long employeeId);
    User findEmployeeById(Long employeeId);
    List<UserDto> findStoreEmployees(Long storeId , UserRole role);
    List<UserDto> findBranchEmployees(Long branchId, UserRole role);

}
