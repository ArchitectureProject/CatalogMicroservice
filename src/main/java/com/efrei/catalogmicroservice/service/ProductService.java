package com.efrei.catalogmicroservice.service;

import com.efrei.catalogmicroservice.model.Product;
import com.efrei.catalogmicroservice.model.dto.ProductToCreate;

import java.util.List;

public interface ProductService {

    Product createProduct(String bearerToken, ProductToCreate productToCreate);

    List<Product> getAllProducts(String bearerToken);

    List<Product> getAllAvailableProducts(String bearerToken);

    Product getProductById(String bearerToken, String productId);

    List<Product> getProductsByIds(String bearerToken, List<String> productIds);

    void setProductUnavailable(String bearerToken, String productId);

    void setProductAvailable(String bearerToken, String productId);

    Product modifyProduct(String bearerToken, String productId, ProductToCreate productToCreate);

    void deleteProduct(String bearerToken, String productId);

}
