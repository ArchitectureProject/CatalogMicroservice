package com.efrei.catalogmicroservice.service;

import com.efrei.catalogmicroservice.model.Catalog;
import com.efrei.catalogmicroservice.model.dto.ProductToCreate;

import java.util.List;

public interface CatalogService {
    List<Catalog> getAllCatalogs(String bearerToken);
    Catalog addNewProductToCatalog(String bearerToken, String catalogId, ProductToCreate productToCreate);
    Catalog addExistingProductToCatalog(String bearerToken, String catalogId, String productId);
    Catalog addNewProductToCatalogByBowlingId(String bearerToken, String bowlingId, ProductToCreate productToCreate);
    Catalog addExistingProductToCatalogByBowlingId(String bearerToken, String bowlingId, String productId);
    Catalog removeProductOfCatalog(String bearerToken, String catalogId, String productId);
    Catalog removeProductOfCatalogByBowlingId(String bearerToken, String bowlingId, String productId);
    Catalog createCatalog(String bearerToken, String bowlingId);
    void deleteCatalog(String bearerToken, String catalogId);
    void deleteCatalogByBowlingId(String bearerToken, String bowlingId);
    Catalog getCatalogByQrCode(String bearerToken, String qrCode);
}
