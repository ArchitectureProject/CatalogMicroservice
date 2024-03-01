package com.efrei.catalogmicroservice.service;

import com.efrei.catalogmicroservice.exception.custom.ProductNotFoundException;
import com.efrei.catalogmicroservice.exception.custom.WrongUserRoleException;
import com.efrei.catalogmicroservice.model.Product;
import com.efrei.catalogmicroservice.model.UserRole;
import com.efrei.catalogmicroservice.model.dto.ProductToCreate;
import com.efrei.catalogmicroservice.repository.ProductRepository;
import com.efrei.catalogmicroservice.utils.JwtUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final JwtUtils jwtUtils;

    public ProductServiceImpl(ProductRepository productRepository, JwtUtils jwtUtils) {
        this.productRepository = productRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Product addProductToCatalog(String bearerToken, ProductToCreate productToCreate) {
        if(!jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT)){
            throw new WrongUserRoleException("User role does not gives him rights to call this endpoint");
        }

        Product product = mapProductToCreateIntoProduct(productToCreate);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts(String bearerToken) {
        if(!jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT)){
            throw new WrongUserRoleException("User role does not gives him rights to call this endpoint");
        }

        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllAvailableProducts(String bearerToken) {
        jwtUtils.validateJwt(bearerToken.substring(7), null);
        return productRepository.findByAvailableTrue();
    }

    @Override
    public Product getProductById(String bearerToken, String productId) {
        jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT);
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
    }

    @Override
    public List<Product> getProductsByIds(String bearerToken, List<String> productIds) {
        jwtUtils.validateJwt(bearerToken.substring(7), null);
        return productRepository.findAllById(productIds);
    }

    @Override
    public void setProductUnavailable(String bearerToken, String productId) {
        if(!jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT)){
            throw new WrongUserRoleException("User role does not gives him rights to call this endpoint");
        }

        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        productRepository.setProductUnavailable(productId);
    }

    @Override
    public void setProductAvailable(String bearerToken, String productId) {
        if(!jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT)){
            throw new WrongUserRoleException("User role does not gives him rights to call this endpoint");
        }

        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        productRepository.setProductAvailable(productId);
    }

    @Override
    public Product modifyProduct(String bearerToken, String productId, ProductToCreate productToCreate) {
        jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT);

        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        Product product = mapProductToCreateIntoProduct(productToCreate);
        product.setId(productId);

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(String bearerToken, String productId) {
        jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT);

        productRepository.deleteById(productId);
    }

    private Product mapProductToCreateIntoProduct(ProductToCreate productToCreate){
        Product product = new Product();
        product.setName(productToCreate.name());
        product.setPrice(productToCreate.price());
        product.setAvailable(productToCreate.available());

        return product;
    }
}
