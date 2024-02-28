package com.efrei.catalogmicroservice.repository;

import com.efrei.catalogmicroservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByAvailableTrue();

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.available = false WHERE p.id = :productId")
    void setProductUnavailable(String productId);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.available = true WHERE p.id = :productId")
    void setProductAvailable(String productId);
}
