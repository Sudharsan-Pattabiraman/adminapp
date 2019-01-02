package com.b2s.service.adminapp.dao;

import com.b2s.service.adminapp.model.Product;

import java.util.List;

/**
 * Created by spattabiraman on 10/22/2018.
 */
public interface ProductDao {

    Product insertProduct(final Product product);

    List<Product> getProducts();

    int correctProduct(final String id, final Product product);

    Product getProductByName(final String name);

    int eraseProduct(final String name);

    String delete(final String name);

    Product updateProduct(final String id, final Product product);

    //List<Product> saveListOfProducts(final List<Product> productList);
}
