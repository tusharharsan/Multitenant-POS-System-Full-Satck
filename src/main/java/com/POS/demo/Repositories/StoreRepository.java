package com.POS.demo.Repositories;

import com.POS.demo.modal.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store , Long> {
    Store findByStoreAdminId(Long id);


}
