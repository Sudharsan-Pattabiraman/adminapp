package com.b2s.service.adminapp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

/**
 * Created by spattabiraman on 10/22/2018.
 */

public class Product {

    private String productName;

    private int productPoints;

    private String productId;

    private ProductType productType;

    private Optional<String> description;

    private Product(ProductBuilder productBuilder) {
        this.productName = productBuilder.productName;
        this.productPoints = productBuilder.productPoints;
        this.productId = productBuilder.productId;
        this.productType = productBuilder.productType;
        this.description = productBuilder.description;
    }

    @JsonCreator
    public static Product create(@JsonProperty("productName") final String theProductName,
                                 @JsonProperty("productPoints") final int theProductPoints,
                                 @JsonProperty("productId") final String theProductId,
                                 @JsonProperty("productType") final ProductType theProductType,
                                 @JsonProperty("description") final String theDescription) {

        return builder().withProductId(theProductId).withProductName(theProductName).withProductPoints(theProductPoints)
                .withProductType(theProductType).withDescription(theDescription).build();
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPoints() {
        return productPoints;
    }

    public ProductType getProductType() {
        return productType;
    }

    public String getProductId() {
        return productId;
    }

    public Optional<String> getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (getProductPoints() != product.getProductPoints()) return false;
        if (!getProductName().equals(product.getProductName())) return false;
        if (!getProductId().equals(product.getProductId())) return false;
        if (getProductType() != product.getProductType()) return false;
        return getDescription().equals(product.getDescription());

    }

    @Override
    public int hashCode() {
        int result = getProductName().hashCode();
        result = 31 * result + getProductPoints();
        result = 31 * result + getProductId().hashCode();
        result = 31 * result + getProductType().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productPoints=" + productPoints +
                ", productId='" + productId + '\'' +
                ", productType=" + productType +
                ", description='" + description + '\'' +
                '}';
    }

    public static class ProductBuilder {

        private String productName;

        private int productPoints;

        private String productId;

        private ProductType productType;

        private Optional<String> description = Optional.empty();

        public ProductBuilder() {
        }

        public ProductBuilder withProductName(final String productName) {
            this.productName = productName;
            return this;
        }

        public ProductBuilder withProductPoints(final int productPoints) {
            this.productPoints = productPoints;
            return this;
        }

        public ProductBuilder withProductId(final String productId) {
            this.productId = productId;
            return this;
        }

        public ProductBuilder withProductType(final ProductType productType) {
            this.productType = productType;
            return this;
        }


        public ProductBuilder withDescription(final String description) {
            this.description = Optional.ofNullable(description);
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
