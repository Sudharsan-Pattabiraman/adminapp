package com.b2s.service.adminapp.dao;

import com.b2s.service.adminapp.model.Product;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by spattabiraman on 10/22/2018.
 */
@Repository
public class JdbcProductDao implements ProductDao {

    private final String UPDATE_QUERY = "update Product set productname = :productName, productpoints = :productPoints," +
            " producttype = :productType, productDescription = :description where productid = :productId";

    private final String DELETE_QUERY = "delete from Product where productname = :productName";

    private final String SQL_QUERY = "select ProductID, ProductName, ProductPoints, ProductType, ProductDescription from product";

    private final String INSERT_QUERY = "insert into Product ( productname, productpoints, producttype, productdescription) " +
            "OUTPUT Inserted.productId " +
            "values(:productName, :productPoints, :productType, :description)";

    private final String VIEW_QUERY = "select ProductID, ProductName, ProductPoints, ProductType, ProductDescription from product " +
            " where productName = :productName";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcProductDao(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Product insertProduct(final Product product) {

        SqlParameterSource productDataMapping = new MapSqlParameterSource()
                .addValue("productName", product.getProductName())
                .addValue("productPoints", product.getProductPoints())
                .addValue("productType", product.getProductType().name())
                .addValue("description", product.getDescription().orElse(null));
        String productId = namedParameterJdbcTemplate.queryForObject(INSERT_QUERY, productDataMapping, String.class);

        return Product.builder()
                .withProductId(productId)
                .withProductName(product.getProductName())
                .withProductPoints(product.getProductPoints())
                .withProductType(product.getProductType())
                .withDescription(product.getDescription().orElse(null)).build();
    }

    public List<Product> getProducts() {

        return namedParameterJdbcTemplate.query(SQL_QUERY, new ProductRowMapper());
    }

    public int correctProduct(final String id, final Product product) {

        SqlParameterSource productDataMapping = new MapSqlParameterSource()
                .addValue("productName", product.getProductName())
                .addValue("productPoints", product.getProductPoints())
                .addValue("productType", product.getProductType().name())
                .addValue("description", product.getDescription().orElse(null))
                .addValue("productId", id);
        return namedParameterJdbcTemplate.update(UPDATE_QUERY, productDataMapping);
    }

    public int eraseProduct(final String name) {

        SqlParameterSource productDataMapping = new MapSqlParameterSource().addValue("productName", name);
        return namedParameterJdbcTemplate.update(DELETE_QUERY, productDataMapping);
    }

    @Override
    public String delete(String name) {
        return null;
    }

    @Override
    public Product updateProduct(String id, Product product) {
        return null;
    }

    /*@Override
    public List<Product> saveListOfProducts(List<Product> productList) {

        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(productList.toArray());
        if (namedParameterJdbcTemplate.batchUpdate())
    }*/

    public Product getProductByName(final String name) {

        SqlParameterSource nameMap = new MapSqlParameterSource().addValue("productName", name);
        try {
            return (Product) namedParameterJdbcTemplate.queryForObject(VIEW_QUERY, nameMap, new ProductRowMapper());

        } catch (RuntimeException e) {
            throw new RuntimeException("Product not found");
        }
    }
}
