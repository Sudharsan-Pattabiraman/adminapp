package com.b2s.service.adminapp.dao;

import com.b2s.service.adminapp.model.Product;
import com.b2s.service.adminapp.model.ProductType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by spattabiraman on 10/24/2018.
 */
public class ProductRowMapper implements RowMapper {
    public Product mapRow(ResultSet resultSet, int var2) throws SQLException {
        return Product.builder()
                .withProductName(resultSet.getString("productname"))
                .withProductId(resultSet.getString("productid"))
                .withProductPoints(resultSet.getInt("productpoints"))
                .withProductType(ProductType.valueOf(resultSet.getString("producttype")))
                .withDescription(resultSet.getString("ProductDescription"))
                .build();
    }
}
