package com.efrei.catalogmicroservice.repository;

import com.efrei.catalogmicroservice.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, String> {

    Optional<Catalog> findByBowlingId(String bowlingId);

    void deleteByBowlingId(String bowlingId);

}
