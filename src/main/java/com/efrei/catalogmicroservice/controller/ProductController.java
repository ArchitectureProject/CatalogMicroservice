package com.efrei.catalogmicroservice.controller;

import com.efrei.catalogmicroservice.model.Product;
import com.efrei.catalogmicroservice.model.dto.ProductToCreate;
import com.efrei.catalogmicroservice.service.ProductService;
import org.jose4j.jwt.MalformedClaimException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add-to-catalog")
    public Product addProductToCatalog(@RequestHeader(name = "Authorization") String bearerToken,
                                       @RequestBody ProductToCreate product) throws MalformedClaimException {
        return productService.addProductToCatalog(bearerToken, product);
    }
}
