package com.b2s.service.adminapp.controller;

import com.b2s.service.adminapp.model.Product;
import com.b2s.service.adminapp.model.ProductInfo;
import com.b2s.service.adminapp.service.DefaultProductService;
import com.b2s.service.adminapp.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class ProductController {

    final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(final ProductService productService) {

        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product save(@RequestBody final Product product) {

        logger.info("Saving a resource");
        return productService.insert(product);

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/products/{name}")
    public ResponseEntity<Product> retrieve(@PathVariable final String name) {

        logger.info("Retrieve a product");
        Product retrieveProduct = productService.viewProduct(name);
        return ResponseEntity.ok(retrieveProduct);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/products/{id}")
    public Product update(@PathVariable final String id, @RequestBody final Product product) {

        logger.info("Updating product info");
        return productService.reviseProduct(id, product);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/products/{id}")
    public String delete(@PathVariable final String id) {

        return productService.remove(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/products")
    public List<Product> listOfProduct() {

        logger.info("Get list of products information");
        return productService.productList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/products/{type}/{points}")
    public List<ProductInfo> productListBasedOnType(@PathVariable final String type, @PathVariable final int points) {

        logger.info("Getting list of product based on type and points");
        return productService.productListByType(type, points);
    }
}
