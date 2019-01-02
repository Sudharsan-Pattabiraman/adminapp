package com.b2s.service.adminapp.service;

import com.b2s.service.adminapp.dao.JdbcProductDao;
import com.b2s.service.adminapp.dao.ProductDao;
import com.b2s.service.adminapp.dao.ProductMapDao;
import com.b2s.service.adminapp.exception.NotFoundException;
import com.b2s.service.adminapp.model.Product;
import com.b2s.service.adminapp.model.ProductInfo;
import com.b2s.service.adminapp.model.SortById;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by spattabiraman on 10/22/2018.
 */
@Service
public class DefaultProductService implements ProductService {

    private final ProductDao productDao;
    private final ProductValidator productValidator;

    public DefaultProductService(final JdbcProductDao jdbcProductDao, final ProductValidator productValidator) {
        this.productDao = jdbcProductDao;
        this.productValidator = productValidator;
    }

    public Product insert(final Product product) {

        productValidator.validate(product);
        return productDao.insertProduct(product);
    }

    public Product viewProduct(final String name) {

        return productDao.getProductByName(name);
    }

    public Product reviseProduct(final String id, final Product product) {

        productValidator.validate(product);
        int i = productDao.correctProduct(id, product);
        return product;
    }

    public String remove(final String name) {

        int affectedLines = productDao.eraseProduct(name);
        if (affectedLines == 0) {
            throw new NotFoundException("Product not found");
        }
        return "Product Deleted";
    }

    public String erase(final String id){

        return null;
    }

    public List<Product> productList() {

        //Collections.sort(productList, new SortById());
        return productDao.getProducts();
    }

    public List<ProductInfo> productListByType(final String type, final int points) {

        return productDao.getProducts()
                .stream()
                .filter(product -> product.getProductPoints() >= points)
                .filter(product -> product.getProductType().name().equalsIgnoreCase(type))
                .map(product -> {
                    ProductInfo productInfo = new ProductInfo();
                    productInfo.setProductId(product.getProductId());
                    productInfo.setProductName(product.getProductName());
                    productInfo.setProductPoints(product.getProductPoints());
                    return productInfo;
                }).collect(Collectors.toList());
    }
}


