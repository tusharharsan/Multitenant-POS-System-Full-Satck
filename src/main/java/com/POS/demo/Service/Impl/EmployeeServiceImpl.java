package com.POS.demo.Service.Impl;

import com.POS.demo.Domain.UserRole;
import com.POS.demo.PayLoad.Dto.UserDto;
import com.POS.demo.Repositories.BranchRepository;
import com.POS.demo.Repositories.StoreRepository;
import com.POS.demo.Repositories.UserRespository;
import com.POS.demo.Service.EmployeeService;
import com.POS.demo.mapper.UserMapper;
import com.POS.demo.modal.Branch;
import com.POS.demo.modal.Store;
import com.POS.demo.modal.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final UserRespository userRespository;
    private final StoreRepository storeRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(
                ()-> new Exception("store not exist...")
        );
        Branch branch = null;
        if(employee.getRole()==UserRole.ROLE_BRANCH_MANAGER){
            if(employee.getBranchId()==null){
                throw new Exception("branch id is required for branch manager...");
            }
            branch = branchRepository.findById(employee.getBranchId()).orElseThrow(
                    ()-> new Exception("branch not exist...")
            );
        }
        User user = UserMapper.toEntity(employee);
        user.setStore(store);
        user.setBranch(branch);
        user.setPassword(passwordEncoder.encode(employee.getPassword()));

        User savedEmployee = userRespository.save(user);
        if(employee.getRole()==UserRole.ROLE_BRANCH_MANAGER && branch!=null){
            branch.setManager(savedEmployee);
            branchRepository.save(branch);
        }
        return UserMapper.toDto(savedEmployee);
    }

    @Override
    public UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception {
        Branch branch = branchRepository.findById(branchId).orElseThrow(
                ()-> new Exception("branch not exist...")
        );

        if(employee.getRole()==UserRole.ROLE_BRANCH_CASHIER ||  employee.getRole()==UserRole.ROLE_BRANCH_MANAGER){
            User user = UserMapper.toEntity(employee);
            user.setBranch(branch);
            user.setPassword(passwordEncoder.encode(employee.getPassword()));
            return UserMapper.toDto(userRespository.save(user));
        }
        throw new Exception("invalid role for branch employee...");
    }

    @Override
    public User updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception {
        User existingEmployee = userRespository.findById(employeeId).orElseThrow(
                ()-> new  Exception("employee not exist...")
        );
        Branch branch = branchRepository.findById(employeeDetails.getBranchId()).orElseThrow(
                ()-> new Exception("branch not exist...")
        );
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setUsername(employeeDetails.getUsername());
        existingEmployee.setPassword(employeeDetails.getPassword());
        existingEmployee.setRole(employeeDetails.getRole());
        existingEmployee.setBranch(branch);

        return userRespository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        User employee = userRespository.findById(employeeId).orElseThrow(
                ()-> new RuntimeException("employee not exist...")
        );

        userRespository.delete(employee);
    }

    @Override
    public User findEmployeeById(Long employeeId) {
        User user = userRespository.findById(employeeId).orElseThrow(
                ()-> new RuntimeException("employee not exist...")
        );
        return user;
    }

    @Override
    public List<User> findStoreEmployees(Long storeId, UserRole role) {
        Store store = storeRepository.findById(storeId).orElseThrow(
                ()-> new RuntimeException("store not exist...")
        );
        return userRespository.findByStore(store).stream().filter(
                user-> role==null || user.getRole()==role
        ).collect(Collectors.toList()
        );
    }

    @Override
    public List<User> findBranchEmployees(Long branchId, UserRole role) {
        Branch branch =  branchRepository.findById(branchId).orElseThrow(
                ()-> new RuntimeException("branch not exist...")
        );
        return userRespository.findByBranchId(branchId)
                .stream().filter(
                        user->role==null || user.getRole()==role
                ).collect(Collectors.toList());
    }
}
