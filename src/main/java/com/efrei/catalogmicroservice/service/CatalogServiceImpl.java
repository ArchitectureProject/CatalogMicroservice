package com.efrei.catalogmicroservice.service;

import com.efrei.catalogmicroservice.exception.custom.CatalogForBowlingIdAlreadyExistingException;
import com.efrei.catalogmicroservice.exception.custom.CatalogNotFoundException;
import com.efrei.catalogmicroservice.exception.custom.WrongUserRoleException;
import com.efrei.catalogmicroservice.model.Catalog;
import com.efrei.catalogmicroservice.model.Product;
import com.efrei.catalogmicroservice.model.UserRole;
import com.efrei.catalogmicroservice.model.dto.Localisation;
import com.efrei.catalogmicroservice.model.dto.ProductToCreate;
import com.efrei.catalogmicroservice.provider.bowling.BowlingParkProvider;
import com.efrei.catalogmicroservice.repository.CatalogRepository;
import com.efrei.catalogmicroservice.utils.JwtUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final JwtUtils jwtUtils;
    private final ProductService productService;
    private final CatalogRepository catalogRepository;
    private final BowlingParkProvider bowlingParkProvider;

    public CatalogServiceImpl(JwtUtils jwtUtils,
                              ProductService productService,
                              CatalogRepository catalogRepository,
                              BowlingParkProvider bowlingParkProvider) {
        this.jwtUtils = jwtUtils;
        this.productService = productService;
        this.catalogRepository = catalogRepository;
        this.bowlingParkProvider = bowlingParkProvider;
    }

    @Override
    public List<Catalog> getAllCatalogs(String bearerToken) {
        if(!jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT)){
            throw new WrongUserRoleException("User role does not gives him rights to call this endpoint");
        }

        return catalogRepository.findAll();
    }

    @Override
    public Catalog addNewProductToCatalog(String bearerToken, String catalogId, ProductToCreate productToCreate) {
        if(!jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT)){
            throw new WrongUserRoleException("User role does not gives him rights to call this endpoint");
        }

        Product product = productService.createProduct(bearerToken, productToCreate);

        Catalog catalog = catalogRepository.findById(catalogId)
                .orElseThrow(() -> new CatalogNotFoundException("Catalog not found with id: " + catalogId));

        catalog.addProduct(product);

        return catalogRepository.save(catalog);
    }

    @Override
    public Catalog addExistingProductToCatalog(String bearerToken, String catalogId, String productId) {
        if(!jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT)){
            throw new WrongUserRoleException("User role does not gives him rights to call this endpoint");
        }

        Product product = productService.getProductById(bearerToken, productId);

        Catalog catalog = catalogRepository.findById(catalogId)
                .orElseThrow(() -> new CatalogNotFoundException("Catalog not found with id: " + catalogId));

        catalog.addProduct(product);

        return catalogRepository.save(catalog);
    }

    @Override
    public Catalog addNewProductToCatalogByBowlingId(String bearerToken, String bowlingId, ProductToCreate productToCreate) {
        if(!jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT)){
            throw new WrongUserRoleException("User role does not gives him rights to call this endpoint");
        }

        Product product = productService.createProduct(bearerToken, productToCreate);

        Catalog catalog = catalogRepository.findByBowlingId(bowlingId)
                .orElseThrow(() -> new CatalogNotFoundException("Catalog not found with bowling id: " + bowlingId));

        catalog.addProduct(product);

        return catalogRepository.save(catalog);
    }

    @Override
    public Catalog addExistingProductToCatalogByBowlingId(String bearerToken, String bowlingId, String productId) {
        if(!jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT)){
            throw new WrongUserRoleException("User role does not gives him rights to call this endpoint");
        }

        Product product = productService.getProductById(bearerToken, productId);

        Catalog catalog = catalogRepository.findByBowlingId(bowlingId)
                .orElseThrow(() -> new CatalogNotFoundException("Catalog not found with bowling id: " + bowlingId));

        catalog.addProduct(product);

        return catalogRepository.save(catalog);
    }

    @Override
    public Catalog removeProductOfCatalog(String bearerToken, String catalogId, String productId) {
        if(!jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT)){
            throw new WrongUserRoleException("User role does not gives him rights to call this endpoint");
        }

        Product product = productService.getProductById(bearerToken, productId);

        Catalog catalog = catalogRepository.findById(catalogId)
                .orElseThrow(() -> new CatalogNotFoundException("Catalog not found with id: " + catalogId));

        catalog.removeProduct(product);

        return catalogRepository.save(catalog);
    }

    @Override
    public Catalog removeProductOfCatalogByBowlingId(String bearerToken, String bowlingId, String productId) {
        if(!jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT)){
            throw new WrongUserRoleException("User role does not gives him rights to call this endpoint");
        }

        Product product = productService.getProductById(bearerToken, productId);

        Catalog catalog = catalogRepository.findByBowlingId(bowlingId)
                .orElseThrow(() -> new CatalogNotFoundException("Catalog not found with bowling id: " + bowlingId));

        catalog.removeProduct(product);

        return catalogRepository.save(catalog);
    }

    @Override
    public Catalog createCatalog(String bearerToken, String bowlingId) {
        if(!jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT)){
            throw new WrongUserRoleException("User role does not gives him rights to call this endpoint");
        }

        Optional<Catalog> catalogInDb = catalogRepository.findByBowlingId(bowlingId);

        if(catalogInDb.isPresent()){
            throw new CatalogForBowlingIdAlreadyExistingException("Catalog is already existing for this bowling id: " + bowlingId);
        }

        Catalog catalog = new Catalog();
        catalog.setBowlingId(bowlingId);
        catalog.setProducts(new HashSet<>());

        return catalogRepository.save(catalog);
    }

    @Override
    public void deleteCatalog(String bearerToken, String catalogId) {
        if(!jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT)){
            throw new WrongUserRoleException("User role does not gives him rights to call this endpoint");
        }

        catalogRepository.deleteById(catalogId);
    }

    @Transactional
    @Override
    public void deleteCatalogByBowlingId(String bearerToken, String bowlingId) {
        if(!jwtUtils.validateJwt(bearerToken.substring(7), UserRole.AGENT)){
            throw new WrongUserRoleException("User role does not gives him rights to call this endpoint");
        }

        catalogRepository.deleteByBowlingId(bowlingId);
    }

    @Override
    public Catalog getCatalogByQrCode(String bearerToken, String qrCode) {
        jwtUtils.validateJwt(bearerToken.substring(7), null);

        Localisation localisation = bowlingParkProvider.getBowlingParkAlleyFromQrCode(bearerToken, qrCode);

        return catalogRepository.findByBowlingId(localisation.bowlingParkId())
                .orElseThrow(() -> new CatalogNotFoundException("Catalog not found with bowling id: " + localisation.bowlingParkId()));
    }

}
