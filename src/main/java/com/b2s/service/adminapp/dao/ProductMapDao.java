package com.b2s.service.adminapp.dao;

import com.b2s.service.adminapp.exception.NotFoundException;
import com.b2s.service.adminapp.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by spattabiraman on 12/20/2018.
 */
@Repository
public class ProductMapDao implements ProductDao {

    final Map<String, Product> productMap = new HashMap<>();

    public Map<String, Product> getProductMap() {
        return productMap;
    }

    @Override
    public Product insertProduct(final Product product) {

        productMap.put(product.getProductId(), product);
        return productMap.get(product.getProductId());
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<Product>(productMap.values());
    }

    @Override
    public int correctProduct(String id, Product product) {
        return 0;
    }

    @Override
    public Product updateProduct(final String name, final Product product) {

        Product dummyProduct = productMap.replace(name, product);
        if (!dummyProduct.getProductName().equals(product.getProductName())){
            return dummyProduct;
        }
        throw new NotFoundException("Product not found");
    }

    @Override
    public Product getProductByName(final String name) {
        List<Product> productList = new ArrayList<>(productMap.values());
        for (Product product : productList){
            if (product.getProductName().equalsIgnoreCase(name)){
                return product;
            }
        }
        throw new NotFoundException("Product not found");
    }

    @Override
    public int eraseProduct(final String name) {

        return 0;
    }

    public String delete(final String id){

        if (productMap.remove(id) != null){
            return "Product deleted";
        }
        throw new NotFoundException("Product not found");
    }
}
