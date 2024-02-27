package com.efrei.catalogmicroservice.service;

import com.efrei.catalogmicroservice.model.Product;
import com.efrei.catalogmicroservice.model.UserRole;
import com.efrei.catalogmicroservice.model.dto.ProductToCreate;
import com.efrei.catalogmicroservice.repository.ProductRepository;
import com.efrei.catalogmicroservice.utils.JwtUtils;
import org.jose4j.jwt.MalformedClaimException;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    JwtUtils jwtUtils;

    public ProductServiceImpl(ProductRepository productRepository, JwtUtils jwtUtils) {
        this.productRepository = productRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Product addProductToCatalog(String bearerToken, ProductToCreate productToCreate) throws MalformedClaimException {
        if(!jwtUtils.isJwtValid(bearerToken.substring(7), UserRole.AGENT)){
            throw new RuntimeException("Mauvais role");
        }

        Product product = mapProductToCreateIntoProduct(productToCreate);
        return productRepository.save(product);
    }

    private Product mapProductToCreateIntoProduct(ProductToCreate productToCreate){
        Product product = new Product();
        product.setName(productToCreate.name());
        product.setPrice(productToCreate.price());
        product.setAvailable(productToCreate.available());

        return product;
    }
}
