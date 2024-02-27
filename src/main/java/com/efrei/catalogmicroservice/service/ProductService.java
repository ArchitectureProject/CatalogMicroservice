package com.efrei.catalogmicroservice.service;

import com.efrei.catalogmicroservice.model.Product;
import com.efrei.catalogmicroservice.model.dto.ProductToCreate;
import org.jose4j.jwt.MalformedClaimException;

public interface ProductService {

    Product addProductToCatalog(String bearerToken, ProductToCreate productToCreate) throws MalformedClaimException;
}
