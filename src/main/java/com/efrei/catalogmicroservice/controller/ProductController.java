package com.efrei.catalogmicroservice.controller;

import com.efrei.catalogmicroservice.model.Product;
import com.efrei.catalogmicroservice.model.dto.ProductToCreate;
import com.efrei.catalogmicroservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add-to-catalog")
    public Product addProductToCatalog(@RequestHeader(name = "Authorization") String bearerToken,
                                       @RequestBody ProductToCreate product){
        return productService.addProductToCatalog(bearerToken, product);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(@RequestHeader(name = "Authorization") String bearerToken){
        return productService.getAllProducts(bearerToken);
    }

    @GetMapping("/catalog")
    public List<Product> getCatalog(@RequestHeader(name = "Authorization") String bearerToken){
        return productService.getAllAvailableProducts(bearerToken);
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@RequestHeader(name = "Authorization") String bearerToken,
                                  @PathVariable String id){
        return productService.getProductById(bearerToken, id);
    }

    @PostMapping("/productsByIds")
    public List<Product> getProductsByIds(@RequestHeader(name = "Authorization") String bearerToken,
                                         @RequestBody List<String> productsIds){
        return productService.getProductsByIds(bearerToken, productsIds);
    }

    @PutMapping("/set-available/{id}")
    public void setProductAvailable(@RequestHeader(name = "Authorization") String bearerToken,
                                    @PathVariable String id){
        productService.setProductAvailable(bearerToken, id);
    }

    @PutMapping("/set-unavailable/{id}")
    public void setProductUnavailable(@RequestHeader(name = "Authorization") String bearerToken,
                                      @PathVariable String id){
        productService.setProductUnavailable(bearerToken, id);
    }

    @PutMapping("/product/{id}")
    public Product modifyProduct(@RequestHeader(name = "Authorization") String bearerToken,
                                 @PathVariable String id,
                                 @RequestBody ProductToCreate product){
        return productService.modifyProduct(bearerToken, id, product);
    }

    @DeleteMapping("/product/{id}")
    public void DeleteMapping(@RequestHeader(name = "Authorization") String bearerToken,
                                 @PathVariable String id){
        productService.deleteProduct(bearerToken, id);
    }

}
