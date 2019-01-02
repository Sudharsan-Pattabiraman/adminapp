package com.b2s.service.adminapp.service;

import com.b2s.service.adminapp.model.Product;
import com.b2s.service.adminapp.model.ProductInfo;

import java.util.List;

/**
 * Created by spattabiraman on 10/22/2018.
 */
public interface ProductService {

    Product insert(final Product product);

    Product viewProduct(final String name);

    Product reviseProduct(final String id, final Product product);

    String remove(final String name);

    List<Product> productList();

    List<ProductInfo> productListByType(final String type, final int points);

    String erase(final String name);
}
