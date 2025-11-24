package com.ecommerce.project.repositories;

import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>  {
//    @Query("SELECT c FROM Address c WHERE c.user.email =?1")
//    Address findAddressByEmail(String email);

    Address findByAddressId(long addressId);

    @Query("SELECT c FROM Address c WHERE c.user.id=?1 AND c.id=?2")
    Address findAddressByUserId(Long userId, Long addressId);
}
