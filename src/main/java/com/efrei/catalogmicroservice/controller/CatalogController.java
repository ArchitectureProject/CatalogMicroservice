package com.efrei.catalogmicroservice.controller;

import com.efrei.catalogmicroservice.model.Catalog;
import com.efrei.catalogmicroservice.model.dto.ProductToCreate;
import com.efrei.catalogmicroservice.service.CatalogService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogController {

    CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/catalogs")
    public List<Catalog> getAllCatalogs(@RequestHeader(name = "Authorization") String bearerToken){
        return catalogService.getAllCatalogs(bearerToken);
    }

    @PutMapping("/catalog/{id}/add-new-product")
    public Catalog addNewProductToCatalog(@RequestHeader(name = "Authorization") String bearerToken,
                                          @PathVariable String id,
                                          @RequestBody ProductToCreate product){
        return catalogService.addNewProductToCatalog(bearerToken, id, product);
    }

    @PutMapping("/catalog/{catalogId}/add-existing-product/{productId}")
    public Catalog addExistingProductToCatalog(@RequestHeader(name = "Authorization") String bearerToken,
                                               @PathVariable String catalogId,
                                               @PathVariable String productId){
        return catalogService.addExistingProductToCatalog(bearerToken, catalogId, productId);
    }

    @PutMapping("/catalog/by-bowling-id/{id}/add-new-product")
    public Catalog addNewProductToCatalogByBowlingId(@RequestHeader(name = "Authorization") String bearerToken,
                                                     @PathVariable String id,
                                                     @RequestBody ProductToCreate product){
        return catalogService.addNewProductToCatalogByBowlingId(bearerToken, id, product);
    }

    @PutMapping("/catalog/by-bowling-id/{bowlingId}/add-existing-product/{productId}")
    public Catalog addExistingProductToCatalogByBowlingId(@RequestHeader(name = "Authorization") String bearerToken,
                                                          @PathVariable String bowlingId,
                                                          @PathVariable String productId){
        return catalogService.addExistingProductToCatalogByBowlingId(bearerToken, bowlingId, productId);
    }

    @PutMapping("/catalog/{catalogId}/remove-product/{productId}")
    public Catalog removeProductOfCatalog(@RequestHeader(name = "Authorization") String bearerToken,
                                          @PathVariable String catalogId,
                                          @PathVariable String productId){
        return catalogService.removeProductOfCatalog(bearerToken, catalogId, productId);
    }

    @PutMapping("/catalog/by-bowling-id/{bowlingId}/remove-product/{productId}")
    public Catalog removeProductOfCatalogByBowlingId(@RequestHeader(name = "Authorization") String bearerToken,
                                                     @PathVariable String bowlingId,
                                                     @PathVariable String productId){
        return catalogService.removeProductOfCatalog(bearerToken, bowlingId, productId);
    }

    @PostMapping("/catalog/{bowlingId}")
    public Catalog createCatalog(@RequestHeader(name = "Authorization") String bearerToken,
                                 @PathVariable String bowlingId){
        return catalogService.createCatalog(bearerToken, bowlingId);
    }

    @DeleteMapping("/catalog/{catalogId}")
    public void deleteCatalog(@RequestHeader(name = "Authorization") String bearerToken,
                              @PathVariable String catalogId){
        catalogService.deleteCatalog(bearerToken, catalogId);
    }

    @DeleteMapping("/catalog/by-bowling-id/{bowlingId}")
    public void deleteCatalogByBowlingId(@RequestHeader(name = "Authorization") String bearerToken,
                                         @PathVariable String bowlingId){
        catalogService.deleteCatalogByBowlingId(bearerToken, bowlingId);
    }

    @GetMapping("/catalog/getCatalogForQRCatalog")
    public Catalog getCatalogByQrCode(@RequestHeader(name = "Authorization") String bearerToken,
                                   @PathParam("qrCode") String qrCode){
        return catalogService.getCatalogByQrCode(bearerToken, qrCode);
    }
}
