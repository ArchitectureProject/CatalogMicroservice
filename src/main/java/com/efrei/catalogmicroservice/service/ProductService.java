package com.efrei.catalogmicroservice.service;

import com.efrei.catalogmicroservice.model.Product;
import com.efrei.catalogmicroservice.model.dto.ProductToCreate;
import org.jose4j.jwt.MalformedClaimException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {

    Product addProductToCatalog(String bearerToken, ProductToCreate productToCreate);

    List<Product> getAllProducts(String bearerToken);

    List<Product> getAllAvailableProducts(String bearerToken);

    Product getProductById(String bearerToken, String productId);

    void setProductUnavailable(String bearerToken, String productId);

    void setProductAvailable(String bearerToken, String productId);

    Product modifyProduct(String bearerToken, String productId, ProductToCreate productToCreate);

    void deleteProduct(String bearerToken, String productId);

}
