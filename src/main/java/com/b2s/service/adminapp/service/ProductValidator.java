package com.b2s.service.adminapp.service;

import com.b2s.service.adminapp.exception.BadRequestException;
import com.b2s.service.adminapp.model.Product;
import org.springframework.stereotype.Component;

/**
 * Created by spattabiraman on 12/10/2018.
 */
@Component
public class ProductValidator {

    public void validate(final Product product) {

        if (product.getProductName() == null) {

            throw new BadRequestException("Product name is empty");
        }

        if (product.getProductName().isEmpty()) {

            throw new BadRequestException("Product name is empty");
        }

        if (product.getProductType() == null) {

            throw new BadRequestException("Product type is empty");
        }
    }
}
